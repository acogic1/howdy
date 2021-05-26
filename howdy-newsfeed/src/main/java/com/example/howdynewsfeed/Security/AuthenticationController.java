package com.example.howdynewsfeed.Security;

import com.example.howdynewsfeed.models.User;
import com.example.howdynewsfeed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private com.example.howdynewsfeed.Security.MyUserDetailsService userDetailsService;

    @Autowired
    private com.example.howdynewsfeed.Security.JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestBody com.example.howdynewsfeed.Security.AuthenticationRQ request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new com.example.howdynewsfeed.Security.AuthenticationRS(token));
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody com.example.howdynewsfeed.Security.ValidationRQ validationRQ) throws Exception {

        UserDetails userDetails = userDetailsService.loadUserByUsername(validationRQ.getUsername());

        if(!jwtUtil.validateToken(validationRQ.getToken(), userDetails)) {
            throw new BadCredentialsException("Incorrect username or password") ;
        }

        User profile = userRepository.findByUsername(validationRQ.getUsername());
        return ResponseEntity.ok(new com.example.howdynewsfeed.Security.ValidationRS(userDetails.getUsername(), userDetails.getPassword(), profile.getUsername()));
    }
}

