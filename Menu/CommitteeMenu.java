package Menu;

import Controller.*;
import Program.*;
import Users.*;

public class CommitteeMenu {

    public static void mainMenu(AllUser allUser, AllCamp allCamp, Student student) {
        String menuText = "\nWhat are we doing today?\n"
                + "(1)\tSubmit suggestions\n"
                + "(2)\tEdit suggestions\n"
                + "(3)\tDelete my suggestions\n"
                + "(4)\tView my suggestions\n"
                + "(5)\tRespond to enquiries\n"
                + "(6)\tGenerate report\n"
                + "(7)\tExit\n"
                + "Choice: ";
        int choice;
        do {
            System.out.print(menuText);

            choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
            switch (choice) {
                case 1:
                    SuggestionMenu.addSuggestion(student);
                    break;
                case 2:
                    SuggestionMenu.editSuggestion(student);
                    break;
                case 3:
                    SuggestionMenu.deleteSuggestion(student);
                    break;
                case 4:
                    SuggestionMenu.viewSuggestion(student);
                    break;
                case 5:
                    EnquiryMenu.replyEnquiry(allCamp, student);
                    break;
                case 6: // generate report
                    ReportMenu.displayReportMenu(student);
                    break;
                case 7: // quit program
                    return;
                default:
                    System.out.println("Sorry, please enter a valid input.");

            }
        } while (true);

    }
}
