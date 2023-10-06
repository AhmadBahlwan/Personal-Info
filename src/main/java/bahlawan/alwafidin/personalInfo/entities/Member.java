package bahlawan.alwafidin.personalInfo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends Person{

    @ManyToOne(fetch = FetchType.LAZY)
    private Parent parent;

    @Builder
    public Member(String firstName, String middleName, String lastName, String nationalNumber, Date birthdate, Parent parent) {
        super(firstName, middleName, lastName, nationalNumber, birthdate);
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Member{" +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
