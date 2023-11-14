package Menu;

import java.util.*;
import org.apache.poi.EmptyFileException;
import Controller.*;
import Program.*;
import Users.*;

import java.io.File;

public class AdminMenu implements UserMenuInterface {

    public void mainMenu(AllUser allUser, AllCamp allCamp) {
        System.out.println("\nList of file that can be added:");
        Integer profilechoice = -1;
        String selectedFile = null;
        int fileChoice = -1;

        File dir = new File("./xlsxLISTfolder");
        File[] files = dir.listFiles((d, name) -> name.endsWith(".xlsx"));
        if (files.length == 0) {
            System.out.println("Error! No xlsx file found!");
            return;
        }
        if (files != null && files.length > 0) {
            int i = 1;
            for (; i <= files.length; i++) {
                System.out.printf("(%d)\t%s\n", i, files[i - 1].getName());
            }
            System.out.printf("(%d)\tExit\n", i);

        }

        do {
            System.out.print("\nSelect a file by number: ");
            fileChoice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();

        } while (!(fileChoice > 0 && fileChoice <= files.length + 1));
        if (fileChoice == files.length + 1) {
            return;
        }
        selectedFile = files[fileChoice - 1].getName();
        System.out.println("Selected file: " + selectedFile);
        String filePath = "./xlsxLISTfolder/" + selectedFile;

        do {
            System.out.printf("\nSelect type:\n(1)\tStaff\n(2)\tStudent\n" + //
                    "Choice: ");
            profilechoice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
        } while (profilechoice != 1 && profilechoice != 2);

        ExcelReader.ROLE role = ExcelReader.ROLE.STAFF;
        if (profilechoice == 2) {
            role = ExcelReader.ROLE.STUDENT;
        }
        try {
            ArrayList<User> newuser = ExcelReader.readExcel(filePath, role);
            allUser.addMultiUser(newuser);
            SerializationUtil.saveObj(allUser, MainProgram.mainObj_filename);
            System.out.printf("\nSuccesfully added\n");
            return;

        } catch (EmptyFileException e) {
            System.out.println("No user information found in selected file.");
            return;
        } catch (Exception e) {
            System.out.printf("\nOperation failed!\n");
            return;
        }
    }
}