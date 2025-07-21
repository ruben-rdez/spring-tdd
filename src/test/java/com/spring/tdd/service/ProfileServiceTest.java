package com.spring.tdd.service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.spring.tdd.model.Profile;
import com.spring.tdd.repository.ProfileRepository;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void findAllProfiles() {
        final List<Profile> profiles = List.of(
            new Profile("Alice", 30),
            new Profile("Bob", 25)
        );

        when(profileRepository.findAll()).thenReturn(profiles);
        List<Profile> result = profileService.findAllProfiles();

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result.get(0).getName()).isEqualTo("Alice");
        Assertions.assertThat(result.get(1).getName()).isEqualTo("Bob");
        Assertions.assertThat(result.get(0).getAge()).isEqualTo(30);
        Assertions.assertThat(result.get(1).getAge()).isEqualTo(25);
    }

}
