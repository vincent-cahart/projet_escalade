package mybootapp.jpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Sortie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "description", length = 200)
    private String description;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "site_web", length = 200)
    private String siteWeb;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member creator;

    @Basic
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "date_sortie")
    private Date dateSortie;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public Sortie(String name, String description, String siteWeb, Member creator, Category category) {
        this(0, name, description, siteWeb, creator, null, category);
    }

}
