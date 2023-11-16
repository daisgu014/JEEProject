package com.JEEProject.TableStore.Auth.auth;

import com.JEEProject.TableStore.Auth.user.User;
import com.JEEProject.TableStore.Auth.user.UserAuthRepository;
import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.Model.ResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserAuthRepository repository;
    private final UserAuthService userAuthService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        if(service.findByPhone(request.getPhone())!=null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new AuthenticationResponse());
        }
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        if(repository.findByUsername(request.getUsername()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new AuthenticationResponse("","")
            );

        }else {
            return ResponseEntity.ok(service.authenticate(request));
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @PostMapping("/getUser")
    @ResponseBody
    public ResponseEntity<ResponseObject> getUser(){
        User user = userAuthService.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Thành công","Lấy user hiện tại",user.getRole())
        );
    }

}
