package uz.kpi.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.kpi.appjparelationships.entity.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    boolean existsByNameAndUniversityId(String name,Integer universityId);
    List<Faculty> findAllByUniversityId(Integer universityId);
}
