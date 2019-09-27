package Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author frcho
 */
public class Util {

    public static String DifferenceBetweenDates(String vinicio, String vfinal) {

        Date dinicio = null, dfinal = null;
        long milis1, milis2, diff;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String DateUTC = dateFormat.format(date);
        return DateUTC;

    }

    public static Date getSomeDate(final String str, final TimeZone tz)
            throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        sdf.setTimeZone(tz);
        return sdf.parse(str);
    }

    public static void main(String[] args) throws ParseException {
       String string1 = "2009-10-10 12:12:12";
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        Date date = sdf.parse(string1);
//        System.out.println(sdf.format(getSomeDate(
//                "2010-11-17 01:12:00", TimeZone.getTimeZone("Europe/Berlin"))));
//        System.out.println(date);
    }
}
