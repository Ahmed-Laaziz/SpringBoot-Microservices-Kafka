package ma.ensaj.school_microservice.Repositories;

import ma.ensaj.school_microservice.Models.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {

    @Query(value="select name from school s where s.id LIKE %:school_id% ",nativeQuery=true)
    String getSchoolNameById(Long school_id);

    @Query(value="select count(id) from school s where s.category ='University' ",nativeQuery=true)
    int getUniversitiesNumber();

    @Query(value="select count(id) from school s where s.category ='High School' ",nativeQuery=true)
    int getHighSchoolsNumber();

    @Query(value="select count(id) from school s where s.category ='OFPPT' ",nativeQuery=true)
    int getOfpptNumber();
}
