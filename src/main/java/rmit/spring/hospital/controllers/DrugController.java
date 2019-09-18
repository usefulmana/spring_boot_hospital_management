package rmit.spring.hospital.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.repositories.DrugRepository;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Drug;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DrugController {

    @Autowired
    private DrugRepository drugRepository;

    @GetMapping("/drugs")
    private List<Drug> getAllDrugs(){
        return drugRepository.findAll();
    }

    @GetMapping("/drugs/{id}")
    private Drug getADrugById(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any drug with id = ", id));

    }

    @GetMapping("/drugs/byName/{name}")
    private List<Drug> getDrugsByName(@Valid @PathVariable(name = "name") String name){
        return drugRepository.findByDrugNameIgnoreCaseContaining(name);
    }

    @PostMapping("/drugs")
    private Drug addDrug(@RequestBody Drug drug){
        return drugRepository.save(drug);
    }

    @PutMapping("/drugs/{id}")
    private Drug getADrugById(@Valid @PathVariable(name = "id") Long id, @RequestBody Drug drugDetails)
            throws ResourceNotFoundException {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any drug with id = ", id));
        drug.setDrugName(drugDetails.getDrugName());

        return drugRepository.save(drug);
    }

    @DeleteMapping("/drugs/{id}")
    private @ResponseBody ResponseEntity<?> deleteDrug(@PathVariable(name = "id") Long id) throws ResourceNotFoundException{
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find any drug with id = ", id));
        drugRepository.delete(drug);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted drug with id = %s", id));
        return ResponseEntity.ok(res);
    }
}
