package Controller;

import java.io.Serializable;
import java.util.*;
import Camp.*;

public class AllCamp implements Serializable {
    private ArrayList<Camp> allCamp = new ArrayList<>();

    public void addCamp(Camp camp) {
        allCamp.add(camp);
        Collections.sort(allCamp);
    }

    public void deleteCamp(Camp camp) {
        allCamp.remove(camp);
        camp.deleteCamp(camp);
    }

    public ArrayList<Camp> getCamps() {
        return this.allCamp;
    }

    public ArrayList<Camp> filterCamp() {
        ArrayList<Camp> campArrayList = new ArrayList<>();
        for (Camp camp : allCamp) {
            if (camp == null) {
                continue;
            }
            if (camp.getVisbility()) {
                campArrayList.add(camp);
            }
        }
        Collections.sort(campArrayList);
        return campArrayList;
    }

    public void printAllCamp() {
        for (Camp camp : allCamp) {
            System.out.println(camp.getBasicCampDetail());
        }
    }

}
