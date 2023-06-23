package ma.ensaj.school_microservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String region;
    private float insuranceAmount;
    private float latitude;
    private float longitude;
    private String category;
    private String image_url;
    //@OneToMany(mappedBy = "school")
    //private List<Student> students;
}
