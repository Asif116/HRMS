package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
	
	Optional<Payroll> findByEmployee_IdAndMonth(Long employeeId, String month);
    List<Payroll> findAllByEmployee_IdAndMonth(Long employeeId, String month);  
}
