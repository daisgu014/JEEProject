package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.Model.*;
import com.JEEProject.TableStore.services.CartService;
import com.JEEProject.TableStore.services.MailSenderService;
import com.JEEProject.TableStore.services.OrderService;
import com.JEEProject.TableStore.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class OrderAction {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService ps;
    @Autowired
    private MailSenderService mailSender;

    @Autowired
    CartService cs;
    @Autowired
    private UserAuthService userAuthService;

    private final HttpServletRequest HttpRequest;

    @RequestMapping(path = "/admin/orders/confirm/*")
    @PreAuthorize("hasRole('ADMIN')")
    public String confirmOrder(@RequestParam("id") String id){
        orderService.confirmOrder(Integer.valueOf(id),userAuthService.getUser().getId());
        return  "redirect:/admin/orders";
    }
    @RequestMapping(path = "/admin/orders/cancel/*")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancelOrder(@RequestParam("id") String id){
        orderService.cancelOrder(Integer.valueOf(id),userAuthService.getUser().getId());
        return  "redirect:/admin/orders";
    }


    @RequestMapping(path = "/user/orders/cancel/*")
    @PreAuthorize("hasRole('USER')")
    public String cancelOrderByUser(@RequestParam("id") String id, HttpSession session){
        orderService.cancelOrder(Integer.valueOf(id),((Account)session.getAttribute("account")).getId());
        return "redirect:/user/purchased";
    }
    public String formatPrice(int price) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String formattedPrice = currencyVN.format(price);
        return formattedPrice;
    }
    public String listDetails(Order order){
        StringBuilder detailsString = new StringBuilder();
        order.getDetails().forEach(item-> {
            detailsString.append(
                  " <tr>\n" +
            "                    <td  style=\"display: flex;\">\n" +

                    "                        <p>"+item.getProduct().getName()+"</p>\n" +
                    "                    </td>\n" +
                    "                    <td>"+item.getProduct().getColor()+"</td>\n" +
                    "                    <td>" +
                                       formatPrice(item.getProduct().getPrice())+
                    "                    </td>\n" +
                    "                    <td class=\"cart-qty\">"+item.getQty()+"</td>\n" +
                    "                    <td>\n" +
                                       formatPrice( item.getProduct().getPrice()* item.getQty())+
                    "                    </td>\n" +
                    "                </tr>\n"
            );
        });
        return detailsString.toString();
    }
    public String mailHtmlBody(Order order){
        return " <div style=\"width: 500px;font-family: sans-serif;\">\n" +
                "        <h3 style=\"text-align: center;\">ĐƠN ĐẶT HÀNG : "+order.getId()+"</h3>\n" +
                "        <table style=\"font-size: 12px; width: 100%; border-collapse: collapse;\">\n" +
                "            <thead style=\"background-color:#6C9BCF; color: #ffff;\">\n" +
                "                <tr>\n" +
                "                    <th>Sản phẩm</th>\n" +
                "                    <th>Màu sắc</th>\n" +
                "                    <th>Đơn giá</th>\n" +
                "                    <th>Số lượng</th>\n" +
                "                    <th>Tổng tiền</th>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody style=\"background:#f6f6f9\">\n" +
                "            </tbody>\n" +
                             listDetails(order)+
                "            <tfoot>\n" +
                "                <td colspan=\"6\" style=\"font-weight: bold;\">Tổng tiền: "+formatPrice(order.getTotal_price())+"</td>\n" +
                "            </tfoot>\n" +
                "        </table>\n" +
                "        <span style=\"font-weight: bold;\">Trạng thái đơn hàng: Chờ xác thực</span>\n" +
                "        <p style=\"font-weight: bold;\">Cảm ơn anh/chị đã mua hàng của chúng !!!</p>\n" +
                "    </div>";
    }
    @PostMapping(value = "/payment")
    @ResponseBody
    public ResponseEntity<ResponseObject> createNewOrder(@RequestBody List<CartRequest> reqs){
        try {
            Order order = orderService.createOrder();
            Account account = (Account) HttpRequest.getSession().getAttribute("account");
            order.setUser_id(account.getId());
            List details = new ArrayList<>();
            reqs.forEach(e ->
                    {
                        OrderDetail tmp = new OrderDetail();
                        tmp.setOrder_id(order.getId());
                        tmp.setProduct_id(e.getProductID());
                        tmp.setProduct(ps.findById(e.getProductID()).get());
                        tmp.setQty(e.getQty());
                        orderService.addDetail(
                                order,
                                tmp
                                );
                    }
            );
            cs.deleteCart(reqs,account);
            mailSender.sendHTMLEmail(
                    account.getEmail(),
                    "Xác nhận đơn hàng",
                    mailHtmlBody(order)
            );
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Thành công","Đơn hàng đặt thành công",""));
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Thất bại", ex.getMessage(),""));
        }
    }
}
