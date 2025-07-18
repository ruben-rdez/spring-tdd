package com.spring.tdd.service;

import org.springframework.stereotype.Service;
import com.spring.tdd.dto.ProfileRequestDTO;
import com.spring.tdd.dto.ProfileResponseDTO;
import com.spring.tdd.model.Profile;
import com.spring.tdd.repository.ProfileRepository;
import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileResponseDTO createProfile(ProfileRequestDTO requestDTO) {
        Profile profile = new Profile(requestDTO.getName(), requestDTO.getAge());
        Profile savedProfile = profileRepository.save(profile);
        return new ProfileResponseDTO(savedProfile.getId(), savedProfile.getName(), savedProfile.getAge());
    }

    public java.util.Optional<ProfileResponseDTO> getProfile(Long id) {
        return profileRepository.findById(id)
                .map(profile -> new ProfileResponseDTO(
                    profile.getId(), 
                    profile.getName(), 
                    profile.getAge()
                ));
    }

    public List<Profile> findAllProfiles() {
        return profileRepository.findAll();
    }
}
