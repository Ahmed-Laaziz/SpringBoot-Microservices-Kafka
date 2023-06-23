package ma.ensaj.school_microservice.Controllers;

import ma.ensaj.school_microservice.Models.Payment;
import ma.ensaj.school_microservice.Models.Student;
import ma.ensaj.school_microservice.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Students")
@CrossOrigin("*")  // sending resources to other applications
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudentById/{student_id}")
    public Student getStudentById(@PathVariable Long student_id){
        return studentService.getStudentById(student_id);
    }

    //add new bill
    @PostMapping("/addStudent")
    public Student save(@RequestBody Student student) {
        return studentService.addStudent(student);
    }
    //update student data
    @PutMapping("/{student_id}")
    public Student save(@RequestBody Student student, @PathVariable Long student_id) {
        return studentService.updateStudent(student, student_id);
    }

    @DeleteMapping("deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        Student student = null;
        student = studentService.getStudentById(id);
        studentService.deleteStudentById(student);
    }

    @GetMapping("getStudentByCne/{cne}")
    public Student getStudentByCNE(@PathVariable String cne){
        return studentService.getStudentByCNE(cne);
    }

    @GetMapping("getMaleStudentsPercentage")
    public float getMaleStudentsPercentage(){
        return studentService.getMaleStudentsPercentage();
    }

   /* @GetMapping("addPaymentToStudent/{payment_id}/{student_id}")
    public void addPaymentToStudent(@PathVariable Long payment_id, @PathVariable  Long student_id){
        studentService.addPaymentToStudent(payment_id, student_id);
    }*/

}

