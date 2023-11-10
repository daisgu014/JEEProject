package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderRepository orderRepository;

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

    public boolean checkUpdateEmail(Account account, String email){
        if (!email.equals(account.getEmail())){
            account.setEmail(email);
            if (!checkEmptyEmail(account)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkUpdatePhone(Account account, String phone){
        if (!phone.equals(account.getPhone())){
            account.setEmail(phone);
            if (!checkEmptyPhone(account)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAccount (String username, String password){
        Account account = accountRepository.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public Account getAccountByUsername (String username){
        return accountRepository.findByUsername(username);
    }

    public void updateInformation(Account account,String fullname, String address){
        account.setFullname(fullname);
        account.setAddress(address);
        accountRepository.save(account);
    }

    public boolean updatePassword(Account account, String password, String newPassword){
        if (!password.equals(account.getPassword())){
            return false;
        }
        account.setPassword(newPassword);
        accountRepository.save(account);
        return true;
    }

    public List<Order>getAllUserOrder(int user_id){
        return orderRepository.findByUserId(user_id);
    }
}
