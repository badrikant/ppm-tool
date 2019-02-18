package io.tool.full.stack.ppmtoolfullstack.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author badrikant.soni on 18/02/19
 */
@Data
public class LoginRequest {

    @NotBlank(message = "Username can't be blank")
    private String username;
    @NotBlank(message = "Password can't be blank")
    private String password;
}
