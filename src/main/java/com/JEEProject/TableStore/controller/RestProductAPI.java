package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Auth.user.User;
import com.JEEProject.TableStore.Auth.user.UserAuthRepository;
import com.JEEProject.TableStore.Model.ImportHistory;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.Model.ProductQtyRequest;
import com.JEEProject.TableStore.Model.ResponseObject;
import com.JEEProject.TableStore.config.JwtService;
import com.JEEProject.TableStore.services.ImportDetailsService;
import com.JEEProject.TableStore.services.ImportHistoryService;
import com.JEEProject.TableStore.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.ws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/product")
@PreAuthorize("hasRole('ADMIN')")
public class RestProductAPI {
    private final ImportHistoryService importHistoryService;
    private final ImportDetailsService importDetailsService;
    private final ProductService productService;
    private final JwtService jwtService;
    private final HttpServletRequest HttpRequest;
    private final UserAuthRepository userRepository;

    @GetMapping(value = "getAll")
    @ResponseBody
    public ResponseEntity<ResponseObject> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Thành công","Danh sách sản phẩm",productService.getAll())
        );
    }
    @PostMapping(path ="getProduct/{id}")
    @ResponseBody
    public ResponseEntity<ResponseObject> getProduct(@PathVariable("id") Integer id){
        if(productService.findById(id).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Thành công","Sản phẩm với id: "+id,productService.findById(id).get())
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Thất bại","Không tìm thấy sản phẩm: "+id,"")
            );
        }
    }
    //saveProduct
    @PostMapping(path = "saveProductQty")
    @ResponseBody
    public ResponseEntity<ResponseObject> saveProductsQty(@RequestBody List<ProductQtyRequest> list){
        HttpSession session = HttpRequest.getSession();
        HashMap<Integer, Integer> productQtys = new HashMap<>();
        list.forEach(productQtyRequest -> {
            if(productService.findById(productQtyRequest.getId()).isPresent()){
                productQtys.put(productQtyRequest.getId(), productQtyRequest.getQty());
            }
        });
        String name = jwtService.extractUsername((String) session.getAttribute("accessToken"));
        User user = userRepository.findByUsername(name).get();
        ImportHistory importHistory= importHistoryService.getImportHistory(user);
        importDetailsService.saveImportDetails(importHistory,productQtys);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Thành công","Thêm sản phẩm với user: "+user.getFull_name(),"")
        );
    }
}
