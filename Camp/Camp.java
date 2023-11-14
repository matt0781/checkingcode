package Camp;

import java.io.Serializable;
import java.util.*;
import Comment.*;
import Users.*;

public class Camp implements Serializable, Comparable<Camp> {

    private CampInformation campInformation;
    private CampStudentManager campStudentManager = new CampStudentManager();
    private CampCommentManager campCommentManager = new CampCommentManager();
    private Boolean visible;
    private int campID;
    private static int nextID = 1;

    public Camp(String name, Date startDate, Date endDate, int totalSlot, String location, Date registrationDeadline,
            Staff staff, ArrayList<Faculty.FACULTY_TYPE> faculty, Boolean visible, String description) {
        this.campInformation = new CampInformation(name, startDate, endDate, totalSlot, location, registrationDeadline,
                staff, faculty, description);
        this.visible = visible;
        campID = nextID;
        nextID += 1;
    }

    public boolean hasEmptySlot() {
        return campStudentManager.getAttendeeSize() + campStudentManager.getCommitteeSize() < campInformation
                .getTotalSlot();
    }

    public void deleteCamp(Camp camp) {
        Staff staff = campInformation.getStaff();
        if (staff != null) {
            staff.deleteCamp(camp);
        }
        campStudentManager.deleteCamp(camp);
    }

    public void addAttendee(Student student) {
        if (student == null) {
            return;
        }
        if (this.hasEmptySlot()) {
            campStudentManager.addAttendee(student);
        }
    }

    public void removeAttendee(Student student) {
        if (student == null) {
            return;
        }
        if (campStudentManager.getAttendeeSize() > 0) {
            campStudentManager.removeAttendee(student);
        }
    }

    public void addCommittee(Student student) {
        if (this.hasEmptySlot() && campStudentManager.getCommitteeSize() < CampInformation.MAX_COMMITTEE_SLOTS) {
            campStudentManager.addCommittee(student);
        }
    }

    public void setVisibility(Boolean value) {
        this.visible = value;
    }

    public Boolean getVisbility() {
        return this.visible;
    }

    public ArrayList<Student> getAttendee() {
        return campStudentManager.getAttendee();
    }

    public ArrayList<Student> getCommittee() {
        return campStudentManager.getCommittee();
    }

    public ArrayList<Comment> getSuggestion() {
        return campCommentManager.getSuggestion();
    }

    public ArrayList<Comment> getEnquiry() {
        return campCommentManager.getEnquiry();
    }

    public CampInformation getCampInformation() {
        return this.campInformation;
    }

    public String getBasicCampDetail(){return campInformation.getCampInformation();}

    public String getCampStudents() {
        return campStudentManager.getStudents();
    }

    public String getCampComments(){
        return campCommentManager.getComments();
    }

    public void addComment(Comment comment) {
        if (comment == null) {
            return;
        }
        if (comment instanceof Suggestion) {
            campCommentManager.addSuggestion(comment);
        } else {
            campCommentManager.addEnquiry(comment);
        }
    }

    public void removeComment(Comment comment) {
        if (comment == null) {
            return;
        }
        if (comment instanceof Suggestion) {
            campCommentManager.removeSuggestion(comment);
        } else if (comment instanceof Enquiry) {
            campCommentManager.removeEnquiry(comment);
        }
    }

    protected int getCampID() {
        return this.campID;
    }

    public Boolean equalsCamp(Camp camp) {
        if (camp == null) {
            return false;
        }
        return this.campID == camp.getCampID();
    }

    

    @Override
    public int compareTo(Camp otherCamp) {
        return this.campInformation.getName().compareTo(otherCamp.campInformation.getName());
    }
}
