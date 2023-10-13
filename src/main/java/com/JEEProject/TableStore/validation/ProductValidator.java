package com.JEEProject.TableStore.validation;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.services.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    private ProductService productService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        if(productService.isProductNameAlreadyInUse(product.getName())){
            errors.rejectValue("name",null,"Tên sản phẩm dã tồn tại. Vui lòng chọn tên khác");
        }
    }
}
