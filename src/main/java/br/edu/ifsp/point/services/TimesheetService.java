package br.edu.ifsp.point.services;

import br.edu.ifsp.point.repositories.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

}
