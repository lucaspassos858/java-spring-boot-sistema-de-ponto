package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.models.vo.TimesheetVO;
import br.edu.ifsp.point.services.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    @Autowired
    TimesheetService timesheetService;

    @GetMapping("")
    public ResponseEntity<SuccessResponse> findAll(){

        List<TimesheetVO> timesheetsVO = timesheetService.findAll();

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheetsVO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){

        TimesheetVO timesheetVO = timesheetService.findById(id);

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheetVO));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<SuccessResponse> findByUserId(@PathVariable("id") Long id){

        List<TimesheetVO> timesheetsVO = timesheetService.findByUserId(id);


        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheetsVO));
    }

    @PostMapping("")
    public ResponseEntity<SuccessResponse> create(@RequestBody TimesheetVO obj) throws ParseException {

        TimesheetVO timesheetCreated = timesheetService.save(obj);

        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, timesheetCreated));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody TimesheetVO obj){

        TimesheetVO timesheetUpdated = timesheetService.update(id, obj);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, timesheetUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){

        timesheetService.deleteById(id);

        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "Apontamento de horas deletado com sucesso"));
    }


}
