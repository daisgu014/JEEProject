package com.JEEProject.TableStore.controller;
import com.JEEProject.TableStore.Auth.user.User;
import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.Model.*;
import com.JEEProject.TableStore.repositories.ProviderRepository;
import com.JEEProject.TableStore.services.*;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "admin/products")
@PreAuthorize("hasRole('ADMIN')")
public class ProductController {
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
    private final UserAuthService userAuthService;
    private final ImportHistoryService importHistoryService;
    private final ImportDetailsService importDetailsService;

    @RequestMapping( value = "" ,method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('admin:read')")
    public String getProduct(ModelMap modelMap,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Product> productPage = productService.getAllWhereDeleteAtIsNull(pageable);
        if (!productPage.hasContent()) {
            Pageable previousPageable = pageable.previousOrFirst();
            Page<Product> previousPage = productService.getAllWhereDeleteAtIsNull(previousPageable);
            if (previousPage.hasContent()) {
                return "redirect:/admin/products?page=" + previousPageable.getPageNumber();
            }
        }
        Iterable<Category> categories = categoryService.getAll();
        Iterable<Provider> providers = providerService.getAll();
        List<Category> filterCategory = new ArrayList<>();
        filterCategory.add(new Category(-1,"Tất cả"));
        filterCategory.addAll((Collection<? extends Category>)categories);
        List<Provider> providerFilters = new ArrayList<>();
        providerFilters.add(new Provider(-1,"Tất cả"));
        providerFilters.addAll((Collection<? extends Provider>)providers);
        modelMap.addAttribute("categories",categories);
        modelMap.addAttribute("providers",providers);
        modelMap.addAttribute("categoriesFilter",filterCategory);
        modelMap.addAttribute("providerFilters",providerFilters);
        modelMap.addAttribute("productPage",productPage);
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
    @PreAuthorize("hasAnyAuthority('admin:create')")
    @Hidden
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
              productService.addProduct(product);

              return new ResponseEntity<>("Thêm sản phẩm thành công", HttpStatus.CREATED);

          }catch (Exception e){
              return new ResponseEntity<>("Lỗi không thêm được sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
    @PostMapping(value = "/edit/{id}")
    @PreAuthorize("hasAnyAuthority('admin:update')")
    @Hidden
    @ResponseBody
    public ResponseEntity<?> editProduct (@RequestParam("name") String name,
                                          @RequestParam("color") String color,
                                          @RequestParam("price") Integer price,
                                          @RequestParam("image") MultipartFile file,
                                          @RequestParam("status") String status,
                                          @RequestParam("category") Integer categoryId,
                                          @RequestParam("provider") Integer providerId,
                                          @PathVariable Integer id
    ){
        try {
            Product product = new Product();
            if(name!=null && !name.equalsIgnoreCase("")){
                product.setName(name);
            }
            if(color!=null && !color.equalsIgnoreCase("")){
                product.setColor(color);
            }
            if(price!=null && price>0){
                product.setPrice(price);
            }
            if(file!=null){
                product.setImgPath(upLoadImage(file,name,color));
            }
            if(status!=null && !status.equalsIgnoreCase("")){
                product.setStatus(status);
            }
            if(categoryId!=null){
                Category category= categoryService.findByIds(categoryId);
                product.setCategory(category);
            }
            if(providerId!=null){
                Provider provider =providerById(providerId);
                product.setProvider(provider);
            }
            if(!productService.updateProduct(id,product)){
                return new ResponseEntity<>("Lỗi chỉnh sửa sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>("Sản phẩm đã được chỉnh sửa thành công", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi chỉnh sửa sản phẩm", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/delete-products/")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    @ResponseBody
    public ResponseEntity<String> deleteIds(@RequestBody String[] ids) {
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                try {
                    int id = Integer.parseInt(ids[i]);
                    productService.DeletebyId(id);
                } catch (NumberFormatException e) {
                    // Xử lý trường hợp chuỗi không phải là số
                    System.err.println("Lỗi: Chuỗi không phải là số - " + ids[i]);
                }
            }
        }
        return ResponseEntity.ok("Xóa thành công");
    }
    @DeleteMapping("/delete-products/{id}")
    @PreAuthorize("hasAnyAuthority('admin:delete')")
    @ResponseBody
    public ResponseEntity<String> deleteId(@PathVariable Integer id) {
        productService.DeletebyId(id);
        return ResponseEntity.ok("Xóa thành công");
    }

    @PostMapping("/add-qty/{id}")
    @ResponseBody
    public ResponseEntity<ResponseObject> addQty(@RequestBody String qty,
                                                 @PathVariable Integer id){
        System.err.println("qty:"+qty);

        Integer qtyValue = Integer.parseInt(qty.replaceAll("\"", ""));
        Product product;
       if(productService.findById(id).isPresent()){
           product = productService.findById(id).get();
       }else{
           return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                   new ResponseObject("failed","Sản phẩm không tìm thấy","")
           );
       }
        User user = userAuthService.getUser();
        ImportHistory importHistory= importHistoryService.getImportHistory(user);
       importDetailsService.saveImportDetail(importHistory,product,qtyValue);
       return ResponseEntity.status(HttpStatus.OK).body(
               new ResponseObject("ok","nhập sản phẩm "+product.getName()+" với số lượng: "+qtyValue,product)
       );
    }
    @GetMapping("/search")
    public String search(ModelMap modelMap,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "100") int size,
                         @RequestParam(value = "nameFilter",defaultValue = "") String name,
                         @RequestParam(value = "min-price",defaultValue = "0") String minPrice,
                         @RequestParam(value = "max-price",defaultValue = "999999999") String maxPrice,
                         @RequestParam("category") Integer categoryId,
                         @RequestParam("status") String status,
                         @RequestParam("provider") Integer providerId){
        Pageable pageable = PageRequest.of(page,size, Sort.by("id"));
        // Kiểm tra và chuyển đổi minPrice
        Integer minValue = Integer.parseInt(minPrice);

        // Kiểm tra và chuyển đổi maxPrice
        Integer maxValue = Integer.parseInt(maxPrice);
        Page<Product> productPage = productService.filter(pageable,name,minValue,maxValue,categoryId,providerId,status);
        Iterable<Category> categories = categoryService.getAll();
        Iterable<Provider> providers = providerService.getAll();
        List<Category> filterCategory = new ArrayList<>();
        filterCategory.add(new Category(-1,"Tất cả"));
        filterCategory.addAll((Collection<? extends Category>)categories);
        List<Provider> providerFilters = new ArrayList<>();
        providerFilters.add(new Provider(-1,"Tất cả"));
        providerFilters.addAll((Collection<? extends Provider>)providers);
        modelMap.addAttribute("categories",categories);
        modelMap.addAttribute("providers",providers);
        modelMap.addAttribute("categoriesFilter",filterCategory);
        modelMap.addAttribute("providerFilters",providerFilters);
        modelMap.addAttribute("productPage",productPage);
        return "adminProduct";
    }
    @GetMapping(value = "/importProducts")
    public String getImportProducts(ModelMap modelMap){
        User user = userAuthService.getUser();
        Iterable<Product> products = productService.getAll();
        modelMap.addAttribute("products",products);
        modelMap.addAttribute("user",user);
        return "AdminImportProduct";
    }

}
