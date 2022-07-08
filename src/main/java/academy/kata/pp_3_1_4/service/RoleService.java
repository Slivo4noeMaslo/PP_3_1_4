package academy.kata.pp_3_1_4.service;

import academy.kata.pp_3_1_4.model.Role;

import java.util.List;

public interface RoleService {

    Role getRole(Long id);

    void addRole(Role role);

    List<Role> getRoles();
}
