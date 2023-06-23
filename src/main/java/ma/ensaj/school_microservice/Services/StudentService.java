package ma.ensaj.school_microservice.Services;

import kafka.producer.KafkaStreamsProducer;
import ma.ensaj.school_microservice.Models.Payment;
import ma.ensaj.school_microservice.Models.Student;
import ma.ensaj.school_microservice.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PaymentService paymentService;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).get();
    }

    public Student addStudent(Student student){
        KafkaStreamsProducer kafkaStreamsProducer = new KafkaStreamsProducer();
        kafkaStreamsProducer.sendStudentToProducer(student);
        return studentRepository.save(student);
    }

    public void deleteStudentById(Student student){
        studentRepository.delete(student);
    }

    public Student updateStudent(Student student, Long id) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public Student getStudentByCNE(String cne) {
        return studentRepository.getStudentByCNE(cne);
    }

    public float getMaleStudentsPercentage(){
        return ((studentRepository.getMaleStudentsPercentage())/(studentRepository.findAll().size()) * 100);
    }



    // This Method is created to add for each student a payment
    /*public void addPaymentToStudent(Long payment_id, Long student_id) {
        //studentRepository.save(student);
        //int payments_size = studentRepository.findById(student_id).get().getPayment().size();
        List<Payment> list_of_payments = studentRepository.findById(student_id).get().getPayment();
        list_of_payments.add(paymentService.getPAymentById(payment_id));
        System.out.println(list_of_payments);
        (studentRepository.findById(student_id)).get().setPayment(list_of_payments);
    }*/

}
