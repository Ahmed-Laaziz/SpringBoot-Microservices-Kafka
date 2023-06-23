package ma.ensaj.school_microservice.Repositories;

import ma.ensaj.school_microservice.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value="select * from student s where s.cne =:cne ",nativeQuery=true)
    Student getStudentByCNE(String cne);
    @Query(value = "select count(id) from student s where s.gender = 'Male'", nativeQuery = true)
    float getMaleStudentsPercentage();
}
