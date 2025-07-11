package main.java.repository;

import main.java.model.Issue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueRepository {
    private Map<String, Issue> issueMap = new HashMap<>();

    public Issue saveIssue(Issue issue){
        return issueMap.put(issue.getIssueId(), issue);
    }
    public Issue findById(String id){
        return issueMap.getOrDefault(id, null);
    }

    public List<Issue> findAll(){
        return new ArrayList<>(issueMap.values());
    }

}
