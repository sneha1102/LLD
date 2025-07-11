package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String id;
    private String name;
    private List<Issue> issues;

    public Project(String id, String name) {
        this.id = id;
        this.name = name;
        this.issues = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }


}
