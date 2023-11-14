package Users;

public class AdminUser extends User {
    public AdminUser(String name, String email, Faculty.FACULTY_TYPE faculty) {
        this.setName(name);
        this.setEmail(email);
        this.setFaculty(faculty);
    }

    public String getUserInfo() {
        return "User: Admin " + super.getUserInfo();
    }
}
