package Controller;

import java.io.Serializable;
import java.util.*;
import Users.*;

public class AllUser implements Serializable {
    private ArrayList<User> userArray = new ArrayList<>();

    public void addMultiUser(ArrayList<User> newArr) {
        for (User usr : newArr) {
            userArray.add(usr);
        }
    }

    public void addUser(User newUser) {
        userArray.add(newUser);
    }

    public void printAllUser() {
        for (User user : userArray) {
            System.out.println(user.getUserInfo());
        }
    }

    public ArrayList<User> getUserArray() {
        return this.userArray;
    }
}
