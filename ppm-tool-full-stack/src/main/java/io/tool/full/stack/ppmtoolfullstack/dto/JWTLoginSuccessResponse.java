package io.tool.full.stack.ppmtoolfullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author badrikant.soni on 18/02/19
 */

@Data
@AllArgsConstructor
@ToString
public class JWTLoginSuccessResponse {

    private boolean success;
    private String token;
}
