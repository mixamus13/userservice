package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.repo.RoleRepo;
import io.getarrays.userservice.repo.UserRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;
  private final RoleRepo roleRepo;

  @Override
  public User saveUser(User user) {
    return userRepo.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    return roleRepo.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    val user = userRepo.findByUsername(username);
    val role = roleRepo.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    return null;
  }

  @Override
  public List<User> getUsers() {
    return null;
  }
}
