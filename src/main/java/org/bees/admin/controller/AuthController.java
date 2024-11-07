package org.bees.admin.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.bees.admin.controller.response.TokenResponse;
import org.bees.admin.dto.AuthRequest;
import org.bees.admin.dto.UserDto;
import org.bees.admin.filter.BlackList;
import org.bees.admin.service.CustomUserDetailsService;
import org.bees.admin.service.JwtService;
import org.bees.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Authentication", description = "Auth management api")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BlackList blackList;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security tutorials !!";
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody UserDto userDto){
            return userService.signup(userDto);
    }

    @PostMapping("/generate/token")
    public TokenResponse login(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return new TokenResponse(jwtService.generateToken(authRequest.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token= null;
        if(authHeader !=null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
        }
        blackList.blacKListToken(token);
        return "You have successfully logged out !!";
    }
}
