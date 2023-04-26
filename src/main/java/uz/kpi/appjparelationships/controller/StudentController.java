package uz.kpi.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.kpi.appjparelationships.entity.Student;
import uz.kpi.appjparelationships.repository.StudentRepository;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    //1. Vazirlik
    @GetMapping("/forMinistry")
    public Page<Student> gitStudentListForMinistry(@RequestParam int page) {
        //1-1=0, 2-1=1, 3-1=2, 4-1=3...
        //select @ from student limit 10 offset (0*10)
        //select @ from student limit 10 offset (1*10)
        //select @ from student limit 10 offset (2*10)
        //select @ from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }
    //2. University
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> gitStudentListForUniversity(@PathVariable Integer universityId, @RequestParam int page) {
        //1-1=0, 2-1=1, 3-1=2, 4-1=3...
        //select @ from student limit 10 offset (0*10)
        //select @ from student limit 10 offset (1*10)
        //select @ from student limit 10 offset (2*10)
        //select @ from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);

        return studentPage;
    }
    //3. Faculty Dekanat
    //4. Group Owner
}
