package rmit.spring.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.repositories.DiseaseRepository;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Disease;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class DiseaseController {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @GetMapping("/diseases")
    public List<Disease> getAllDiseases(){
        return diseaseRepository.findAll();
    }

    @GetMapping("/diseases/{id}")
    public Disease getADiseaseById(@Valid @PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        return diseaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any disease with id = ", id));
    }

    @GetMapping("/diseases/byName/{name}")
    public List<Disease> getDiseasesByName(@Valid @PathVariable(value = "name") String name){
        return diseaseRepository.findByDiseaseNameIgnoreCaseContaining(name);
    }

    @PostMapping("/diseases")
    public Disease addNewDisease(@Valid @RequestBody Disease disease){
        return  diseaseRepository.save(disease);
    }

    @PutMapping("/diseases/{id}")
    public Disease updateDiseaseInfo(@Valid @PathVariable(value = "id") Long id, @RequestBody Disease diseaseDetails) throws ResourceNotFoundException{
        Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any disease with id = ", id));
//        disease.setChapterCode(diseaseDetails.getChapterCode());
//        disease.setChapterName(diseaseDetails.getChapterName());
//        disease.setDiseaseCode(diseaseDetails.getDiseaseCode());
        disease.setDiseaseName(diseaseDetails.getDiseaseName());
//        disease.setGroupCode(diseaseDetails.getGroupCode());
//        disease.setGroupName(diseaseDetails.getGroupName());
//        disease.setTypeCode(diseaseDetails.getTypeCode());
//        disease.setTypeName(diseaseDetails.getTypeName());

        return diseaseRepository.save(disease);
    }

    @DeleteMapping("/diseases/{id}")
    public ResponseEntity<?> deleteDisease(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException{
        Disease disease = diseaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any disease with id = ", id));
        diseaseRepository.delete(disease);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted a disease with id = %s", id));
        return ResponseEntity.ok(res);
    }
}
