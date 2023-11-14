package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import Camp.*;
import Comment.Comment;
import Users.*;
import Comment.Enquiry;

public class Report {

    public enum ListReportType {
        ATTENDEE,
        COMMITTEE,
        BOTH
    }

    public static void generateListReport(Camp camp, ListReportType reportType) {
        if (camp == null || reportType == null) {
            return;
        }
        String fileName = "report.txt";
        // Get the current working directory
        String workingDir = System.getProperty("user.dir");
        // Combine them to create the file path
        String filePath = Paths.get(workingDir, fileName).toString();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(camp.getCampInformation().getCampInformation());

            if (reportType == ListReportType.ATTENDEE || reportType == ListReportType.BOTH) {
                ArrayList<Student> attendees = camp.getAttendee();
                writer.append("\nAttendee list:\n");
                int i = 1;
                for (Student attendee : attendees) {
                    writer.append(i++ + ". " + attendee.getUserInfo() + "\n");
                }
            }

            if (reportType == ListReportType.COMMITTEE || reportType == ListReportType.BOTH) {
                ArrayList<Student> committees = camp.getCommittee();
                writer.append("\nCommittee list:\n");
                int j = 1;
                for (Student committee : committees) {
                    writer.append(j++ + ". " + committee.getUserInfo() + "\n");
                }
            }

            System.out.println("Report is generated successfully at " + filePath + ".");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }

    }

    public static void generatePerformanceReport(Camp camp) {
        if (camp == null) {
            return;
        }
        ArrayList<Student> committees = camp.getCommittee();

        String fileName = "performance_report.txt";
        // Get the current working directory
        String workingDir = System.getProperty("user.dir");
        // Combine them to create the file path
        String filePath = Paths.get(workingDir, fileName).toString();

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(camp.getCampInformation().getCampInformation());
            writer.append("\nCommittee Performance List:\n");
            int i = 1;
            for (Student committee : committees) {
                writer.append(i++ + ". " + committee.getUserInfo() + ", Points: " + committee.getCommittee().getPoints()
                        + "\n");
            }

            System.out.println("Report is generated successfully at " + filePath + ".");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }
    }

    public static void generateStudentEnquiryReport(Camp camp){
        if (camp == null) {
            return;
        }

        String fileName = "student_enquiry_report.txt";
        // Get the current working directory
        String workingDir = System.getProperty("user.dir");
        // Combine them to create the file path
        String filePath = Paths.get(workingDir, fileName).toString();

        ArrayList<Comment> enquiries = camp.getEnquiry();
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(camp.getCampInformation().getCampInformation());

            if (enquiries != null){
                for(Comment enquiry : enquiries ){
                    Enquiry en = (Enquiry) enquiry;
                    writer.append(en.getComment() + "\n");
                }
            }
            else{
                writer.append("There is no enquiry for this camp.\n");
            }

            System.out.println("Report is generated successfully at " + filePath + ".");

        } catch (IOException e) {
            System.out.println("An error occurred while writing the report: " + e.getMessage());
        }

    }

}