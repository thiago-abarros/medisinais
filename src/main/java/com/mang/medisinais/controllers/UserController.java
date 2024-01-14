package com.mang.medisinais.controllers;

import com.mang.medisinais.domain.dto.LoginDTO;
import com.mang.medisinais.domain.dto.UserDTO;
import com.mang.medisinais.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/cadastro")
  public ResponseEntity<UserDTO> userRegister(@RequestBody UserDTO userDTO) {
    return null;
  }

  @PostMapping("/login")
  public RequestEntity<UserDTO> userLogin(@RequestBody LoginDTO login) {
    return null;
  }
}
