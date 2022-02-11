package com.mercadolibre.integrativeproject.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Implementação de função static para calcular a diferença enrte duas datas, requisito 6 Samuel Stalschus
 *
 * @author Samuel Stalschus
 *
 * */
public class JavaTimeDifference {
    public static Integer diferenceBetweenInDays(Timestamp timestamp1, Timestamp timestamp2) {
        long milliseconds = timestamp2.getTime() - timestamp1.getTime();
        int seconds = (int) milliseconds / 1000;
        int hours = seconds / 3600;
        return (int) hours / 24;
    }

    public static Timestamp dateNow() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static Timestamp toTimestamp(String date) {
        try {
            return new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

}
