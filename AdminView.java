import java.util.*;

import Controller.*;
import Program.*;
import Users.*;

public class AdminView {
    public static void main(String[] args) {
        AllUser allUser = new AllUser();
        ArrayList<User> newuser = ExcelReader.readExcel("staff_list.xlsx", ExcelReader.ROLE.STAFF);
        allUser.addMultiUser(newuser);
        newuser = ExcelReader.readExcel("student_list.xlsx", ExcelReader.ROLE.STUDENT);
        allUser.addMultiUser(newuser);
        MainObj mainObj = new MainObj(allUser);
        SerializationUtil.saveObj(mainObj, MainProgram.mainObj_filename);
    }

}
