package com.JEEProject.TableStore.controller;

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

        if (providerService.getProviderByName(data).isEmpty()){
            Provider provider = new Provider();
            provider.setName(data);
            provider.setCreateAt(new Date());
            try{
                providerService.create(provider);
                return "Data added successfully!";
            }catch (Exception e){
                return e.toString();
            }

        }else {
            return "Data added fail!";
        }
    }
    @RequestMapping(value = "/update-provider",method = RequestMethod.POST)
    @ResponseBody
    public String updateProvider(@RequestBody Provider data) {
        Provider provider = new Provider();
        provider.setId(data.getId());
        provider.setName(data.getName());
        providerService.update(provider);
        return "Data added successfully!";
    }

}
