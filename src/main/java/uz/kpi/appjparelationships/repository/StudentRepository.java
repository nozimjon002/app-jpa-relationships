package uz.kpi.appjparelationships.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.kpi.appjparelationships.entity.Student;
import uz.kpi.appjparelationships.entity.Subject;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);

}
