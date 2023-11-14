package Camp;

import java.io.Serializable;
import java.util.*;
import Comment.*;

public class CampCommentManager implements Serializable{
    private ArrayList<Comment> suggestion = new ArrayList<>();
    private ArrayList<Comment> enquiry = new ArrayList<>();

    protected ArrayList<Comment> getSuggestion() {
        return this.suggestion;
    }

    protected ArrayList<Comment> getEnquiry() {
        return this.enquiry;
    }

    protected void addSuggestion(Comment comment) {
        if (comment == null) {
            return;
        }
        this.suggestion.add(comment);
    }

    protected void addEnquiry(Comment comment) {
        if (comment == null) {
            return;
        }
        this.enquiry.add(comment);
    }

    protected void removeEnquiry(Comment comment) {
        if (comment == null || !this.enquiry.contains(comment)) {
            return;
        }
        this.enquiry.remove(comment);
    }

    protected void removeSuggestion(Comment comment) {
        if (comment == null || !this.suggestion.contains(comment)) {
            return;
        }
        this.suggestion.remove(comment);
    }

    protected String getComments(){
        String info = "\nSuggestions (" + suggestion.size() + "):\n";
        for(Comment comment : suggestion){
            info += "\t" + comment.getComment() + "\n";
        }

        info += "\nEnquiry (" + enquiry.size() + "):\n";
        for(Comment comment : enquiry){
            info += "\t" + comment.getComment() + "\n";
        }
        
        return info;
    }
}
