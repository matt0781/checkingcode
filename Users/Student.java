package Users;

import java.util.*;
import Camp.*;
import Controller.*;

public class Student extends User {
    Committee studentCommittee;
    Attendee studentAttendee;
    BusyDate busyDate = new BusyDate();
    

    public Student(String name, String email, Faculty.FACULTY_TYPE faculty) {
        this.setName(name);
        this.setEmail(email);
        this.setFaculty(faculty);
        this.id = User.nextStudentId;
        User.nextStudentId += 1;
    }

    public void addAttendeeCamp(Camp camp) {
        if (camp == null) {
            return;
        }
        camp.addAttendee(this);
        if (studentCommittee == null) {
            this.studentAttendee = new Attendee(this);
        }
        this.studentAttendee.registerCamp(camp);
    }

    public void addCommitteeCamp(Camp camp) {
        if (camp == null) {
            return;
        }
        camp.addCommittee(this);
        this.studentCommittee = new Committee(camp, this);
    }

    public boolean registerCamp(Camp camp, boolean attendee) {
        if (camp == null) {
            return false;
        }
        CampInformation campInformation = camp.getCampInformation();
        if (campInformation == null) {
            return false;
        }
        if (busyDate.isOverlap(campInformation.getCampDate())) {
            System.out.println("There's an overlap in your schedule, registration failed!");
            return false;
        }
        if (attendee) {
            this.addAttendeeCamp(camp);
        } else {
            this.addCommitteeCamp(camp);
        }
        busyDate.addDates(campInformation.getCampDate());

        System.out.printf("Successfully joined as %s\n", attendee ? "attendee" : "committee member");
        return true;
    }

    public ArrayList<Camp> getAvailCamp(AllCamp allCamp) {
        if (allCamp == null) {
            return null;
        }
        ArrayList<Camp> campArrayList = allCamp.filterCamp();
        return campArrayList;
    }

    public void withdrawAttendeeCamp(Camp camp) {
        if (camp == null) {
            return;
        }
        camp.removeAttendee(this);
        studentAttendee.withdrawCamp(camp);
    }

    public Attendee getAttendee() {
        return this.studentAttendee;
    }

    public Committee getCommittee() {
        return this.studentCommittee;
    }

    public boolean inCommittee() {
        return this.studentCommittee == null;
    }

    public void deleteCamp(Camp camp) {
        if (studentCommittee != null) {
            if (camp.equalsCamp(this.studentCommittee.getCamp())) {
                this.studentCommittee = null;
            }
        }
        if (studentAttendee != null) {
            ArrayList<Camp> attendeeCampList = this.studentAttendee.getCamps();
            if (attendeeCampList == null) {
                return;
            }
            try {
                attendeeCampList.remove(camp);
            } catch (Exception e) {
                System.out.println("Camp does not exist in list");
            }
        }
    }

    public String getUserInfo() {
        return "User: Student " + super.getUserInfo();
    }
}
