package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String name;
    private List<Commit> commits;

    public Branch(String name) {
        this.name = name;
        this.commits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void addCommit(Commit commit){
        commits.add(commit);
    }

    public Commit getLatestCommit(){
        if(commits.isEmpty())
            return null; // No commits in this branch yet
        return commits.get(commits.size() - 1);
    }

    public void rollBackToCommit(String commitId) throws Exception {
        for(int i = commits.size() - 1; i >= 0; i--){
            if(commits.get(i).getId().equals(commitId)){
                // Remove commits beyond rollback point
                while(commits.size() > i + 1){
                    commits.remove(commits.size() - 1);
                }
                return;
            }
        }
        throw new Exception("Commit ID not found for rollback");
    }
}
