package Menu;

import java.util.*;
import Camp.*;
import Comment.*;
import Program.*;
import Users.*;

public class SuggestionMenu {

    private static Suggestion selectSuggestion(Camp camp, User user) {

        if (camp == null) {
            return null;
        }
        ArrayList<Comment> suggestionArrayList = camp.getSuggestion();
        if (suggestionArrayList.isEmpty()) {
            System.out.println("No available suggestions!");
            return null;
        }
        if (user instanceof Student) {
            Student student = (Student) user;
            ArrayList<Comment> studentSuggestionArrayList = new ArrayList<>();
            for (Comment comment : suggestionArrayList) {
                if (student.equals(comment.getStudent())) {
                    studentSuggestionArrayList.add(comment);
                }
            }
            suggestionArrayList = studentSuggestionArrayList;
        }
        System.out.println("\nSelect Suggestion:");

        int i = 1;
        for (Comment comment : suggestionArrayList) {
            System.out.printf("\n%d ->\t%s\n", i, comment.getComment());
            System.out.println();
            i += 1;
        }
        System.out.printf("%d ->\tExit\n", i);
        System.out.print("\nChoice: ");
        int choice = MainProgram.sc.nextInt();
        MainProgram.sc.nextLine();
        if (choice == i) {
            return null;
        }
        while (choice > suggestionArrayList.size() || choice <= 0) {
            System.out.print("Invalid input\nChoice: ");
            choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
        }
        Comment targetComment = suggestionArrayList.get(choice - 1);
        if (targetComment instanceof Suggestion) {
            Suggestion targetSuggestion = (Suggestion) targetComment;
            if (targetSuggestion.getProcessed()) {
                System.out.printf("Suggestion has already been processed. It has been %s\n",
                        targetSuggestion.getStatus().toString());
                return null;
            } else {
                return targetSuggestion;
            }
        }
        return null;

    }

    public static void addSuggestion(Student student) {
        System.out.print("Enter your suggestion: ");
        String comment = MainProgram.sc.nextLine();
        Committee committee = student.getCommittee();
        if (committee == null) {
            return;
        }
        committee.createSuggestion(comment);
        committee.addPoint();
        System.out.printf("Suggestion submitted\n");
        return;
    }

    public static void editSuggestion(Student student) {
        if (student == null) {
            return;
        }
        Committee committee = student.getCommittee();
        if (committee == null) {
            return;
        }
        Camp camp = committee.getCamp();
        if (camp == null) {
            return;
        }
        Suggestion selectedSuggestion = selectSuggestion(camp, student);
        if (selectedSuggestion == null) {
            return;
        }

        System.out.printf("Current suggestion:\n%s", selectedSuggestion.getComment());
        System.out.printf("Enter your new suggestion: ");
        String newSuggestion = MainProgram.sc.nextLine();
        selectedSuggestion.editComment(newSuggestion);
        System.out.println("Your suggestion has been updated successfully");
    }

    public static void deleteSuggestion(Student student) {

        if (student == null) {
            return;
        }
        Committee committee = student.getCommittee();
        if (committee == null) {
            return;
        }
        Camp camp = committee.getCamp();
        if (camp == null) {
            return;
        }
        Suggestion selectedSuggestion = selectSuggestion(camp, student);
        if (selectedSuggestion == null) {
            return;
        }

        System.out.printf("Selected suggestion:\n%s", selectedSuggestion.getComment());

        System.out.printf("Are you sure you want to delete this suggestion? Y/N\tChoice: ");
        String choice = MainProgram.sc.nextLine().toLowerCase();
        if (choice.equals("y")) {
            camp.removeComment(selectedSuggestion);
            committee.minusPoint();
            System.out.println("Suggestion has been deleted");
        } else if (choice.equals("n")) {
            System.out.println("Suggestion not deleted");
        } else {
            System.out.println("Unrecognised input, returning main menu...");
        }
        return;
    }

    public static void approveSuggestion(Staff staff) {
        if (staff == null) {
            return;
        }
        Camp selectedCamp = CampMenu.selectCamp(staff.getCampsCreated());
        Suggestion selectedSuggestion = selectSuggestion(selectedCamp, staff);
        if (selectedSuggestion == null) {
            return;
        }
        System.out.printf("Approve? (Y/N)\tChoice: ");
        String choice = MainProgram.sc.nextLine().toLowerCase();
        if (choice.equals("y")) {
            selectedSuggestion.setProcessed(Suggestion.STATUS.APPROVED);
            System.out.println("Suggestion has been approved");
        } else if (choice.equals("n")) {
            selectedSuggestion.setProcessed(Suggestion.STATUS.REJECTED);
            System.out.println("Suggestion has been rejected");
        } else {
            System.out.println("Unrecognised input, returning main menu...");
        }
        return;
    }

    public static void viewSuggestion(User user) {
        Camp targetCamp = null;
        Boolean isStudent = false;
        Student student = null;
        if (user instanceof Student) {
            student = (Student) user;
            Committee committee = student.getCommittee();
            if (committee == null) {
                return;
            }
            targetCamp = committee.getCamp();
            isStudent = true;
        } else if (user instanceof Staff) {
            Staff staff = (Staff) user;
            targetCamp = CampMenu.selectCamp(staff.getCampsCreated());
        }
        if (targetCamp == null) {
            return;
        }
        ArrayList<Comment> commentArrayList = targetCamp.getSuggestion();
        
        if (commentArrayList == null || commentArrayList.size() == 0) {
            System.out.println("This camp has no suggestions!");
            return;
        }
        int i = 1;
        for (Comment comment : commentArrayList) {
            if (isStudent && !(student.equals(comment.getStudent()))) {
                continue;
            }
            System.out.printf("\n%d ->\t%s", i, comment.getComment());
            i += 1;
        }
    }
}