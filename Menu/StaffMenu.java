package Menu;

import Controller.*;
import Program.*;
import Users.*;

public class StaffMenu implements UserMenuInterface {
    private Staff staff;

    public StaffMenu(Staff staff) {
        this.staff = staff;
    }

    public void mainMenu(AllUser allUser, AllCamp allCamp) {
        String menuText = "\nWhat are we doing today?\n"
                + "(1)\tCreate camp\n"
                + "(2)\tView all camps\n"
                + "(3)\tEdit Camp\n"
                + "(4)\tView Enquiry\n"
                + "(5)\tReply Enquiry\n"
                + "(6)\tDelete Camp\n"
                + "(7)\tView students in camp\n"
                + "(8)\tGenerate Report\n"
                + "(9)\tApprove Suggestion\n"
                + "(10)\tView Suggestion\n"
                + "(11)\tExit\nChoice: ";
        System.out.print(menuText);

        int choice = MainProgram.sc.nextInt();
        MainProgram.sc.nextLine();
        while (true) {
            switch (choice) {
                case 1:
                    CampMenu.createCamp(allUser, allCamp, this.staff);
                    break;
                case 2:
                    CampMenu.selectCamp(allCamp.getCamps());
                    break;
                case 3:
                    CampMenu.editCamps(this.staff);
                    break;
                case 4:
                    EnquiryMenu.viewEnquiry(allCamp, staff);
                    break;
                case 5:
                    EnquiryMenu.replyEnquiry(allCamp, staff);
                    break;
                case 6:
                    CampMenu.deleteCamp(allUser, allCamp, staff);
                    break;
                case 7:
                    CampMenu.viewCampMembers(allUser, allCamp);
                    break;
                case 8:
                    ReportMenu.displayReportMenu(staff);
                    break;
                case 9:
                    SuggestionMenu.approveSuggestion(staff);
                    break;
                case 10:
                    SuggestionMenu.viewSuggestion(staff);
                    break;
                default:
                    return;
            }
            System.out.print(menuText);
            choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
        }

    }

}
