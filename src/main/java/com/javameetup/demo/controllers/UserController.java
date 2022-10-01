package com.javameetup.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private ClientRegistration registration;


    public UserController(ClientRegistrationRepository registrations) {
        this.registration = registrations.findByRegistrationId("okta");
    }

    @GetMapping("/api/user")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal OAuth2User user){
        if(null == user){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return ResponseEntity.ok().body(user.getAttributes());
    }

    @PostMapping("/api/logout")
    public ResponseEntity<?> logOutUser(HttpServletRequest request,
                                        @AuthenticationPrincipal(expression = "idToken") OidcIdToken idToken){
        //Client needs logOut url in order to initiate log out
        String logOutUrl = this.registration.getProviderDetails()
                                .getConfigurationMetadata().get("end_session_endpoint").toString();

        Map<String, String> logoutDetails = new HashMap<>();

        logoutDetails.put("logoutUrl", logOutUrl);
        logoutDetails.put("idToken", idToken.getTokenValue());
        request.getSession(false).invalidate();

        return ResponseEntity.ok().body(logoutDetails);
    }
}
