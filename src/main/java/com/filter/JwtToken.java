package com.filter;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

public class JwtToken {
	
	private String token;
	private static final String SECRETKEY="##XXXAACCCRR#123###AAA";
	public JwtToken()
	{
		
	}
	public void generateToken(String userName,String password,String role)
	{ 
		this.token=Jwts.builder()
				   .claim("username",userName)
				   .claim("passowrd", password)
				   .claim("role",role)
				   .signWith(SignatureAlgorithm.HS256,SECRETKEY)
				   .compact();
	}
	public String getToken()
	{ 
		return this.token;
	}
	public String getSecretKey() 
	{
		return SECRETKEY;
	}
	
	public boolean validate(String token) 
	{
		  try {
		Jws<Claims> claims=Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token);
	  return true;
		  }
		  catch(JwtException e)
		  {
			   return false;
		  }
	}

}
