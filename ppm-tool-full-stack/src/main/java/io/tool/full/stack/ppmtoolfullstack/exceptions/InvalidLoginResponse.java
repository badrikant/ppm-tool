package io.tool.full.stack.ppmtoolfullstack.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author badrikant.soni on 16/02/19
 */
@Getter
@Setter
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalid Username";
        this.password = "Invalid Password";
    }
}
