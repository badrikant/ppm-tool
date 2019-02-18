package io.tool.full.stack.ppmtoolfullstack.security;

/**
 * @author badrikant.soni on 18/02/19
 */
public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer "; // space is need after Bearer
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 30_000; // 30 seconds
}
