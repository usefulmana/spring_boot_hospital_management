package rmit.spring.hospital.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.models.Visit;
import rmit.spring.hospital.repositories.PrescriptionRepository;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Prescription;
import rmit.spring.hospital.repositories.VisitRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private VisitRepository visitRepository;

    @GetMapping("/prescriptions/{id}")
    public Prescription getPrescriptionById(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a prescription with id = ", id));
    }

    @GetMapping("/prescriptions/visit/{v_id}")
    public List<Prescription> getAllPrescriptionInAVisit(@Valid @PathVariable(name = "v_id") Long id){
        return prescriptionRepository.findAllByVisit_Id(id);
    }


    @PostMapping("/prescriptions/visit/{v_id}")
    public Prescription createAPrescription(@Valid @PathVariable(name = "v_id") Long id,
                                            @RequestBody Prescription prescription) throws
            ResourceNotFoundException{
        Visit visit = visitRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Unable to find a visit with id = ", id));
        prescription.setVisit(visit);
        return prescriptionRepository.save(prescription);
    }

    @PutMapping("/prescriptions/{id}")
    public Prescription updateAPrescription(@Valid @PathVariable(name = "id") Long id, @RequestBody Prescription
            updatedPrescription) throws ResourceNotFoundException{
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a prescription with id = ", id));

        prescription.setPrescriptionDetails(updatedPrescription.getPrescriptionDetails());

        return prescriptionRepository.save(prescription);
    }

    @DeleteMapping("/prescriptions/{id}")
    public ResponseEntity<?> deleteAPrescription(@Valid @PathVariable(name = "id") Long id) throws
            ResourceNotFoundException{
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a prescription with id = ", id));
        prescriptionRepository.delete(prescription);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted a prescription with id = %s", id));
        return ResponseEntity.ok(res);

    }
}
