package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private MailSenderService mailSender;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderRepository orderRepository;

    @Value("${default.role:USER}")
    private String role;

    public void addNewAccount(Account account) {
        account.setRole(role);
        accountRepository.save(account);
    }

    public boolean findEmail(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findPhone(String phone) {
        Account account = accountRepository.findByPhone(phone);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    public Account getAccountByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public void sendEmailUserPassword(Account account, String newPassword){
        mailSender.sendHTMLEmail(
                account.getEmail(),
                "Thay đổi mật khẩu cho tài khoản: " + account.getUsername(),
                      "<p style='font-size: 16px'>Xin chào!</p>" + account.getFullname() + "</h1>" +
                            "<p style='font-size: 16px'>Cữa hàng của chúng tôi vừa nhận được yêu cầu thay đổi mật khẩu của bạn, đây sẽ là mật khẩu mới của bạn: </p>" +
                              "<p style='text-align: center; font-size: 18px; font-weight: bold;'>" + newPassword + "</p><br>" +
                            "<p style='font-size: 16px'>Xin hãy đăng nhập tài khoản và thay đổi lại thông tin của bạn.</p><br>" +
                            "<p style='font-size: 16px'>Trân trọng! (^-^)</p>"
        );
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
            if (checkEmptyEmail(account) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean checkUpdatePhone(Account account, String phone){
        if (!phone.equals(account.getPhone())){
            account.setPhone(phone);
            if (!checkEmptyPhone(account)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAccount (String username){
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    public Account getAccountByUsername (String username){
        return accountRepository.findByUsername(username);
    }

    public String randomPassword (){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    public void updateAccount(Account account){
        accountRepository.save(account);
    }

    public List<Order>getAllUserOrder(int user_id){
            return orderRepository.findByUserId(user_id);
    }
}
