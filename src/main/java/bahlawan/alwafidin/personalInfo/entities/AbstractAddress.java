package bahlawan.alwafidin.personalInfo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor

public abstract class AbstractAddress {

    @Column(name = "first_name", nullable = false, length = 45)
    protected String firstName;

    @Column(name = "middle_name", length = 45)
    protected String middleName;

    @Column(name = "last_name", length = 45)
    protected String lastName;

    public AbstractAddress(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
}