# checkMeAttendance

You can connect with your odoo host and to do check in and checkout

# Odoo Settings

You need to add a simple field to odoo model
  
The following steps describe the basic case about how to add a field to an odoo model. In this way you are also sure that you will not loose your changes when in future will-update odoo.

1. Activate developer mode
2. Go to **Configuration** -> **technical** -> **Database structure**->**Models**
3. Open for hr.employee and **edit**
4. Add a textual field **x_fingerprint** in list **Fields** used **Add a line** for create the field
5. Please create the field with **Text** type
6. Save & Close

Now when you want to save a fingerprint for one user the java application will use the field created above.

# Validation based in Odoo Tags

You need to create different tags in you Odoo server for use this application

This Tags must be assigne to each user for use the aplication, you would deside which users will be use it.
 

## Description

 - Manual Attendance: Tag that allows to user to use the code Badge ID for check in.
 - Fingerprint Register: Tag that allows to chosse which user will have the ability for register fingerprint.
 - Fingerprint: Tag that allows a user to be listed for fingerprint register.
