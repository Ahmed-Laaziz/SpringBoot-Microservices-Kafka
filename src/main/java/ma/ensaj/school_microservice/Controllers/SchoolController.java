package ma.ensaj.school_microservice.Controllers;

import ma.ensaj.school_microservice.Models.School;
import ma.ensaj.school_microservice.Models.Student;
import ma.ensaj.school_microservice.Services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Schools")
@CrossOrigin("*")

public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @GetMapping("/getAllSchools")
    public List<School> getAllSchools(){
        return schoolService.getAllSchools();
    }

    @GetMapping("/getSchoolById/{school_id}")
    public School getStudentById(@PathVariable Long school_id){
        return schoolService.getSchoolById(school_id);
    }

    //add new school
    @PostMapping("/addSchool")
    public School save(@RequestBody School school) {
        return schoolService.addSchool(school);
    }
    //update school data
    @PutMapping("/{school_id}")
    public School save(@RequestBody School school, @PathVariable Long school_id) {
        return schoolService.updateSchool(school, school_id);
    }

    @DeleteMapping("deleteSchool/{id}")
    public void deleteSchool(@PathVariable Long id) {
        School school = null;
        school = schoolService.getSchoolById(id);
        schoolService.deleteSchoolById(school);
    }
    //find school name using its id
    @GetMapping("/getSchoolNameById/{school_id}")
    public String getSchoolNameById(@PathVariable Long school_id) {
        return schoolService.getSchoolNameById(school_id);
    }

    @GetMapping("/getSchoolsNumber")
    public int getSchoolsNumber(){return schoolService.getSchoolsNumber();}

    @GetMapping("/getUniversitiesNumber")
    public int getUniversitiesNumber(){return schoolService.getUniversitiesNumber();}

    @GetMapping("/getHighSchoolsNumber")
    public int getHighSchoolsNumber(){return schoolService.getHighSchoolsNumber();}

    @GetMapping("/getOfpptsNumber")
    public int getOfpptNumber(){return schoolService.getOfpptNumber();}
}
