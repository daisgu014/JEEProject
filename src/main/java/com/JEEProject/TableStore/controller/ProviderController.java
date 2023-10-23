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
    @RequestMapping(value = "/add-data",method = RequestMethod.POST)
    @ResponseBody
    public String addData(@RequestParam("data") String data) {
        return "Data added successfully!";
    }

    /*@GetMapping("/provider-create")
    public String createProvider(ModelMap modelMap){
        modelMap.addAttribute("provider",new Provider());
        return "adminProvider";
    }*/
    /*@PostMapping("/provider-create")
    @ResponseBody
    public String createProviders(@RequestParam("data") String data
                                 ){
            Provider provider = new Provider();
            provider.setName(data);
            providerService.create(provider);
            return "redirect:/provider";
        return "Success";
    }*/
}
