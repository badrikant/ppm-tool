package io.tool.full.stack.ppmtoolfullstack.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author badrikant.soni on 16/02/19
 */
@Data
@AllArgsConstructor
public class UsernameAlreadyExistsResponse {

    private String username; // putting "username" make it consist across when calling by UI.
}
