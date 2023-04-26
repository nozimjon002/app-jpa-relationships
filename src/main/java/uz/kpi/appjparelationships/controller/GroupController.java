package uz.kpi.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.kpi.appjparelationships.entity.Faculty;
import uz.kpi.appjparelationships.entity.Group;
import uz.kpi.appjparelationships.payload.GroupDto;
import uz.kpi.appjparelationships.repository.FacultyRepository;
import uz.kpi.appjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;

    //Vazirlik uchun
    //READ
    @GetMapping
    public List<Group> getGroups() {
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

    //Universitet mas'ul hodimi uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId) {
        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
        List<Group> groupByUniversityId = groupRepository.getGroupByUniversityId(universityId);
        List<Group> groupByUniversityIdNative = groupRepository.getGroupByUniversityIdNative(universityId);
        return allByFaculty_university_id;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return "Such faculty not found";
        }
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group added";

    }


}
