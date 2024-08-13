package com.bank.boubyan.model.mapper;

import com.bank.boubyan.model.domain.User;
import com.bank.boubyan.model.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public User toEntity(UserDTO dto) {
        var user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setAddress(dto.getAddress());
        user.setJobTitle(dto.getJobTitle());
        user.setRoles(dto.getRoles());
        return user;
    }

    @Override
    public UserDTO toDTO(User entity) {
       return new UserDTO();
    }
}
