package main.java.model;

public class Issue {
    private String issueId;
    private String title;
    private String description;
    private Status issueStatus;


    public Issue(String issueId, String title, String description) {
        this.issueId = issueId;
        this.title = title;
        this.description = description;
        this.issueStatus = Status.CREATED;
    }

    public String  getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(Status issueStatus) {
        this.issueStatus = issueStatus;
    }
}
