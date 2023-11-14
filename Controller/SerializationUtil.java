package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Users.*;

public class SerializationUtil {

    private static void deleteFile(String filePath) {
        File myObj = new File(filePath);
        myObj.delete();
    }

    public static void saveObj(Object obj, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            deleteFile(filePath);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addAdmin(MainObj mainObj) {
        AdminUser newuser = new AdminUser("admin", "admin@ntu.edu.sg", Faculty.FACULTY_TYPE.UNIVERSE);
        if (mainObj != null && newuser != null) {
            AllUser allUser = mainObj.getAllUser();
            if (allUser != null) {
                allUser.addUser(newuser);
            }
        }
    }

    public static MainObj loadMainObj(String filePath) {
        File myObj = new File(filePath);
        MainObj mainObj = null;
        if (!myObj.exists()) {
            mainObj = new MainObj();
            addAdmin(mainObj);
            SerializationUtil.saveObj(mainObj, filePath);
        } else {
            try {
                FileInputStream fileIn = new FileInputStream(filePath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                mainObj = (MainObj) objectIn.readObject();
                objectIn.close();
                fileIn.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return mainObj;

    }
}
