package Users;

import java.io.Serializable;
import java.util.*;
import Camp.*;

public class Attendee implements Serializable {
    Student student;
    ArrayList<Camp> camp = new ArrayList<>();
    ArrayList<Camp> campBlacklist = new ArrayList<>();

    public Attendee(Student student) {
        this.student = student;
    }

    public void registerCamp(Camp camp) {
        if (campBlacklist.contains(camp)) {
            System.out.println("Unable to join camp you previously quit...");
            return;
        }
        this.camp.add(camp);
        Collections.sort(this.camp);
    }

    public void withdrawCamp(Camp camp) {
        if (camp == null || !this.camp.contains(camp)) {
            return;
        }
        this.camp.remove(camp);
        this.campBlacklist.add(camp);

    }

    public ArrayList<Camp> getCamps() {
        return this.camp;
    }
}
