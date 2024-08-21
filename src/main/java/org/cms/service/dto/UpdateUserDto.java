package org.cms.service.dto;

import org.cms.enums.Role;

import java.util.Optional;

public record UpdateUserDto(
        Optional<String> email,
        Optional<String> password,
        Optional<Role> role
) {
}
