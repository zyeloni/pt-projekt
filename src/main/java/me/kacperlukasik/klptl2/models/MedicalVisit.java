package me.kacperlukasik.klptl2.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.util.Base64Utils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "medicalVisit")
public class MedicalVisit {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Autowired(required = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    @Column(name = "visit_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    @Column(name = "visit_cost", nullable = false)
    private float visitCost;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Drug> drugs;

    @Lob
    @Column(columnDefinition="BLOB")
    private byte[] fileContent;

    public MedicalVisit() {
        drugs = new HashSet<>();
    }

    public String getImage(){
        if (fileContent==null)
            return "asdasd";

        return Base64Utils.encodeToString(fileContent);
    }

}
