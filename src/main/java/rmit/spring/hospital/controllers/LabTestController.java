package rmit.spring.hospital.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.LabTest;
import rmit.spring.hospital.repositories.LabTestRepository;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class LabTestController {

    @Autowired
    private LabTestRepository labTestRepository;

    @GetMapping("/tests")
    public List<LabTest> getAllTests(){
        return labTestRepository.findAll();
    }

    @GetMapping("/tests/{id}")
    public LabTest getATestById(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return labTestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find a test with id=%s", id));
    }

    @PostMapping("/tests")
    public LabTest addATest(@RequestBody LabTest labTest){
        return labTestRepository.save(labTest);
    }

    @PutMapping("/tests/{id}")
    public LabTest updateATest(@PathVariable(name = "id") Long id, @RequestBody LabTest updatedTest) throws ResourceNotFoundException {
        LabTest test = labTestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find a test with id=%s", id));
        test.setTestName(updatedTest.getTestName());
        test.setDescription(updatedTest.getDescription());
        return labTestRepository.save(test);
    }

    @DeleteMapping("/tests/{id}")
    public @ResponseBody ResponseEntity<?> updateATest(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        LabTest test = labTestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find a test with id=%s", id));
        labTestRepository.delete(test);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted a test with id = %s", id));
        return ResponseEntity.ok(res);
    }
}
