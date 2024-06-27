package upc.edu.oneup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(name="address",nullable = false,length = 50)
    @NotNull
    @Size(max = 50)
    private String address;

    @Column(name="date",nullable = false,length = 15)
    @NotNull
    @Size(max = 15)
    private String date;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @Column(name="weight",nullable = false,length = 10)
    @NotNull
    @DecimalMin("0.1")
    @DecimalMax("300.0")
    private Double weight;

    @Column(name="height",nullable = false,length = 10)
    @NotNull
    @DecimalMin("0.10")
    @DecimalMax("3.00")
    private Double height;

    @JsonIgnore
    @JsonBackReference("user-patients")
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @JsonIgnore
    @JsonManagedReference
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Device device;


    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Report> reports;
}