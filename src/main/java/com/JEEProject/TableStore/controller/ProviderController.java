package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Provider;
import com.JEEProject.TableStore.repositories.ProductRepository;
import com.JEEProject.TableStore.repositories.ProviderRepository;
import com.JEEProject.TableStore.services.ProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping(path = "admin/provider")
public class ProviderController {


    @Autowired
    private ProviderService providerService;
    @RequestMapping(method = RequestMethod.GET)
    public String getAllProviders(ModelMap modelMap){
        Iterable<Provider> providers = providerService.getAll();
        modelMap.addAttribute("providers",providers);
        return "adminProvider";
    }
    @RequestMapping(value = "/add-provider",method = RequestMethod.POST)
    @ResponseBody
    public String addProvider(@RequestParam("data") String data) {
        try{
            Provider provider = new Provider();
            provider.setName(data);
            provider.setCreateAt(new Date());
            if (providerService.getProviderByName(data).isEmpty()){
                providerService.create(provider);
                return "success";
            }else {
                return "Nhà cung cấp đã tồn tại!";
            }
        }catch (Exception e){
            return "Thêm thất bại!";
        }

    }
    @RequestMapping(value = "/update-provider",method = RequestMethod.POST)
    @ResponseBody
    public String updateProvider(@RequestBody Provider data) {
        try{
            if (providerService.getProviderByName(data.getName()).isEmpty()){
                providerService.update(data);
                return "success";
            }else {
                return "Nhà cung cấp đã tồn tại!";
            }
        }catch (Exception e){
            return "Chỉnh sửa thất bại!";
        }
    }
    @RequestMapping(value = "/delete-provider",method = RequestMethod.POST)
    @ResponseBody
    public String deleteProvider(@RequestBody Provider data) {
        try{
            data.setDeleteAt(new Date());
            providerService.delete(data);
            return "success";
        }catch (Exception e){
            return "Thất bại!";
        }
    }
    @RequestMapping(value = "/deleteall-provider", method = RequestMethod.POST)
    @ResponseBody
    public  String deleteAllProvider(@RequestBody List<Provider> data){

        try{
            for (Provider provider : data){
                provider.setDeleteAt(new Date());
                providerService.delete(provider);
            }
            return "success";
        }catch (Exception e){
            return e.toString();
        }
    }
}
