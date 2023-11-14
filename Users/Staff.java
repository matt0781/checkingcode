package Users;

import java.util.*;
import Camp.*;


public class Staff extends User{
    private ArrayList<Camp> campsCreated = new ArrayList<Camp>();
    
    public Staff(String name, String email, Faculty.FACULTY_TYPE faculty){
        this.setName(name);
        this.setEmail(email);
        this.setFaculty(faculty);
        this.id = User.nextStaffId;
        User.nextStaffId += 1;
    }

    public void setVisibility(Camp camp, Boolean value){
        if(camp == null){return;}
        camp.setVisibility(value);
    }

    public void addCamp(Camp camp){
        campsCreated.add(camp);
        Collections.sort(campsCreated);
    }

    public void deleteCamp(Camp camp){
        if(campsCreated.contains(camp)){
            campsCreated.remove(camp);
        }  
    }
    public ArrayList<Camp> getCampsCreated(){return this.campsCreated;}

    public String getUserInfo() {
        return "User: Staff " + super.getUserInfo();
    }
    // @Override
    // public boolean equals(Object o) {
    //     if (this == o) return true;
    //     if (o == null || getClass() != o.getClass()) return false;
    //     Staff staff = (Staff) o;
    //     return id == staff.id;
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(id);
    // }
}
