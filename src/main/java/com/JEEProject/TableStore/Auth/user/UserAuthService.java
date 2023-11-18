package com.JEEProject.TableStore.Auth.user;


import com.JEEProject.TableStore.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserAuthRepository repository;
    private final JwtService jwtService;
    private final HttpServletRequest HttpRequest;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }
    public User findByPhone(String phone){
        if(repository.findByPhone(phone).isPresent()){
            return repository.findByPhone(phone).get();
        }
        return null;
    }
    public User getUser(){
        HttpSession session = HttpRequest.getSession();
        String name = jwtService.extractUsername((String) session.getAttribute("accessToken"));
        User user = repository.findByUsername(name).get();
        return user;
    }
}
