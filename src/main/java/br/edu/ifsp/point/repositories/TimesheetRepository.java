package br.edu.ifsp.point.repositories;

import br.edu.ifsp.point.models.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    @Query(value = "SELECT * FROM tb_timesheet tm WHERE tm.user_id = ?1", nativeQuery = true)
    public List<Timesheet> findByUserId(Long userId);

}
