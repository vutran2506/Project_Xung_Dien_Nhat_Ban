package com.example.slg_corporation_be.security_authentication.payload.reponse;

import java.util.List;


public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;

    private String name;
    private List<String> roles;

    public JwtResponse(String token, String username, String name, List<String> roles) {
        this.token = token;
        this.username = username;
        this.name = name;
        this.roles = roles;
    }

    public JwtResponse() {
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
