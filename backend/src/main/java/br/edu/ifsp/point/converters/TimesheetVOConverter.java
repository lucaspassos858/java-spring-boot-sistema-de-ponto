package br.edu.ifsp.point.converters;

import br.edu.ifsp.point.models.Timesheet;
import br.edu.ifsp.point.models.User;
import br.edu.ifsp.point.models.vo.TimesheetVO;
import br.edu.ifsp.point.models.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class TimesheetVOConverter {

    public static Timesheet convertVOToTimesheet(TimesheetVO timesheetVO){

        Timesheet timesheet = new Timesheet();

        timesheet.setId(timesheetVO.getId());
        timesheet.setDate(timesheetVO.getData());
        timesheet.setStart(timesheetVO.getJornadaInicio());
        timesheet.setEnd(timesheetVO.getJornadaFim());
        timesheet.setUser(UserVOConverter.convertVOToUser(timesheetVO.getUsuario()));

        return timesheet;
    }

    public static TimesheetVO convertTimesheetToVO(Timesheet timesheet){

        TimesheetVO timesheetVO = new TimesheetVO();

        timesheetVO.setId(timesheet.getId());
        timesheetVO.setData(timesheet.getDate());
        timesheetVO.setJornadaInicio(timesheet.getStart());
        timesheetVO.setJornadaFim(timesheet.getEnd());
        timesheetVO.setUsuario(UserVOConverter.convertUserToVO(timesheet.getUser()));

        return timesheetVO;
    }

    public static List<TimesheetVO> convertTimesheetsToVO(List<Timesheet> timesheets) {

        List<TimesheetVO> timesheetsVO = new ArrayList<>();

        for (Timesheet timesheet: timesheets) {
            timesheetsVO.add(TimesheetVOConverter.convertTimesheetToVO(timesheet));
        }

        return timesheetsVO;
    }

}
