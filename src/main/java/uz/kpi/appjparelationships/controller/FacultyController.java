package uz.kpi.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.kpi.appjparelationships.entity.Faculty;
import uz.kpi.appjparelationships.entity.University;
import uz.kpi.appjparelationships.payload.FacultyDto;
import uz.kpi.appjparelationships.repository.FacultyRepository;
import uz.kpi.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    //Vazirlik uchun
    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    //Create
    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exist = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exist)
            return "Bu universitetda shunday fakultet mavjud";
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return "Universitet topilmadi";
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return "Fakultet saqlandi";
    }

    //Universitet hodimi uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    //Delete
    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {


            facultyRepository.findById(id);
            return "Faculty deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }
    }

    //Update
    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()) {
                return "University not found";
            }
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Faculty not found";
    }
}
