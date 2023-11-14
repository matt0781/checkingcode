package Users;

import java.io.Serializable;
import Camp.*;
import Comment.*;

public class Committee implements Serializable {
    private Camp camp;
    private Student student;
    private int points = 0;

    public Committee(Camp camp, Student student) {
        this.camp = camp;
        this.student = student;
    }



    public void createSuggestion(String comment) {
        Suggestion suggestion = new Suggestion(this.student, this.camp, comment);
        this.camp.addComment(suggestion);
    }


    public void addPoint() {
        this.points++;
        System.out.printf("You have been awarded one point\nYour points: %d\n", getPoints());
    }

    public void minusPoint() {
        this.points--;
        System.out.printf("One point has been deducted\nYour points: %d\n", getPoints());
    }

    public int getPoints() {
        return this.points;
    }

    public Camp getCamp() {
        return this.camp;
    }

    public Student getStudent() {
        return this.student;
    }

}
