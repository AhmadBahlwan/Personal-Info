package bahlawan.alwafidin.personalInfo.repositories;

import bahlawan.alwafidin.personalInfo.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Page<Person> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM person WHERE " +
            "MATCH(first_name, middle_name, last_name, phone_number, alternate_phone_number) AGAINST (?1)",
            nativeQuery = true)
    Page<Person> findAll(String keyword, Pageable pageable);
}
