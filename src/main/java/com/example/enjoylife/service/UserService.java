package com.example.enjoylife.service;

import com.example.enjoylife.entity.dto.user.UserCreateUpdateDTO;
import com.example.enjoylife.entity.dto.user.UserDTO;
import com.example.enjoylife.entity.User;
import com.example.enjoylife.exception.EnjoyLifeException;
import com.example.enjoylife.mapper.RoleMapperService;
import com.example.enjoylife.mapper.UserMapperService;
import com.example.enjoylife.repo.jpa.RoleRepo;
import com.example.enjoylife.repo.jpa.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserMapperService userMapperService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;
    private final RoleMapperService roleMapperService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public Optional<UserDTO> findById(Long id) {
        return userRepo.findById(id)
                .map(userMapperService::mapToDto);
    }

    public UserDTO save(UserCreateUpdateDTO userCreateUpdateDTO) {
        userRepo.findByUsername(userCreateUpdateDTO.getUsername())
                .ifPresent(value -> {
                    throw new EnjoyLifeException("Данное имя уже существует");
                });

        User user = User.builder()
                .username(userCreateUpdateDTO.getUsername())
                .password(passwordEncoder.encode(userCreateUpdateDTO.getPassword()))
                .createdAt(OffsetDateTime.now())
                .roles(Set.of(roleRepo.findByName("ROLE_USER")
                        .orElseThrow(() -> new EnjoyLifeException("Роли USER не существует"))))
                .build();

        return userMapperService.mapToDto(userRepo.save(user));
    }

    public UserDTO update(Long id, UserCreateUpdateDTO userCreateUpdateDTO) {
        User userFromDb = userRepo.getReferenceById(id);

        userFromDb.setUsername(userCreateUpdateDTO.getUsername());
        userFromDb.setPassword(passwordEncoder.encode(userCreateUpdateDTO.getPassword()));
        userFromDb.setRoles(userCreateUpdateDTO
                .getRoles()
                .stream()
                .map(roleMapperService::mapToEntity)
                .collect(Collectors.toSet()));

        return userMapperService.mapToDto(userRepo.save(userFromDb));
    }

    public Long delete(Long id) {
        userRepo.deleteById(id);

        return id;
    }

    public Page<UserDTO> getList() {
        return userRepo.findAll(Pageable.unpaged())
                .map(userMapperService::mapToDto);
    }
}