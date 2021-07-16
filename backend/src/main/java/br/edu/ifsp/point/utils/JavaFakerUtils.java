package br.edu.ifsp.point.utils;

import br.edu.ifsp.point.models.vo.TimesheetVO;
import br.edu.ifsp.point.models.vo.UserVO;
import com.github.javafaker.Faker;

import java.sql.Time;
import java.util.Date;

public class JavaFakerUtils {

    public static UserVO generateUserVOData() {

        Faker faker = new Faker();

        UserVO userVO = new UserVO();

        userVO.setNome(faker.name().fullName());
        userVO.setEmail(faker.internet().emailAddress());
        userVO.setSenha("ifsp@123");

        return userVO;
    }

    @SuppressWarnings("deprecation")
	public static TimesheetVO generateTimesheetVOData(UserVO userVO) {

        TimesheetVO timesheetVO = new TimesheetVO();

        timesheetVO.setData(new Date());
        timesheetVO.setJornadaInicio(new Time(9, 0, 0));
        timesheetVO.setJornadaFim(new Time(18, 0, 0));
        timesheetVO.setUsuario(userVO);

        return timesheetVO;
    }


}
