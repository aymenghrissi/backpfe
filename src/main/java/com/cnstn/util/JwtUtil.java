package com.cnstn.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private static final String Secret_Key = "CnstnSecretKeyIsThisKeyoooooooooooooooooooooooooooooooooooooooooooooooooooooooizehfoezhfoeoahfoaihfoiahfoiahfohafhoazfhoazhfohfacjaojcohazoichoiazhcoiccjccjcjcj";
	private static final int TOKEN_VALIDITY = 3600 * 5 ;
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
		
	}
	
	private<T> T getClaimFromToken(String token , Function<Claims,T> claimresolver ) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimresolver.apply(claims) ;
	}
	private Claims getAllClaimsFromToken(String token ) {
		 return Jwts.parser().setSigningKey(Secret_Key).parseClaimsJws(token).getBody();
	}
	
	public Boolean validateToken(String token , UserDetails userDetails) {
		String userName = getUsernameFromToken(token);
		return (userName.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	private Boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date() );
		
	}
	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>() ;
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, Secret_Key )
				.compact()
				;
	}

}
