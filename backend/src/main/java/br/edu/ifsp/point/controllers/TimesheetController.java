package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.models.vo.TimesheetVO;
import br.edu.ifsp.point.services.TimesheetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/timesheet")
@Api(value = "API - Timesheet")
@CrossOrigin(origins = "*")
public class TimesheetController {

    @Autowired
    TimesheetService timesheetService;

    @GetMapping("")
    @ApiOperation("Retorna uma lista com os pontos cadastrados")
    public ResponseEntity<SuccessResponse> findAll(){

        List<TimesheetVO> timesheetsVO = timesheetService.findAll();

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheetsVO));
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna um único ponto cadastrado")
    public ResponseEntity<SuccessResponse> findById(@PathVariable("id") Long id){

        TimesheetVO timesheetVO = timesheetService.findById(id);

        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheetVO));
    }

    @GetMapping("/user/{id}")
    @ApiOperation("Retorna uma lista com os pontos cadastrados pelo usuário especificado")
    public ResponseEntity<SuccessResponse> findByUserId(@PathVariable("id") Long id){

        List<TimesheetVO> timesheetsVO = timesheetService.findByUserId(id);


        return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK, timesheetsVO));
    }

    @PostMapping("")
    @ApiOperation("Cadastra um ponto na base de dados")
    public ResponseEntity<SuccessResponse> create(@RequestBody TimesheetVO obj) throws ParseException {

        TimesheetVO timesheetCreated = timesheetService.save(obj);

        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, timesheetCreated));
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um ponto na base de dados")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody TimesheetVO obj){

        TimesheetVO timesheetUpdated = timesheetService.update(id, obj);

        return ResponseEntity.ok().body(new SuccessResponse(HttpStatus.OK, timesheetUpdated));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deleta um ponto na base de dados")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){

        timesheetService.deleteById(id);

        return ResponseEntity.status(204).body(new SuccessResponse(HttpStatus.NO_CONTENT, "Apontamento de horas deletado com sucesso"));
    }


}
