package br.edu.ifsp.point.repositories;

import br.edu.ifsp.point.models.Timesheet;
import br.edu.ifsp.point.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM tb_user u WHERE u.email = ?1", nativeQuery = true)
    public User findByEmail(String email);

}
