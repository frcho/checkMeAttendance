package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author frcho
 */
public class Util {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_Z = "yyyy-MM-dd HH:mm:ss z";

    public static String DifferenceBetweenDates(String vinicio, String vfinal) {

        Date dinicio = null, dfinal = null;
        long milis1, milis2, diff;

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        try {
            // PARSEO STRING A DATE
            dinicio = sdf.parse(vinicio);
            dfinal = sdf.parse(vfinal);

        } catch (ParseException e) {

            System.out.println("Se ha producido un error en el parseo");
        }

        //INSTANCIA DEL CALENDARIO GREGORIANO
        Calendar cinicio = Calendar.getInstance();
        Calendar cfinal = Calendar.getInstance();

        //ESTABLECEMOS LA FECHA DEL CALENDARIO CON EL DATE GENERADO ANTERIORMENTE
        cinicio.setTime(dinicio);
        cfinal.setTime(dfinal);

        milis1 = cinicio.getTimeInMillis();

        milis2 = cfinal.getTimeInMillis();

        diff = milis2 - milis1;

        // calcular la diferencia en segundos
        long diffSegundos = Math.abs(diff / 1000);

        // calcular la diferencia en minutos
        long diffMinutos = Math.abs(diff / (60 * 1000));

        double lastTime = (double) (diffSegundos * 1) / 3600;

        long restominutos = diffMinutos % 60;
        long restoseg = diffSegundos % 60;

        // calcular la diferencia en horas
        long diffHoras = (diff / (60 * 60 * 1000));

        // calcular la diferencia en dias
        long diffdias = Math.abs(diff / (24 * 60 * 60 * 1000));

        System.out.println("En segundos: " + diffSegundos + " segundos.");

        System.out.println("En minutos: " + diffMinutos + " minutos.");

        System.out.println("En horas: " + diffHoras + " horas.");

        System.out.println("En dias: " + diffdias + " dias.");

        System.out.println("Total Horas con Minutos: " + lastTime);

        String devolver = String.valueOf(diffHoras + "h " + restominutos + "m " + restoseg + "s");
        // String devolver = String.valueOf(lastTime);

        return devolver;
    }

    /**
     * Method to get String dateTime
     *
     * @return String
     */
    public static String dateFormatTocheck() {

        Date dat = new Date();
        java.sql.Date date = new java.sql.Date(dat.getTime());

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String DateUTC = dateFormat.format(date);
        return DateUTC;

    }

    /**
     * Method to get String dateTime
     *
     * @return String
     */
    public static String dateFormatWithTimezone() {

        Date dat = new Date();
        java.sql.Date date = new java.sql.Date(dat.getTime());

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String DateUTC = dateFormat.format(date);
        return DateUTC;

    }

    /**
     * 
     * Method to get Current dateTime by default timezone 
     * @param stamp string date with format yyyy-MM-dd HH:mm:ss
     * @return String datetime
     * @throws ParseException 
     */
    public static String getCurrentTime(String stamp) throws ParseException {

        SimpleDateFormat dfZone = new SimpleDateFormat(DATE_FORMAT_Z);
        Date dZulu = dfZone.parse(stamp + " UTC");

        dfZone.setTimeZone(TimeZone.getDefault());

        return new SimpleDateFormat(DATE_FORMAT).format(dZulu);
    }
}
