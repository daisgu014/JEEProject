package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.ResponseObject;
import com.JEEProject.TableStore.services.ProductService;
import jakarta.xml.ws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/v1/product")
@PreAuthorize("hasRole('ADMIN')")
public class RestProductAPI {
    private final ProductService productService;

    @GetMapping(path = "getAll")
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
}
