package ma.ensaj.school_microservice.Services;

import ma.ensaj.school_microservice.Models.School;
import ma.ensaj.school_microservice.Models.Student;
import ma.ensaj.school_microservice.Repositories.SchoolRepository;
import ma.ensaj.school_microservice.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    public String getSchoolNameById(Long school_id) {
        return schoolRepository.getSchoolNameById(school_id);
    }
    public List<School> getAllSchools(){
        return schoolRepository.findAll();
    }

    public School getSchoolById(Long id){
        return schoolRepository.findById(id).get();
    }

    public School addSchool(School school){
        return schoolRepository.save(school);
    }

    public void deleteSchoolById(School school){
        schoolRepository.delete(school);
    }

    public School updateSchool(School school, Long id) {
        school.setId(id);
        return schoolRepository.save(school);
    }

    public int getSchoolsNumber(){
        List<School> allSchools = schoolRepository.findAll();
        return allSchools.size();
    }

    public int getUniversitiesNumber(){
        return (schoolRepository.getUniversitiesNumber());
    }

    public int getHighSchoolsNumber(){
        return (schoolRepository.getHighSchoolsNumber());
    }

    public int getOfpptNumber(){
        return (schoolRepository.getOfpptNumber());
    }
}
