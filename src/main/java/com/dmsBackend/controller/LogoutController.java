package com.dmsBackend.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/logout")
public class LogoutController {
    @GetMapping
    public ResponseEntity<?> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
