package Controller;

import java.io.Serializable;

public class MainObj implements Serializable {
    private AllUser allUser;
    private AllCamp allCamp;

    public MainObj() {
        allCamp = new AllCamp();
        allUser = new AllUser();
    }

    public MainObj(AllUser allUser) {
        this.allUser = allUser;
        allCamp = new AllCamp();
    }

    public AllUser getAllUser() {
        return this.allUser;
    }

    public AllCamp getAllCamp() {
        return this.allCamp;
    }
}
