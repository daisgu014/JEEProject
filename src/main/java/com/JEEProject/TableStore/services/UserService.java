package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    AccountRepository accountRepository;

    @Value("${default.role:customer}")
    private String role;

    public void addNewAccount(Account account) {
        account.setRole(role);
        accountRepository.save(account);
    }

    public boolean checkEmptyUsername(Account account){
        Account username = accountRepository.findByUsername(account.getUsername());
        if (username != null){return false;}
        else {return true;}
    }

    public boolean checkEmptyEmail(Account account){
        Account email = accountRepository.findByEmail(account.getEmail());
        if (email != null){return false;}
        else {return true;}
    }

    public boolean checkEmptyPhone(Account account){
        Account phone= accountRepository.findByPhone(account.getPhone());
        if (phone != null){return false;}
        else {return true;}
    }

    public boolean checkAccount (String username, String password){
        Account account = accountRepository.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
