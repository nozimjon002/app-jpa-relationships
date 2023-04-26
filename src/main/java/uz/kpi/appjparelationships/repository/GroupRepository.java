package uz.kpi.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.kpi.appjparelationships.entity.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findAllByFaculty_University_Id(Integer faculty_university_id);

    @Query("select gr from groups gr where gr.faculty.university.id=:universityId")
    List<Group> getGroupByUniversityId(Integer universityId);

    @Query(value = "select * " +
            "from groups g join faculty f on f.id=g.faculty_id " +
            "join university u on u.id=f.university_id " +
            "where u.id=:university_id", nativeQuery = true)
    List<Group> getGroupByUniversityIdNative(Integer university_id);
}

