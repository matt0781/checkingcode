package Program;

import java.util.*;
import Controller.*;
import Menu.*;
import Users.*;

public class MainProgram {
    public static final String mainObj_filename = "mainObj.ser";
    public static final Scanner sc = new Scanner(System.in);
    public static Date currentDate = new Date();

    public static void main(String[] args) {
        MainObj mainObj = SerializationUtil.loadMainObj(mainObj_filename);
        if (mainObj == null) {
            return;
        }

        AllUser allUser = mainObj.getAllUser();
        AllCamp allCamp = mainObj.getAllCamp();

        allUser.printAllUser();
        allCamp.printAllCamp();

        SerializationUtil.saveObj(mainObj, mainObj_filename);

        boolean runProgram = true;
        while (runProgram) {

            System.out.println("\n  █████  █████  ███    ███ ███████ ");
            System.out.println("██      ██   ██ ████  ████ ██      ");
            System.out.println("██      ███████ ██ ████ ██ ███████ ");
            System.out.println("██      ██   ██ ██  ██  ██      ██ ");
            System.out.println(" ██████ ██   ██ ██      ██ ███████ ");
            System.out.println("");
            System.out.println("Welcome to the Camp Application and Management System");
            System.out.print("\n(1)\tLogin\n(2)\tQuit\nChoice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    User mainUser = AuthenticationMenu.loginMenu(allUser);
                    if (mainUser == null) {
                        continue;
                    }
                    SerializationUtil.saveObj(mainObj, mainObj_filename);
                    boolean loop = true;
                    String menuText = "\nMain menu\n(1)\tEdit profile\n(2)\tCamp menu\n(3)\tLogout\nChoice: ";
                    String adminMenuText = "\nMain menu\n(1)\tEdit profile\n(2)\tAdd Users\n(3)\tLogout\nChoice: ";
                    if (mainUser instanceof AdminUser) {
                        menuText = adminMenuText;
                    }
                    while (loop) {
                        System.out.print(menuText);
                        int choice2 = MainProgram.sc.nextInt();
                        MainProgram.sc.nextLine();
                        switch (choice2) {
                            case 1:
                                AuthenticationMenu.authenticationMenu(mainUser);
                                SerializationUtil.saveObj(mainObj, mainObj_filename);
                                break;
                            case 2:
                                userMenu(mainUser, allUser, allCamp);
                                SerializationUtil.saveObj(mainObj, mainObj_filename);
                                break;
                            default:
                                loop = false;
                                break;
                        }
                    }
                    SerializationUtil.saveObj(mainObj, mainObj_filename);

                    break;
                case 2:
                    runProgram = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();

    }

    private static void userMenu(User mainUser, AllUser allUser, AllCamp allCamp) {
        if (mainUser instanceof Staff) {
            Staff mainStaff = (Staff) mainUser;
            StaffMenu staffMenu = new StaffMenu(mainStaff);
            staffMenu.mainMenu(allUser, allCamp);

        } else if (mainUser instanceof Student) {
            Student mainStudent = (Student) mainUser;
            StudentMenu studentMenu = new StudentMenu(mainStudent);
            studentMenu.mainMenu(allUser, allCamp);
        } else {
            AdminMenu adminmenu = new AdminMenu();
            adminmenu.mainMenu(allUser, allCamp);
        }
        return;
    }
}
