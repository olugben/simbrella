package com.hammed.auth;

import com.hammed.UserManagement.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
private String firstname;
private String lastname;
private String email;
private String password;
private Role role;
private String phone_number;

}



