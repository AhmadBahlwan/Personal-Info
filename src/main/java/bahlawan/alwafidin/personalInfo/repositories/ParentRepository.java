package bahlawan.alwafidin.personalInfo.repositories;

import bahlawan.alwafidin.personalInfo.entities.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {

    Page<Parent>findAll(Pageable pageable);
}
