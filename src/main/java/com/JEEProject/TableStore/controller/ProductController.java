package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.Model.Provider;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import com.JEEProject.TableStore.repositories.ProductRepository;
import com.JEEProject.TableStore.repositories.ProviderRepository;
import com.JEEProject.TableStore.services.CategoryService;
import com.JEEProject.TableStore.services.ProductService;
import com.JEEProject.TableStore.services.ProviderService;
import com.JEEProject.TableStore.validation.ProductValidator;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping(path = "admin/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
       @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProviderService providerService;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ServletContext servletContext;
    @RequestMapping( value = "" ,method = RequestMethod.GET)
    public String getProduct(ModelMap modelMap,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Product> productPage = productService.getAllWhereDeleteAtIsNull(pageable);
        modelMap.addAttribute("controller","products");
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("categories",categoryService.getAll());
        modelMap.addAttribute("providers",providerService.getAll());
        Iterable<Product> products = productRepository.findAll();
        modelMap.addAttribute("productPage",productPage);
        modelMap.addAttribute("product", new Product());
        return"adminProduct";
    }
    public String upLoadImage(MultipartFile img,String name, String color){
        String uploadDir = servletContext.getRealPath("/WEB-INF/images/products/");
        List<String> allowedExtensions = Arrays.asList(".png",".jpeg",".jpg");
        if(!img.isEmpty()){
            try {
                String originalFileName = img.getOriginalFilename().toLowerCase();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                if(allowedExtensions.contains(fileExtension)){
                    String filePath = uploadDir +name+"_"+color+fileExtension;
                    String filePath2 = name+"_"+color+fileExtension;
                    File uploadPath = new File(uploadDir);
                    if (!uploadPath.exists()) {
                        uploadPath.mkdirs();
                    }
                    File imageFile = new File(filePath);
                    img.transferTo(imageFile);
                    return filePath2;
                }else{
                    return "Chỉ nhận các file PNG, JPEG và JPG";
                }

            }catch (Exception e){
                return e.toString();
            }
        }else {
            return "Vui lòng chọn ảnh";
        }
    }
    Provider providerById(Integer id){
        if(providerRepository.findById(id).isPresent()){
            return providerRepository.findById(id).get();
        }
        return null;
    }
    @PostMapping(value = "/create-product/")
    public ResponseEntity<?> addProduct (@RequestParam("name") String name,
                                         @RequestParam("color") String color,
                                         @RequestParam("price") Integer price,
                                         @RequestParam("image") MultipartFile file,
                                         @RequestParam("status") String status,
                                         @RequestParam("category") Integer categoryId,
                                         @RequestParam("provider") Integer providerId
                                         ){
          try{
              Category category= categoryService.findByIds(categoryId);
              Provider provider =providerById(providerId);
              Product product = new Product(name,
                      color,
                      status,
                      upLoadImage(file,name,color),
                      price,category,
                      provider);
              System.err.println(
                      product.getName()+"\n"+
                              product.getColor()+"\n"+
                              product.getStatus()+"\n"+
                              product.getImgPath()+"\n"+
                              product.getPrice()+"\n"+
                              product.getInStock()+"\n"+
                              product.getCategory().getName()+"\n"+
                              product.getProvider().getName()
              );
              productService.addProduct(product);

              return new ResponseEntity<>("Thêm sản phẩm thành công", HttpStatus.CREATED);

          }catch (Exception e){
              return new ResponseEntity<>("Lỗi không thêm được sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
    @PostMapping(value = "/edit/{id}")
    @ResponseBody
    public ResponseEntity<?> editProduct (@RequestBody  Product product,
                                          @PathVariable Integer id
    ){
        try {
            System.err.println("Category: "+product.getName()+product.getPrice());
            return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/delete-products/")
    @ResponseBody
    public ResponseEntity<String> deleteIds(@RequestBody String[] ids) {
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                try {
                    int id = Integer.parseInt(ids[i]);
                    productRepository.deleteProductUpDelete(id);
                } catch (NumberFormatException e) {
                    // Xử lý trường hợp chuỗi không phải là số
                    System.err.println("Lỗi: Chuỗi không phải là số - " + ids[i]);
                }
            }
        }
        return ResponseEntity.ok("Xóa thành công");
    }
    @DeleteMapping("/delete-products/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteId(@PathVariable Integer id) {
        productService.DeletebyId(id);
        return ResponseEntity.ok("Xóa thành công");
    }



}
