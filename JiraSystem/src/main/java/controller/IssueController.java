package main.java.controller;

import main.java.model.Issue;
import main.java.model.Project;
import main.java.model.Status;
import main.java.model.User;
import main.java.service.IssueService;
import main.java.service.ProjectService;
import main.java.service.UserService;

import java.util.List;

public class IssueController {
    private final UserService userService;
    private final ProjectService projectService;
    private final IssueService issueService;

    public IssueController(UserService userService, ProjectService projectService, IssueService issueService) {
        this.userService = userService;
        this.projectService = projectService;
        this.issueService = issueService;
    }

    public User createUser(String name){
        return userService.createUser(name);
    }
    public Project createProject(String name){
        return projectService.createProject(name);
    }
    public Issue createIssue(String title, String desc, User user, Project project){
        return issueService.createIssue(title, desc, user, project);
    }
    public void updateStatus(String issueId, Status status){
        issueService.updateStatus(issueId, status);
    }

    public List<Issue> getAllIssuesForProject(String projectId){
        return projectService.getAllIssuesForProject(projectId);
    }
}
