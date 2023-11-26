package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "admin/categories")
@PreAuthorize("hasRole('ADMIN')")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllCategory(ModelMap modelMap,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id"));
        Page<Category> categoryPage = categoryService.getAllCategoriesWhereDeleteAtIsNull(pageable);
        if (!categoryPage.hasContent()) {
            Pageable previousPageable = pageable.previousOrFirst();
            Page<Category> previousPage = categoryService.getAllCategoriesWhereDeleteAtIsNull(previousPageable);
            if (previousPage.hasContent()) {
                return "redirect:/admin/categories?page=" + previousPageable.getPageNumber();
            }
        }
        modelMap.addAttribute("categoryPage",categoryPage);
        return "adminCategory";
    }
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<?> addCategory (@RequestBody Category category
    ){
        try {
            Category category1 = new Category(category.getName());
            categoryService.addCategory(category1);
            return new ResponseEntity<>("Thêm thể loại thành công", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi không thêm được thể loại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/edit/{id}")
    @ResponseBody
    public ResponseEntity<?> edit(@RequestBody Category category,
                                  @PathVariable Integer id){
        try{
            categoryService.updateCategory(id,category);
            return new ResponseEntity<>("Cập nhật thể loại thành công", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi không cập nhật được thể loại", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping("/delete-categories/")
    @ResponseBody
    public ResponseEntity<String> deleteIds(@RequestBody String[] ids) {
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                try {
                    int id = Integer.parseInt(ids[i]);
                    categoryService.DeletebyId(id);
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi: Chuỗi không phải là số - " + ids[i]);
                }
            }
        }
        return ResponseEntity.ok("Xóa thành công");
    }
    @DeleteMapping("/delete-categories/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteId(@PathVariable Integer id) {
        categoryService.DeletebyId(id);
        return ResponseEntity.ok("Xóa thành công");
    }
    @GetMapping("/search")
    public String FilterCategory(ModelMap modelMap,
                                 @RequestParam(value = "nameFilter",defaultValue = "") String name,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("id"));
        Page<Category> categoryPage = categoryService.filter(pageable,name);
        modelMap.addAttribute("categoryPage",categoryPage);
        return "adminCategory";
    }
}

