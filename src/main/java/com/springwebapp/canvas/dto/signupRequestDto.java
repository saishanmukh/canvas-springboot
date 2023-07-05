package com.springwebapp.canvas.dto;

import com.springwebapp.canvas.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class signupRequestDto {
    private String username;
    private String password;
    private Role role;
}
