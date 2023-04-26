package uz.kpi.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.kpi.appjparelationships.entity.Subject;
import uz.kpi.appjparelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    //Create
    @RequestMapping(method = RequestMethod.POST)
    public String getSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "Bunday fan mavjud!";

        subjectRepository.save(subject);
        return "Add subject";
    }
    @GetMapping
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }
    @RequestMapping(value = "/subject/{id}",method = RequestMethod.PUT)
    public String editSubject(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject subject=new Subject();
            Subject editSubject = optionalSubject.get();
            editSubject.setName(subject.getName());
            subjectRepository.save(editSubject);
            return "Edit Subject";
        }
        return "Error";
    }

}
