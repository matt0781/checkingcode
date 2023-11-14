package Camp;

import java.io.Serializable;
import java.util.ArrayList;

import Users.*;

public class CampStudentManager implements Serializable{
    private ArrayList<Student> attendee = new ArrayList<>();
    private ArrayList<Student> committee = new ArrayList<>();

    protected int getAttendeeSize() {
        return this.attendee.size();
    }

    protected int getCommitteeSize() {
        return this.committee.size();
    }

    public void deleteCamp(Camp camp) {
        if (camp == null) {
            System.out.println("Error deleting camp");
            return;
        }
        for (Student student : attendee) {
            student.deleteCamp(camp);
        }
        for (Student student : committee) {
            student.deleteCamp(camp);
        }
    }

    protected void addAttendee(Student student) {
        if (student == null) {
            System.out.println("Error adding student");
            return;
        }
        if (!attendee.contains(student)) {
            attendee.add(student);
        } else {
            System.out.println("Student is already in camp");
        }
    }

    protected void addCommittee(Student student) {
        if (student == null) {
            System.out.println("Error adding student");
            return;
        }
        if (!committee.contains(student)) {
            committee.add(student);
        } else {
            System.out.println("Student is already in camp");
        }
    }

    protected void removeAttendee(Student student) {
        if (student == null) {
            System.out.println("Error removing student");
            return;
        }
        if (attendee.contains(student)) {
            attendee.remove(student);
        } else {
            System.out.println("Student is not in camp");
        }
    }

    protected ArrayList<Student> getAttendee() {
        return attendee;
    }

    protected ArrayList<Student> getCommittee() {
        return committee;
    }

    protected String getStudents(){
        String info = "\nCommittee (" + committee.size() + "):\n";
        for(Student student : committee){
            info += "\t" + student.getName() + "\n";
        }

        info += "\nAttendee (" + attendee.size() + "):\n";
        for(Student student : attendee){
            info += "\t" + student.getName() + "\n";
        }
        
        return info;
    }

}
