package Comment;

import java.io.Serializable;
import Camp.*;
import Users.*;

public abstract class Comment implements Serializable {
    int commentId;
    static int nextSuggestionId = 1;
    static int nextEnquiryId = 1;
    Student student;
    Camp camp;
    String comment;
    Boolean processed = false;

    public void editComment(String comment) {
        this.comment = comment;
    }

    public boolean getProcessed() {
        return this.processed;
    }

    public String getComment() {
        CampInformation campInformation = camp.getCampInformation();
        if (campInformation == null) {
            return null;
        }
        return "Created by:" + student.getName() + " | Camp: " + campInformation.getName() + " | Comment: "
                + this.comment + "\n";
    }

    public Student getStudent() {
        return this.student;
    }
}
