package mybootapp.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "first_name", length = 200)
    private String firstName;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "last_name", length = 100)
    private String lastName;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "email", length = 200)
    private String email;

    @Basic(optional = false, fetch = FetchType.EAGER)
    @Column(name = "password", length = 200)
    private String password;

    //private Set<Sortie> sortieSet;
    public Member(String firstName, String secondName, Date birthDay, String email, String passWord) {
        this(0, firstName, secondName, birthDay, email, passWord);
    }

    public Member(String firstName, String secondName, String email, String password) {
        this(0, firstName, secondName, null, email, password);
    }
}
