package br.edu.ifsp.point.services;

import br.edu.ifsp.point.converters.TimesheetVOConverter;
import br.edu.ifsp.point.exceptions.UserNotFoundException;
import br.edu.ifsp.point.models.Timesheet;
import br.edu.ifsp.point.models.vo.TimesheetVO;
import br.edu.ifsp.point.models.vo.UserVO;
import br.edu.ifsp.point.repositories.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    UserService userService;

    public List<TimesheetVO> findAll(){
        return TimesheetVOConverter.convertTimesheetsToVO(timesheetRepository.findAll());
    }

    public TimesheetVO findById(Long id){
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Apontamento de horas não encontrado na base de dados"));
        return TimesheetVOConverter.convertTimesheetToVO(timesheet);
    }

    public List<TimesheetVO> findByUserId(Long id){
        return TimesheetVOConverter.convertTimesheetsToVO(timesheetRepository.findByUserId(id));
    }

    public TimesheetVO save(TimesheetVO timesheetVO) throws ParseException {

        UserVO userVO = userService.findById(timesheetVO.getUsuario().getId());

        timesheetVO.setUsuario(userVO);

        Timesheet timesheet = timesheetRepository.save(TimesheetVOConverter.convertVOToTimesheet(timesheetVO));

        return TimesheetVOConverter.convertTimesheetToVO(timesheet);
    }

    public TimesheetVO update(Long id, TimesheetVO timesheetVO) {

        TimesheetVO timesheetFound = this.findById(id);

        UserVO userVO = userService.findById(timesheetVO.getUsuario().getId());

        timesheetFound.setData(timesheetVO.getData());
        timesheetFound.setJornadaInicio(timesheetVO.getJornadaInicio());
        timesheetFound.setJornadaFim(timesheetVO.getJornadaFim());
        timesheetFound.setUsuario(userVO);

        Timesheet timesheet = timesheetRepository.save(TimesheetVOConverter.convertVOToTimesheet(timesheetFound));

        return TimesheetVOConverter.convertTimesheetToVO(timesheet);
    }

    public void deleteById(Long id) {

        this.findById(id);

        timesheetRepository.deleteById(id);
    }

    /*private Timesheet normalize(Long id, TimesheetVO timesheetVO){

        Timesheet timesheet;

        if(id == null) {
            timesheet = new Timesheet();
        } else {
            timesheet = this.findById(id);
        }

        // User user = userService.findById(timesheetDTO.getUserId());

        User user = new User();

        timesheet.setDate(timesheetVO.getDate());
        timesheet.setStart(timesheetVO.getStart());
        timesheet.setEnd(timesheetVO.getEnd());
        timesheet.setUser(user);

        return timesheet;

    }*/

}
