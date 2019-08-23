/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Internals;

import Logging.OdooXmlRpc;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author frcho
 */
public class check {

    public OdooXmlRpc connection() {
        OdooXmlRpc odoo = new OdooXmlRpc();
        odoo.login("http://localhost:8071", "odoo", "info@uva3.com", "aaa");
        return odoo;
    }

    public Integer checkinOut(int employeeId) {

        OdooXmlRpc odoo = this.connection();

        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("employee_id", "=", employeeId));
        AllFilters.add(asList("check_out", "=", false));

        List attendanceResult = odoo.getRecords("hr.attendance", asList("id", "check_in", "check_out"), AllFilters);

        if (!attendanceResult.isEmpty()) {
            System.out.println("Realizando el checkout");
            System.out.println(attendanceResult);
            HashMap attendance = (HashMap) attendanceResult.get(0);
            int attendanceId = (int) attendance.get("id");

            Map data = new HashMap();
            //Adding elements to map  
            data.put("check_out", this.dateTime());
            odoo.updateRecord("hr.attendance", data, attendanceId);
            System.out.println("Checkout Realizado.");

        } else {
            Map checking = new HashMap();
            //Adding elements to map  
            checking.put("employee_id", employeeId);
            checking.put("check_in", this.dateTime());
            Integer idAttendace = odoo.createRecord("hr.attendance", checking);
            System.out.println(idAttendace);
            return idAttendace;
        }

        return 0;

    }

    public List searchEmployee(String email) {
        OdooXmlRpc odoo = this.connection();
//                odoo.listRecords("hr.attendance");
//                odoo.dumpRequest();
//        Object[] filters = new Object[1];
//        filters[0] = (Arrays.asList(
//                asList("work_email", "=", "aiden.hughes71@example.com")));
//        filters[0] = (Arrays.asList(
//                asList("is_company", "=", true), asList("customer", "=", true)));
        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("work_email", "=", email));

        List employee = odoo.getRecords("hr.employee",
                asList("id", "name", "work_email"), AllFilters);

        return employee;
    }
    public List AllEmployee() {
        OdooXmlRpc odoo = this.connection();
//                odoo.listRecords("hr.attendance");
//                odoo.dumpRequest();
//        Object[] filters = new Object[1];
//        filters[0] = (Arrays.asList(
//                asList("work_email", "=", "aiden.hughes71@example.com")));
//        filters[0] = (Arrays.asList(
//                asList("is_company", "=", true), asList("customer", "=", true)));
        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("active", "=", true));

        List employees = odoo.getRecords("hr.employee",
                asList("id", "name", "work_email"), AllFilters);

        return employees;
    }

    public String dateTime() {

        Date dat = new Date();
        java.sql.Date date = new java.sql.Date(dat.getTime());
        DateFormat dateFormat = null;

        dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        String DateUTC = dateFormat.format(date);
        return DateUTC;

    }

}
