package com.rsh.android.rekammedisbosimrsh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rezar on 04/05/2017.
 */

public class DateConverter {
    public static String dateConvertPlus(String dateInput) {
        String ttl = null;
        SimpleDateFormat formatFromString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatConvert = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");

        try {
            Date date = formatFromString.parse(dateInput);
            ttl = formatConvert.format(date);
            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ttl;
    }
}
