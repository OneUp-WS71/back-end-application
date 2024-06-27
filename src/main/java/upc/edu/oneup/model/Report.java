package upc.edu.oneup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.TimeZone;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reports")
public class    Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "heart_rate",nullable = false, length = 50)
    private double heartRate;

    @Column(name = "breathing_frequency",nullable = false, length = 50)
    private double breathingFrequency;

    @Column(name = "temperature",nullable = false, length = 50)
    private double temperature;

    @Column(name = "longitude",nullable = false,  length = 50)
    private String longitude;

    @Column(name = "latitude",nullable = false,  length = 50)
    private String latitude;

    @Column(name = "report_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // Especifica el tipo de temporalidad de la fecha
    private Date reportTime; // Campo para almacenar la fecha y hora del reporte


    @JsonIgnore
    @JsonManagedReference
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;


}
