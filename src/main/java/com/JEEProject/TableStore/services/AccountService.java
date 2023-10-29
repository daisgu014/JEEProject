package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Provider;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.ProviderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public Iterable<Account> getAll(){
        return accountRepository.findAll();
    }
    public Optional<Account> getAccountById(Account account){
        return accountRepository.findById(account.getId());
    }
    public void create(Account account){
        accountRepository.save(account);
    }
    @Transactional
    public void update(Account account){
        accountRepository.update(account);
    }
    public void delete(Account account){
        accountRepository.save(account);
    }

}
