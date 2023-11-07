package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Provider;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.ProviderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

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
    public Account getAccountByName(Account account){
        return accountRepository.findByUsername(account.getUsername());
    }
    public Account getAccountByPhone(Account account){
        return accountRepository.findByPhone(account.getPhone());
    }
    public void create(Account account){
        accountRepository.save(account);
    }

    public void update(Account account){
        Account existAccount = accountRepository.findById(account.getId()).orElse(null);
        if (existAccount != null){
            Date currentCreateAt = existAccount.getCreateAt();
            Date currentDeleteAt = existAccount.getDeleteAt();
            account.setCreateAt(currentCreateAt);
            account.setDeleteAt(currentDeleteAt);
            accountRepository.save(account);
        }
    }
    public void delete(Account account){
        Account existAccount = accountRepository.findById(account.getId()).orElse(null);
        if (existAccount != null){
            existAccount.setDeleteAt(account.getDeleteAt());
            accountRepository.save(existAccount);
        }
    }

}
