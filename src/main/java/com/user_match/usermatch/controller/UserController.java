package com.user_match.usermatch.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_match.usermatch.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("v1/user/")
public class UserController {
    private UserRepository userRepository;

    @PostMapping("set-up-profile")
    @CrossOrigin(origins = {"https://127.0.0.1:5173"})
    public ResponseEntity<Map> profile(@RequestBody Map<String, String> request){

        return (ResponseEntity<Map>) ResponseEntity.status(200);
    }
}
