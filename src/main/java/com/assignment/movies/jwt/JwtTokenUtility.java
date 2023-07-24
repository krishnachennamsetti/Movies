package com.assignment.movies.jwt;

import com.assignment.movies.exception.MoviesException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtility {

    @Value("${jwt.secret:'movies_jwt_secret'}")
    private String secret;

    @Value("${jwt.expiry:18000000}")
    private long tokenExpiry;

    /**
     * This method is used to generate the Json Web Token for API authentication
     * @param username username of the user accessing the APIs
     * @return JWT token
     * @throws MoviesException
     */
    public String generateToken(String username) throws MoviesException {

        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        Claims claims = Jwts.claims().setSubject(username);
        Date issueDate = new Date();
        Date expiryDate = new Date(issueDate.getTime() + tokenExpiry);
        return Jwts.builder().setClaims(claims).setIssuedAt(issueDate).setExpiration(expiryDate)
                .signWith(algorithm, secret).compact();

    }

    /**
     * This method is used to fetch username from token
     * @param token JWT token
     * @return Claims from the JWT token
     * @throws MoviesException
     */
    public String getUsernameFromToken(String token) throws MoviesException {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * This method is used to fetch expire date from token
     * @param token JWT token
     * @return Expiry date for JWT
     * @throws MoviesException
     */
    public Date getTokenExpiry(String token) throws MoviesException {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * This method is used to load specific claim from token
     * @param <T>
     * @param token JWT token
     * @param claimsResolver claims
     * @return Claim from JWT
     * @throws MoviesException
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws MoviesException{
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * This method is used to fetch all token claims
     * @param token
     * @return token claims
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * This method is used to check if JWT expired or not
     * @param token
     * @return token expired or not
     * @throws MoviesException
     */
    public boolean isTokenExpired(String token) throws MoviesException {
        final Date expiration = getTokenExpiry(token);
        return expiration.before(new Date());
    }

}

