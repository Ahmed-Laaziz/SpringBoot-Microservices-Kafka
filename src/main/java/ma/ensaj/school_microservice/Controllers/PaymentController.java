package ma.ensaj.school_microservice.Controllers;

import ma.ensaj.school_microservice.Models.Payment;
import ma.ensaj.school_microservice.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/getAllPayments")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    //add new category
    @PostMapping("/addPayment")
    public Payment save(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }
    //update student data
    @PutMapping("/{payment_id}")
    public Payment save(@RequestBody Payment payment, @PathVariable Long payment_id) {

        return paymentService.updatePayment(payment, payment_id);
    }

    @DeleteMapping("deletePayment/{id}")
    public void deletePayment(@PathVariable Long id) {
        Payment payment = null;
        payment = paymentService.getPAymentById(id);
        paymentService.deletePayment(payment);
    }

    //find all the bills of a specific user
    @GetMapping("/allPaymentsOfStudent/{student_id}")
    public List<Payment> getAllPaymentsOfStudent(@PathVariable int student_id) {
        return paymentService.getAllPaymentsOfStudent(student_id);
    }

}
