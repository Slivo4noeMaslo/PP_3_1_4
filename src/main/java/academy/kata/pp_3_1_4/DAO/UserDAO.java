package academy.kata.pp_3_1_4.DAO;

import academy.kata.pp_3_1_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
