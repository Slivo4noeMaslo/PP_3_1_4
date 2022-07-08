package academy.kata.pp_3_1_4.DAO;

import academy.kata.pp_3_1_4.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
