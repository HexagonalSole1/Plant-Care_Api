package com.example.APISkeleton.utils;

import com.example.APISkeleton.types.JWTType;

import java.util.Map;

public interface IJWTUtils {
    String generateToken(String email, Map<String, Object> payload, JWTType tokenType);
    String getSubjectFromToken(String token);
    Map<String, Object> getClaimsFromToken(String token);
    Boolean isTokenValid(String token, JWTType tokenType);
}
