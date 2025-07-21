package com.spring.tdd.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.tdd.dto.ProfileRequestDTO;
import com.spring.tdd.dto.ProfileResponseDTO;
import com.spring.tdd.service.ProfileService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@Valid @RequestBody ProfileRequestDTO requestDTO) {
        if (!isValidProfile(requestDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ProfileResponseDTO responseDTO = profileService.createProfile(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long id) {
        return profileService.getProfile(id)
                .map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private boolean isValidProfile(ProfileRequestDTO profile) {
        return profile.getName() != null && 
               !profile.getName().trim().isEmpty() && 
               profile.getAge() >= 0;
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles() {
        List<ProfileResponseDTO> profiles = profileService.findAllProfiles()
            .stream()
            .map(profile -> new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getAge()))
            .collect(Collectors.toList());
        
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
}
