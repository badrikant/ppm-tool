package io.tool.full.stack.ppmtoolfullstack.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.tool.full.stack.ppmtoolfullstack.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.tool.full.stack.ppmtoolfullstack.security.SecurityConstants.EXPIRATION_TIME;
import static io.tool.full.stack.ppmtoolfullstack.security.SecurityConstants.SECRET;

/**
 * @author badrikant.soni on 18/02/19
 */

@Component
public class JwtTokenProvider {

    //Generate the token
    public String generateToken(Authentication authentication){

        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        String userid = Long.toString(user.getId());

        Map<String,Object> claims  = new HashMap<>();
        claims.put("id",userid);
        claims.put("username",user.getUsername());
        claims.put("password",user.getPassword());

        return Jwts.builder()
                .setSubject(userid)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    // Validate the Token

    // Get userid from token
}
