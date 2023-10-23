package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Provider;
import com.JEEProject.TableStore.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    public Iterable<Provider> getAll(){
        return providerRepository.findAll();
    }
    public void create(Provider provider){
        providerRepository.save(provider);
    }
}
