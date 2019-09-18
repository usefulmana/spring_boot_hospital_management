package rmit.spring.hospital.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.repositories.PrescriptionRepository;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.Prescription;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping("/prescriptions/{id}")
    public Prescription getPrescriptionById(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a prescription with id = ", id));
    }

    @PostMapping("/prescriptions")
    public Prescription createAPrescription(@Valid @RequestBody Prescription prescription){
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
