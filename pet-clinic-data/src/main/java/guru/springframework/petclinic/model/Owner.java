package guru.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owners")
public class Owner extends Person{

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String sity;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public Owner(Long id, String firstName, String lastName, String address, String sity, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.sity = sity;
        this.telephone = telephone;
        this.pets = pets;
    }
}
