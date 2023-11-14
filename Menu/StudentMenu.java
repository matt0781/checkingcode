package Menu;

import Controller.*;
import Program.*;
import Users.*;

public class StudentMenu implements UserMenuInterface {
    private Student student;

    public StudentMenu(Student student) {
        this.student = student;
    }

    private String getMenuText(){
        String menuText = "\nWhat are we doing today?\n(1)\tJoin camp\n(2)\tView available camps\n(3)\tView joined camps\n(4)\tEnquire about one Camp\n(5)\tEdit your enquiry\n(6)\tDelete your enquiry\n(7)\tView enquries\n";
        if (student.getCommittee() != null) {
            menuText += "(8)\tCommittee Menu\n(9)\tExit\nChoice: ";
        } else {
            menuText += "(8)\tExit\nChoice: ";
        }
        return menuText;
    }

    public void mainMenu(AllUser allUser, AllCamp allCamp) {
        String menuText = getMenuText();
        System.out.print(menuText);

        int choice = MainProgram.sc.nextInt();
        MainProgram.sc.nextLine();
        while (true) {
            switch (choice) {
                case 1:
                    CampMenu.joinCamp(allUser, allCamp, this.student);
                    break;
                case 2:
                    CampMenu.viewAvailableCamps(allUser, allCamp, student);
                    break; // need to change for filter
                case 3:
                    CampMenu.viewJoinedCamps(student);
                    break;
                case 4:
                    EnquiryMenu.addEnquiry(allCamp, student);
                    break;
                case 5:
                    EnquiryMenu.editEnquiry(allCamp, student);
                    break;
                case 6:
                    EnquiryMenu.deleteEnquiry(allCamp, student);
                    break;
                case 7:
                    EnquiryMenu.viewEnquiry(allCamp, student);
                    break;
                case 8:
                    if (student.getCommittee() != null) {
                        CommitteeMenu.mainMenu(allUser, allCamp, student);
                        break;
                    }
                default:
                    return;
            }
            menuText = getMenuText();
            System.out.print(menuText);
            choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
        }

    }
}