package br.edu.ifsp.point.services;

import br.edu.ifsp.point.exceptions.handler.UserNotFoundException;
import br.edu.ifsp.point.models.Timesheet;
import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.dtos.TimesheetDTO;
import br.edu.ifsp.point.repositories.TimesheetRepository;
import br.edu.ifsp.point.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    UserService userService;

    public List<Timesheet> findAll(){
        return timesheetRepository.findAll();
    }

    public Timesheet findById(Long id){
        return timesheetRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Apontamento de horas não encontrado na base de dados"));
    }

    public List<Timesheet> findByUserId(Long id){
        return timesheetRepository.findByUserId(id);
    }

    public Timesheet save(TimesheetDTO timesheetDTO) {

        Timesheet timesheet = this.normalize(null, timesheetDTO);

        return timesheetRepository.save(timesheet);
    }

    public Timesheet update(Long id, TimesheetDTO timesheetDTO) {

        Timesheet timesheet = this.normalize(id, timesheetDTO);

        return timesheetRepository.save(timesheet);
    }

    public void deleteById(Long id) {

        findById(id);

        timesheetRepository.deleteById(id);
    }

    private Timesheet normalize(Long id, TimesheetDTO timesheetDTO){

        Timesheet timesheet;

        if(id == null) {
            timesheet = new Timesheet();
        } else {
            timesheet = this.findById(id);
        }

        User user = userService.findById(timesheetDTO.getUserId());

        timesheet.setDate(timesheetDTO.getDate());
        timesheet.setStart(timesheetDTO.getStart());
        timesheet.setEnd(timesheetDTO.getEnd());
        timesheet.setUser(user);

        return timesheet;

    }

}
