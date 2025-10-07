package repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {
	
	Optional<Payroll> findByEmployee_IdAndMonth(Long employeeId, YearMonth month);
    List<Payroll> findAllByMonth(YearMonth month);


}
