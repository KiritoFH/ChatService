package com.rehome.chat.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UsersController {

//    @GetMapping("/registration/{userName}")
//    public ResponseEntity<Void> register(@PathVariable String userName) {
//        System.out.println("handling register user request: " + userName);
//        try {
//            UserStorage.getInstance().setUser(userName);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/fetchAllUsers")
//    public Set<String> fetchAll() {
//        return UserStorage.getInstance().getUsers();
//    }
}
