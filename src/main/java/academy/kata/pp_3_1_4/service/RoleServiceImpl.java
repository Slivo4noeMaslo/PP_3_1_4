package academy.kata.pp_3_1_4.service;

import academy.kata.pp_3_1_4.DAO.RoleDAO;
import academy.kata.pp_3_1_4.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRole(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public void addRole(Role role) {
        roleDAO.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.findAll();
    }
}
