package de.fhdo.fsc.project.DataType;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public class Date {

    LocalDateTime ldt;

    public Date(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public boolean add(LocalDateTime b) {
        return false;
        //TemporalAmount ta = b.
        //ldt.plus();
    }
}
