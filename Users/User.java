package Users;

import java.io.Serializable;

public abstract class User implements Serializable {
    private Authentication authentication;
    private String name;
    private String email;
    private Faculty.FACULTY_TYPE facultyType;
    int id;
    static int nextStaffId = 100;
    static int nextStudentId = 200;
    private Boolean loggedIn = false;

    public boolean getLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn(Boolean value) {
        this.loggedIn = value;
    }

    public Boolean equals(User anotherUser) {
        return this.id == anotherUser.getId();
    }

    public int getId() {
        return this.id;
    }

    public Faculty.FACULTY_TYPE getFaculty() {
        return this.facultyType;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }


    public Authentication getAuthentication() {
        return this.authentication;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
        this.authentication = new Authentication(email.substring(0, email.indexOf("@")));
    }

    public void setFaculty(Faculty.FACULTY_TYPE facultyType) {
        this.facultyType = facultyType;
    }

    public String getUserInfo() {
        return ("| ID: " + id + " | Name: " + name + " | Email: " + email + " | Faculty: " + facultyType);
    }
}
