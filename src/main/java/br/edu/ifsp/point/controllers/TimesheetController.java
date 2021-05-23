package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.Timesheet;
import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.models.dtos.TimesheetDTO;
import br.edu.ifsp.point.services.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    @Autowired
    TimesheetService timesheetService;

    @GetMapping("")
    public ResponseEntity<SuccessResponse> findAll(){

        List<Timesheet> timesheets = timesheetService.findAll();

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){

        Timesheet timesheet = timesheetService.findById(id);

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheet));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<SuccessResponse> findByUserId(@PathVariable("id") Long id){

        List<Timesheet> timesheets = timesheetService.findByUserId(id);


        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheets));
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse> create(@RequestBody TimesheetDTO obj){

        Timesheet timesheet = timesheetService.save(obj);

        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, timesheet));
    }

    @PostMapping("/submit")
    public ResponseEntity<SuccessResponse> submit(@RequestBody TimesheetDTO obj){

        Timesheet timesheet = timesheetService.save(obj);

        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, timesheet));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody TimesheetDTO obj){

        Timesheet timesheet = timesheetService.update(id, obj);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, timesheet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){

        timesheetService.deleteById(id);

        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "Apontamento de horas deletado com sucesso"));
    }

}
