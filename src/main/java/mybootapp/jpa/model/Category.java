package mybootapp.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "name", length = 200)
    private String name;

    public Category(String name) {
        this.name = name;
    }
    // Constructeurs, getters et setters
}
