package ma.ensaj.school_microservice.Services;

import kafka.producer.KafkaStreamsProducer;
import ma.ensaj.school_microservice.Models.Payment;
import ma.ensaj.school_microservice.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPAymentById(Long id){
        return paymentRepository.findById(id).get();
    }

    public Payment addPayment(Payment payment){

        return paymentRepository.save(payment);
    }

    public void deletePayment(Payment payment){
        paymentRepository.delete(payment);
    }

    public Payment updatePayment(Payment payment, Long id) {
        payment.setId(id);
        return paymentRepository.save(payment);
    }


    public List<Payment> getAllPaymentsOfStudent(int student_id) {
        return paymentRepository.getAllPaymentsOfStudent(student_id);
    }
}
