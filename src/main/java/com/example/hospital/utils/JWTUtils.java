package com.example.hospital.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    public static final String jwtToken="123456@abc";
    public static String createToken(Long id){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",id);
        JwtBuilder jwtBuilder= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,jwtToken)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));
        String token=jwtBuilder.compact();
        return token;
    }

    public static Map<String,Object> checkToken(String token){
        try{
            Jwt parse=Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String,Object>)parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("token异常");
            return null;
        }
    }
}
