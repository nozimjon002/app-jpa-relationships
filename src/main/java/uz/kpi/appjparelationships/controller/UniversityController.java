package uz.kpi.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.kpi.appjparelationships.entity.Address;
import uz.kpi.appjparelationships.entity.University;
import uz.kpi.appjparelationships.payload.UniversitiDto;
import uz.kpi.appjparelationships.repository.AddressRepository;
import uz.kpi.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    //Read
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversties() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    //Create
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversitiDto universitiDto) {
        Address address = new Address();
        address.setCity(universitiDto.getCity());
        address.setDistrict(universitiDto.getDistrict());
        address.setStreet(universitiDto.getStreet());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universitiDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return "University added";
    }

    //Update
    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversityAddress(@PathVariable Integer id, @RequestBody UniversitiDto universitiDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            University editingUniversity = optionalUniversity.get();
            editingUniversity.setName(universitiDto.getName());
            Address address = editingUniversity.getAddress();
            address.setCity(universitiDto.getCity());
            address.setDistrict(universitiDto.getDistrict());
            address.setStreet(universitiDto.getStreet());
            addressRepository.save(address);
            universityRepository.save(editingUniversity);
            return "Edit University";
        }
        return "Not found University";
    }
    //Delete
    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        universityRepository.deleteById(id);
        return "Deleted";
    }
}
