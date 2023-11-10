package com.JEEProject.TableStore.Auth.auth;

import com.JEEProject.TableStore.Auth.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String full_name;
    private String password;
    private String phone;
    private String email;
    private Role role;
    private String address;
}
