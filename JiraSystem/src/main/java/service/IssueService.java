package main.java.service;

import jdk.jshell.Snippet;
import main.java.model.Issue;
import main.java.model.Project;
import main.java.model.Status;
import main.java.model.User;
import main.java.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IssueService {
    private IssueRepository issueRepository;


    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue createIssue(String title, String desc, User user, Project project){
        Issue issue = new Issue(UUID.randomUUID().toString(), title, desc);
        issueRepository.saveIssue(issue);
        project.getIssues().add(issue);
        System.out.println("Issue created successfully with issue id " + issue.getIssueId());
        return issue;
    }

    public void updateStatus(String issueId, Status newStatus){
        Issue issue = issueRepository.findById(issueId);
        if(issue != null) {
            issue.setIssueStatus(newStatus);
            issueRepository.saveIssue(issue);
            System.out.println("Issue status has been updated successfully with status " + newStatus);
        }else{
            System.out.println("No issue is present with id " + issueId);
        }
    }

    public Issue getIssueById(String id){
        return issueRepository.findById(id);
    }

    public List<Issue> getAllIssue(){
        return new ArrayList<>(issueRepository.findAll());
    }

}
