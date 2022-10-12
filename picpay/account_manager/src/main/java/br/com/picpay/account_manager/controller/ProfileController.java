package br.com.picpay.account_manager.controller;

import br.com.picpay.account_manager.model.dto.CreateProfileDTO;
import br.com.picpay.account_manager.model.dto.ResponseProfileDTO;
import br.com.picpay.account_manager.model.dto.TransactionDTO;
import br.com.picpay.account_manager.model.dto.UpdateProfileDTO;
import br.com.picpay.account_manager.service.ProfileService;
import br.com.picpay.account_manager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    ProfileService profileService;

    @Autowired
    ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    private ResponseEntity getProfile(@PathVariable Long id) {
        ResponseProfileDTO response = profileService.getProfile(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    private ResponseEntity createProfile(@RequestBody CreateProfileDTO request) {
        ResponseProfileDTO response = profileService.createProfile(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    private ResponseEntity updateProfile(@PathVariable Long id, @RequestBody UpdateProfileDTO request) {
        ResponseProfileDTO response = profileService.updateProfile(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }
}
