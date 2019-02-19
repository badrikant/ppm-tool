package io.tool.full.stack.ppmtoolfullstack.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.tool.full.stack.ppmtoolfullstack.domain.User;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class JwtTokenProvider {

    //Generate the token
    public String generateToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        String userid = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userid);
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());

        return Jwts.builder()
                .setSubject(userid)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    // Validate the Token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT Signature", ex);
        } catch (MalformedJwtException ex) {
            log.error("Invalid Jwt Token", ex);
        } catch (ExpiredJwtException ex) {
            log.error("Expired Jwt Token", ex);
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported jwt token", ex);
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty", ex);
        }
        return false;
    }

    // Get userid from token
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String) claims.get("id");
        return Long.parseLong(id);
    }
}
