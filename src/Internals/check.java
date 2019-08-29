package Internals;

import Logging.OdooXmlRpc;
import Utils.Util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Arrays.asList;
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

    /**
     * Method to create connection with odoo api.
     *
     * @return boolean
     */
    public OdooXmlRpc connection() {
        OdooXmlRpc odoo = new OdooXmlRpc();
        odoo.login("http://localhost:8071", "odoo", "info@uva3.com", "aaa");
        return odoo;
    }

    /**
     * Method that allow to do check in/out
     *
     * @param employeeId
     * @return Map
     */
    public Map checkinOut(int employeeId) {

        OdooXmlRpc odoo = this.connection();

//        List AllFilters = new ArrayList<>();
//        AllFilters.add(asList("employee_id", "=", employeeId));
//        AllFilters.add(asList("check_out", "=", false));
//
//        List attendanceResult = odoo.getRecords("hr.attendance", asList("id", "check_in", "check_out"), AllFilters);
        List attendanceResult = attendanceList(employeeId);
        if (!attendanceResult.isEmpty()) {
            System.out.println("Realizando el checkout");
            System.out.println(attendanceResult);
            HashMap attendance = (HashMap) attendanceResult.get(0);
            int attendanceId = (int) attendance.get("id");
            String checkIn = (String) attendance.get("check_in");

            Map checkInOut = new HashMap();
            //Adding elements to map  
            checkInOut.put("check_out", Util.dateFormatTocheck());
            //Send Map to updateRecord to regist checkOut time
            odoo.updateRecord("hr.attendance", checkInOut, attendanceId);
            // Add employee id
            checkInOut.put("employee_id", employeeId);
            //Add checkIn to map by calculate the time to show by the user
            checkInOut.put("check_in", checkIn);
            System.out.println("Checkout Realizado.");

            return checkInOut;
        } else {
            Map checkInOut = new HashMap();
            //Adding elements to map  
            checkInOut.put("employee_id", employeeId);
            checkInOut.put("check_in", Util.dateFormatTocheck());
            Integer idAttendace = odoo.createRecord("hr.attendance", checkInOut);
            System.out.println(idAttendace);
            return checkInOut;
        }
    }

    /**
     * List with every regist of employee
     *
     * @param employeeId
     * @return
     */
    public List attendanceList(int employeeId) {

        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("employee_id", "=", employeeId));
        AllFilters.add(asList("check_out", "=", false));
        OdooXmlRpc odoo = this.connection();
        List attendanceResult = odoo.getRecords("hr.attendance", asList("id", "check_in", "check_out"), AllFilters);

        return attendanceResult;
    }

    /**
     * Method to allow search employees using a email and badge
     *
     * @param badge
     * @return List
     */
    public Integer searchEmployeeByBadgeId(String badge) {
        OdooXmlRpc odoo = this.connection();
        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("barcode", "=", badge));

        List employee = odoo.getRecords("hr.employee",
                asList("id"), AllFilters);

        if (!employee.isEmpty()) {
            HashMap emp = (HashMap) employee.get(0);
            int id = (int) emp.get("id");
            return id;
        }

        return 0;
    }

    public List searchEmployeeById(Integer id) {
        OdooXmlRpc odoo = this.connection();
        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("id", "=", id));

        List employee = odoo.getRecords("hr.employee",
                asList("id", "name", "work_email"), AllFilters);


        return employee;
    }

    /**
     * Method to allow search employees using a email
     *
     * @param email
     * @return List
     */
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
                asList("id", "name", "work_email", "x_fingerprint"), AllFilters);

        return employee;
    }

    /**
     * Method to update fingerprint by employed
     *
     * @param id
     * @param data
     */
    public void addFingerprintToEmployee(Integer id, Map data) {
        OdooXmlRpc odoo = this.connection();
        odoo.updateRecord("hr.employee", data, id);
    }

    /**
     * List employes actives
     *
     * @return List
     */
    public List allEmployee() {
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
                asList("id", "name", "work_email", "category_ids", "x_fingerprint"), AllFilters);

        return employees;
    }

    /**
     * Method that return a boolean true if the user has the tag send in
     * variable tag
     *
     * @param id employee id
     * @param tag String with the name of tag case sensitive
     * @return List
     */
    public Boolean hasTag(int id, String tag) {
        OdooXmlRpc odoo = this.connection();
        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("employee_ids", "=", id));

        List categories = odoo.getRecords("hr.employee.category",
                asList("id", "display_name"), AllFilters);

        int i = 0;
        while (i < categories.size()) {
            HashMap cat = (HashMap) categories.get(i);
            String categorieName = (String) cat.get("display_name");
            if (categorieName.matches(tag)) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * List employes actives with fingerprint registered
     *
     * @return List
     */
    public List allEmployeeWithFingerPrint() {
        OdooXmlRpc odoo = this.connection();

        List AllFilters = new ArrayList<>();
        AllFilters.add(asList("active", "=", true));
        AllFilters.add(asList("x_fingerprint", "!=", false));

        List employees = odoo.getRecords("hr.employee",
                asList("id", "name", "work_email", "x_fingerprint"), AllFilters);

        return employees;
    }

}
