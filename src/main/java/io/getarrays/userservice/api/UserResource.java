package io.getarrays.userservice.api;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.RoleToUserName;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.service.UserService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @PostMapping("/user/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/user/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    val uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/role/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @PostMapping("/role/addtouser")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserName form) {
    userService.addRoleToUser(form.getUsername(), form.getRolName());
    return ResponseEntity.ok().build();
  }
}
