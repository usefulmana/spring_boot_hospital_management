package rmit.spring.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.spring.hospital.exceptions.ResourceNotFoundException;
import rmit.spring.hospital.models.LabReport;
import rmit.spring.hospital.models.Prescription;
import rmit.spring.hospital.models.Visit;
import rmit.spring.hospital.repositories.LabReportRepository;
import rmit.spring.hospital.repositories.VisitRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LabReportController {

    @Autowired
    private LabReportRepository labReportRepository;

    @Autowired
    private VisitRepository visitRepository;

    @GetMapping("/labs/{id}")
    public LabReport getALabReportById(@Valid @PathVariable(name = "id") Long id) throws ResourceNotFoundException {
        return labReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a lab report with id = ", id));
    }

    @GetMapping("/labs/visit/{v_id}")
    public List<LabReport> getAllReportsInAVisit(@Valid @PathVariable(name = "v_id") Long id){
        return labReportRepository.findAllByVisit_Id(id);
    }

    @PostMapping("/labs/visit/{v_id}")
    public LabReport createALabReport(@Valid @PathVariable(name = "v_id") Long id,
                                            @RequestBody LabReport report) throws
            ResourceNotFoundException{
        Visit visit = visitRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Unable to find a visit with id = ", id));
        report.setVisit(visit);
        return labReportRepository.save(report);
    }

    @PutMapping("/labs/{id}")
    public LabReport updateAReport(@Valid @PathVariable(name = "id") Long id, @RequestBody LabReport
            updatedReport) throws ResourceNotFoundException{
        LabReport report = labReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a report with id = ", id));

        report.setLabReportDetails(updatedReport.getLabReportDetails());

        return labReportRepository.save(report);
    }

    @DeleteMapping("/labs/{id}")
    public ResponseEntity<?> deleteALabReport(@Valid @PathVariable(name = "id") Long id) throws
            ResourceNotFoundException{
        LabReport report = labReportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find a report with id = ", id));
        labReportRepository.delete(report);
        HashMap<String, String> res = new HashMap<>();
        res.put("message", String.format("Successfully deleted a report with id = %s", id));
        return ResponseEntity.ok(res);

    }
}
