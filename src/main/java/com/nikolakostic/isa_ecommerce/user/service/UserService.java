package com.nikolakostic.isa_ecommerce.user.service;

import com.nikolakostic.isa_ecommerce.category.service.CategoryService;
import com.nikolakostic.isa_ecommerce.security.dto.RegisterDTO;
import com.nikolakostic.isa_ecommerce.security.model.ConcreteUserDetails;
import com.nikolakostic.isa_ecommerce.user.dto.UpdateProfileDTO;
import com.nikolakostic.isa_ecommerce.user.exception.InvalidCredentialsException;
import com.nikolakostic.isa_ecommerce.user.exception.UserExistsException;
import com.nikolakostic.isa_ecommerce.user.entity.User;
import com.nikolakostic.isa_ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryService categoryService;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(RegisterDTO dto) throws InvalidCredentialsException, UserExistsException, IllegalArgumentException {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) throw new InvalidCredentialsException();
        if (this.findByEmail(dto.getEmail()).isPresent()) throw new UserExistsException();
        this.categoryService.validateCategories(dto.getFavoriteCategories());
        User user = new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getPhone(),
                dto.getAddress(),
                dto.getFavoriteCategories()
        );
        return userRepository.save(user);
    }

    public User update(UpdateProfileDTO dto) throws IllegalArgumentException {
        User user = this.getAuthenticatedUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        this.categoryService.validateCategories(dto.getFavoriteCategories());
        user.setFavoriteCategories(dto.getFavoriteCategories());
        return userRepository.save(user);
    }

    public void changePassword(String password) {
        User user = this.getAuthenticatedUser();
        user.setPassword(password);
        this.userRepository.save(user);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ConcreteUserDetails userDetails = (ConcreteUserDetails) authentication.getPrincipal();
        return userRepository.findById(userDetails.getId()).get();
    }

    public User getById(Long id) {
        return this.userRepository.getById(id);
    }
}
