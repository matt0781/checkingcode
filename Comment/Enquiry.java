package Comment;

import java.util.ArrayList;
import Camp.*;
import Users.*;

public class Enquiry extends Comment {
    ArrayList<String> replies = new ArrayList<>();

    public Enquiry(Student student, Camp camp, String comment) {
        this.comment = comment;
        this.student = student;
        this.camp = camp;
        this.commentId = Comment.nextEnquiryId;
        Comment.nextEnquiryId += 1;
    }

    public void addReply(String reply) {
        replies.add(reply);
        this.processed = true;
    }

    public ArrayList<String> getReplies() {
        return this.replies;
    }

    public String getComment() {
        String info = "";
        info += "Enquiry " + this.commentId + ":\n";
        info += super.getComment();
        info += "\nReplies (" + replies.size() + "):\n";
        for(String reply : replies){
            info += "\t" + reply + "\n";
        }
        return info;
    }
}
