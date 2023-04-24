package com.example.slg_corporation_be.controller;
import com.example.slg_corporation_be.security_authentication.jwt.JwtUtility;
import com.example.slg_corporation_be.security_authentication.payload.reponse.JwtResponse;
import com.example.slg_corporation_be.security_authentication.payload.request.LoginRequest;
import com.example.slg_corporation_be.security_authentication.service.AccountDetails;
import com.example.slg_corporation_be.service.IAppUserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public")
@CrossOrigin
public class SecurityRestController {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAppUserService appUserService;

    /**
     * Created by: HoangNM
     * Date created: 29/03/2023
     * Function: login, authentication
     *
     * @param loginRequest
     * @return HttpStatus.No_Content if result is error or HttpStatus.OK is result is not error
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
        AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getUsername(),
                        appUserService.findByEmail(loginRequest.getUsername()).getName(),
                        roles)
        );
    }
}
