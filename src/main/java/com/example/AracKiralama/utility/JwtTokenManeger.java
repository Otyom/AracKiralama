package com.example.AracKiralama.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.AracKiralama.entity.Person;
import com.example.AracKiralama.entity.enums.Role;
import com.example.AracKiralama.exception.persons.InvaildToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManeger {


    @Value("${tokenAlgorithm.secret-key}")
    String tokenCode;

    public Optional<String> createToken(Long id, Role role){
        String token="";

        Long expiresTime=1000*60*60l;
        try {
            token= JWT.create()
                    .withClaim("id",id)
                    .withClaim("email","notyom.123@gmail.com")
                    .withClaim("role", role.ordinal())
                    .withIssuer("Otyom")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+expiresTime))
                    .sign(Algorithm.HMAC512(tokenCode));
            return Optional.of(token);
        }catch (Exception e){
            return Optional.of(null);
        }
    }



    public Optional<Long> getIdByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(tokenCode);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("Otyom").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            if (decodedJWT==null){
                return Optional.empty();
            }

            Optional<Long> id= Optional.of(decodedJWT.getClaim("id").asLong());
            return id;

        }catch (Exception e){
            throw new  InvaildToken();
        }

    }
}
