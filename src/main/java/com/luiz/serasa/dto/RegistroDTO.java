package com.luiz.serasa.dto;

import com.luiz.serasa.security.UserRole;

public record RegistroDTO(String username, String password, UserRole role) {

}
