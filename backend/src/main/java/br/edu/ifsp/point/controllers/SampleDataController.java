package br.edu.ifsp.point.controllers;

import br.edu.ifsp.point.models.api.SuccessResponse;
import br.edu.ifsp.point.models.vo.TimesheetVO;
import br.edu.ifsp.point.models.vo.UserVO;
import br.edu.ifsp.point.services.TimesheetService;
import br.edu.ifsp.point.services.UserService;
import br.edu.ifsp.point.utils.JavaFakerUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/sample")
public class SampleDataController {

    @Autowired
    UserService userService;

    @Autowired
    TimesheetService timesheetService;

    @PostMapping("/generate")
    public ResponseEntity<SuccessResponse> generate() throws ParseException {

        UserVO userVO1 = userService.save(JavaFakerUtils.generateUserVOData());
        UserVO userVO2 = userService.save(JavaFakerUtils.generateUserVOData());
        UserVO userVO3 = userService.save(JavaFakerUtils.generateUserVOData());

        TimesheetVO timesheetVO1 = timesheetService.save(JavaFakerUtils.generateTimesheetVOData(userVO1));
        TimesheetVO timesheetVO2 = timesheetService.save(JavaFakerUtils.generateTimesheetVOData(userVO2));


        return ResponseEntity.status(201).body(new SuccessResponse(HttpStatus.CREATED, "Dados de exemplo gerados com sucesso"));
    }

}
