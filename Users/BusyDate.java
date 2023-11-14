package Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BusyDate implements Serializable {
    private transient ArrayList<Date> busyDates;

    public BusyDate() {
        busyDates = new ArrayList<>();
    }

    public boolean isOverlap(Date[] newEventDates) {
        if (newEventDates.length != 2) {
            return true;
        }

        Date newEventStartDate = newEventDates[0];
        Date newEventEndDate = newEventDates[1];

        for (Date busyDate : busyDates) {
            if (newEventStartDate.before(busyDate) && newEventEndDate.after(busyDate)) {
                return true;
            }
            if (newEventStartDate.after(busyDate) && newEventStartDate.before(busyDate)) {
                return true;
            }
        }

        return false;
    }

    public void addDates(Date[] newEventDates) {
        if (newEventDates.length != 2) {
            return;
        }

        Date newEventStartDate = newEventDates[0];
        Date newEventEndDate = newEventDates[1];

        if (isOverlap(newEventDates)) {
            return;
        }

        Date currentDate = newEventStartDate;
        while (currentDate.before(newEventEndDate) || currentDate.equals(newEventEndDate)) {
            busyDates.add(currentDate);
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000);
        }
    }

    // // Custom writeObject method to handle busyDates field during serialization
    // private void writeObject(java.io.ObjectOutputStream out) throws IOException {
    //     out.defaultWriteObject();
    //     out.writeObject(busyDates);
    // }

    // // Custom readObject method to handle busyDates field during deserialization
    // private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
    //     in.defaultReadObject();
    //     busyDates = (ArrayList<Date>) in.readObject();
    // }
}
