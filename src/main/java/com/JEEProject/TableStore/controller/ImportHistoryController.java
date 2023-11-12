package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.ImportDetails;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.services.ImportDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "admin/products/import-history")
public class ImportHistoryController {
    private final ImportDetailsService importDetailsService;
    @RequestMapping(value = "", method = RequestMethod.GET)
     public String getImportHistory(ModelMap modelMap,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "6") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<ImportDetails> importDetails = importDetailsService.getImport(pageable);
        if (!importDetails.hasContent()) {
            Pageable previousPageable = pageable.previousOrFirst();
            Page<ImportDetails> previousPage = importDetailsService.getImport(previousPageable);
            if (previousPage.hasContent()) {
                return "redirect:/admin/products/import-history?page=" + previousPageable.getPageNumber();
            }
        }
        modelMap.addAttribute("imports",importDetails);
         return "importHistory";
     }
}
