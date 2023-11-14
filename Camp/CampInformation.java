package Camp;

import java.io.Serializable;
import java.util.*;
import Users.*;

public class CampInformation implements Serializable {
    private String name;
    private Date[] campDate;
    private int totalSlot;
    private String location;
    private Date registrationDeadline;
    private Staff staff;
    private ArrayList<Faculty.FACULTY_TYPE> faculty;
    private String description;
    public static final int MAX_COMMITTEE_SLOTS = 10;

    public CampInformation(String name, Date startDate, Date endDate, int totalSlot, String location,
            Date registrationDeadline, Staff staff, ArrayList<Faculty.FACULTY_TYPE> faculty,
            String description) {
        this.name = name;
        this.campDate = new Date[2];
        this.campDate[0] = startDate;
        this.campDate[1] = endDate;
        this.totalSlot = totalSlot;
        this.location = location;
        this.registrationDeadline = registrationDeadline;
        this.staff = staff;
        this.faculty = faculty;
        this.description = description;
    }

    public void setTotalSlot(int newval) {
        if (newval < 0) {
            System.out.println("Error: Total slots cannot be negative.");
        } else {
            this.totalSlot = newval;
            System.out.println("Total slots updated to " + newval);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String newname) {
        System.out.println(newname);
        this.name = newname;
    }

    public Date[] getCampDate() {
        return campDate;
    }

    public void setCampDate(Date[] campDate) {
        this.campDate = campDate;
    }

    public int getTotalSlot() {
        return totalSlot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public ArrayList<Faculty.FACULTY_TYPE> getFaculty() {
        return faculty;
    }

    public void setFaculty(ArrayList<Faculty.FACULTY_TYPE> faculty) {
        this.faculty = faculty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCampInformation() {
        StringBuilder info = new StringBuilder();
        info.append("Camp Name: ").append(name).append("\n");
        info.append("Camp Dates: ").append(campDate[0]).append(" - ").append(campDate[1]).append("\n");
        info.append("Total Slots: ").append(totalSlot).append("\n");
        info.append("Location: ").append(location).append("\n");
        info.append("Registration Deadline: ").append(registrationDeadline).append("\n");
        info.append("Staff: ").append(staff.getName()).append("\n");
        info.append("Faculties: ");
        for (Faculty.FACULTY_TYPE facultyType : faculty) {
            info.append(facultyType.toString()).append(", ");
        }
        info.append("\n");
        info.append("Description: ").append(description).append("\n");

        return info.toString();
    }

    public ArrayList<Faculty.FACULTY_TYPE> getFaculties() {
        return this.faculty;
    }
}
