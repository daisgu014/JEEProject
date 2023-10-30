package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.Model.Provider;
import com.JEEProject.TableStore.repositories.ProviderRepository;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {
    @Autowired
    private ProviderRepository providerRepository;
    public Iterable<Provider> getAll(){
        return providerRepository.findAll();
    }
    public List<Provider> getProviderByName(String name){
        return providerRepository.findByName(name);
    }
    public void create(Provider provider){
        providerRepository.save(provider);
    }
    @Transactional
    public void update(Provider provider){
        providerRepository.update(provider);
    }
    @Transactional
    public void delete(Provider provider){
        providerRepository.delete(provider);
    }
}
