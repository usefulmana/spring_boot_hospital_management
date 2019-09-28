package rmit.spring.hospital.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Diagnosis;
import rmit.spring.hospital.models.Visit;
import rmit.spring.hospital.repositories.DiagnosisRepository;
import rmit.spring.hospital.repositories.VisitRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class DiagnosisController {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private VisitRepository visitRepository;

    @GetMapping("/diagnosis/{id}")
    public Diagnosis getDiagnosisById(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return diagnosisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a diagnosis with id = ", id));
    }


    @GetMapping("/diagnosis/visit/{v_id}")
    public List<Diagnosis> getAllDiagnosisInAVisit(@Valid @PathVariable(name = "v_id") Long id){
        return diagnosisRepository.findAllByVisit_Id(id);
    }

    @PostMapping("/diagnosis/visit/{v_id}")
    public Diagnosis createADiagnosis(@Valid @PathVariable(name = "v_id") Long id,
                                            @RequestBody Diagnosis diagnosis) throws
            ResourceNotFoundException{
        Visit visit = visitRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Unable to find a visit with id = ", id));
        diagnosis.setVisit(visit);
        return diagnosisRepository.save(diagnosis);
    }

    @PutMapping("/diagnosis/{id}")
    public Diagnosis updateAPrescription(@Valid @PathVariable(name = "id") Long id, @RequestBody Diagnosis
            updatedDiagnosis) throws ResourceNotFoundException{
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a diagnosis with id = ", id));

        diagnosis.setDiagnosisDetails(updatedDiagnosis.getDiagnosisDetails());

        return diagnosisRepository.save(diagnosis);
    }

    @DeleteMapping("/diagnosis/{id}")
    public ResponseEntity<?> deleteAPrescription(@Valid @PathVariable(name = "id") Long id) throws
            ResourceNotFoundException{
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a diagnosis with id = ", id));
        diagnosisRepository.delete(diagnosis);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted a diagnosis with id = %s", id));
        return ResponseEntity.ok(res);

    }
}
