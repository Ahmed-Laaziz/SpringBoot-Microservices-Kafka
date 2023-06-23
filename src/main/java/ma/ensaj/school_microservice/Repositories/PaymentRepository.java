package ma.ensaj.school_microservice.Repositories;

import ma.ensaj.school_microservice.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value="select * from payment b where b.student_id=:student_id ",nativeQuery=true)
    List<Payment> getAllPaymentsOfStudent(int student_id);
}
