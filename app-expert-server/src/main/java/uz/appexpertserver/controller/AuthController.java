package uz.appexpertserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.appexpertserver.peyload.ReqUser;
import uz.appexpertserver.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @PostMapping("/register")
    public HttpEntity<?> addUser(@Valid  @RequestBody ReqUser reqUser){
        return ResponseEntity.ok(authService.register(reqUser));
    }
}
