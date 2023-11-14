package Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import Camp.*;
import Comment.*;
import Controller.*;
import Program.*;
import Users.*;

public class EnquiryMenu {

    public static void viewEnquiry(AllCamp allCamp, User user) {
        Camp selectedCamp = EnquiryMenu.selectCampToEnquire(allCamp, user);
        if (selectedCamp == null) {
            return;
        }

        ArrayList<Comment> enquiryArrayList = selectedCamp.getEnquiry();
        if (enquiryArrayList.isEmpty()) {
            System.out.println("This camp has no enquiries!");
            return;
        }

        Boolean isStudent = false;
        Student student = null;
        if (user instanceof Student) {
            isStudent = true;
            student = (Student) user;
        }
        int i = 1;
        for (Comment comment : enquiryArrayList) {
            if (isStudent && !student.equals(comment.getStudent())) {
                continue;
            }
            System.out.printf("\n%d ->\t%s", i, comment.getComment());
            i += 1;
        }

    }

    private static Enquiry selectEnquiry(Camp camp, User user) {
        if (camp == null) {
            return null;
        }
        ArrayList<Comment> enquiryArrayList = camp.getEnquiry();
        if (enquiryArrayList.isEmpty()) {
            System.out.println("No available enquiries!");
            return null;
        }
        if (user instanceof Student) {
            Student student = (Student) user;
            ArrayList<Comment> studentEnquirArrayList = new ArrayList<>();
            for (Comment comment : enquiryArrayList) {
                if (student.equals(comment.getStudent())) {
                    studentEnquirArrayList.add(comment);
                }
            }
            enquiryArrayList = studentEnquirArrayList;
        }
        System.out.println("\nSelect Enquiry:");

        int i = 1;
        for (Comment comment : enquiryArrayList) {
            System.out.printf("\n%d ->\t%s", i, comment.getComment());
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
        while (choice > enquiryArrayList.size() || choice <= 0) {
            System.out.print("Invalid input\nChoice: ");
            choice = MainProgram.sc.nextInt();
            MainProgram.sc.nextLine();
        }
        Comment targetComment = enquiryArrayList.get(choice - 1);
        if (targetComment instanceof Enquiry) {
            Enquiry targetEnquiry = (Enquiry) targetComment;

            return targetEnquiry;

        }
        return null;

    }

    public static Camp selectCampToEnquire(AllCamp allCamp, User user) {

        ArrayList<Camp> campArrayList = null;
        Camp selectedCamp = null;
        if (user instanceof Staff) {
            campArrayList = ((Staff) user).getCampsCreated();
            if (campArrayList == null) {
                return null;
            }
            selectedCamp = CampMenu.selectCamp(campArrayList);
        } else if (user instanceof Student) {
            campArrayList = FilterCamp.getAvailableCamps(allCamp, ((Student) user));
            if (campArrayList == null) {
                System.out.println("No camp available for enquiry\n");
                return null;
            }
            selectedCamp = CampMenu.selectCamp(campArrayList);
        }
        return selectedCamp;
    }

    public static void addEnquiry(AllCamp allCamp, Student student) {
        Camp camp = EnquiryMenu.selectCampToEnquire(allCamp, student);
        if (camp == null)
            return;

        System.out.print("Enter enquiry: ");

        String comment = MainProgram.sc.nextLine();
        Enquiry enquiry = new Enquiry(student, camp, comment);
        camp.addComment(enquiry);
        System.out.println("Enquiry raised");
        return;
    }

    public static void editEnquiry(AllCamp allCamp, Student student) {
        Camp camp = EnquiryMenu.selectCampToEnquire(allCamp, student);
        if (camp == null)
            return;
        Comment enquiry = EnquiryMenu.selectEnquiry(camp, student);
        if (enquiry == null)
            return;
        if (enquiry.getProcessed()) {
            System.out.println("Enquiry has already been processed!");
            return;
        }
        System.out.println("Enquiry to edit:\n" + enquiry.getComment());
        System.out.print("Enquiry to replace with: ");

        String editedEnquiry = MainProgram.sc.nextLine();
        enquiry.editComment(editedEnquiry);
        System.out.println("\nEnquiry successfully edited!");
        return;
    }

    public static void deleteEnquiry(AllCamp allCamp, Student student) {
        Camp camp = EnquiryMenu.selectCampToEnquire(allCamp, student);
        if (camp == null)
            return;
        Comment enquiry = EnquiryMenu.selectEnquiry(camp, student);
        if (enquiry == null)
            return;
        if (enquiry.getProcessed()) {
            System.out.println("Enquiry has already been processed!");
            return;
        }
        System.out.print("Are you sure to delete this enquiry? Y/N\tChoice: ");

        String choice = MainProgram.sc.nextLine();
        if (choice.toLowerCase().equals("y")) {
            camp.removeComment(enquiry);
            System.out.println("\n Enquiry deleted!");
        } else {
            System.out.println("Enquiry deletion failed.");
        }
        return;
    }

    public static void replyEnquiry(AllCamp allCamp, User user) {
        Camp selectedCamp = null;
        Committee committee = null;
        if (user instanceof Staff) {
            selectedCamp = EnquiryMenu.selectCampToEnquire(allCamp, user);
        } else if (user instanceof Student) {
            committee = ((Student) user).getCommittee();
            if (committee == null) {
                return;
            }
            selectedCamp = committee.getCamp();
        }
        if (selectedCamp == null) {
            return;
        }
        Enquiry selectedEnquiry = EnquiryMenu.selectEnquiry(selectedCamp, null);
        if (selectedEnquiry == null) {
            return;
        }
        if (user instanceof Student && selectedEnquiry.getStudent().equals((Student) user)) {
            System.out.println("Not allowed to reply to own enquiry!");
            return;
        }
        System.out.printf("Enter reply: ");
        String replyInput = MainProgram.sc.nextLine();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        String formattedReply = user.getName() + " (" + formattedDateTime + ") : " + replyInput;
        selectedEnquiry.addReply(formattedReply);
        return;
    }
}