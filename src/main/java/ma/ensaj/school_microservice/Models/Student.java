package ma.ensaj.school_microservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String last_name;
    private String first_name;
    private String gender;
    private String email;
    private String phone;
    private String picture;
    private String address;
    private String cin;
    private String cne;
    private Date birthday;
    private String nationality;
    private String school_name;
    //@OneToMany(mappedBy = "student")
    //private List<Payment> payment;
}
