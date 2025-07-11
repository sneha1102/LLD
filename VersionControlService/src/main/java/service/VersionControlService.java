package main.java.service;

import main.java.command.Command;
import main.java.command.RollBackCommand;
import main.java.factory.BranchFactory;
import main.java.factory.CommitFactory;
import main.java.model.Branch;
import main.java.model.Commit;
import main.java.model.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VersionControlService {
    private Map<String , Branch> branches;



    private Branch activeBranch;

    public VersionControlService() {
        this.branches = new HashMap<>();
        Branch mainBranch = new Branch("main");
        branches.put("main", mainBranch);
        this.activeBranch = mainBranch;
    }
    public void addBranch(String name){
        if(branches.containsKey(name)){
            System.out.println("Branch already exists with name as " + name);
        }else{
            Branch newBranch = BranchFactory.createBranch(name);
            branches.put(name, newBranch);
            System.out.println("Branch " + name + " created");
        }
    }

    public void switchBranch(String name){
        if(branches.containsKey(name)){
            activeBranch = branches.get(name);
            System.out.println("Switched to branch '" + name + "'");
        } else {
            System.out.println("Branch '" + name + "' does not exist");
        }
    }

    public void addCommit(String msg, List<File> files){
        Commit commit = CommitFactory.createCommit(msg, files);
        activeBranch.addCommit(commit);
        System.out.println("Commit added to branch '" + activeBranch.getName() + "' with ID: " + commit.getId());
    }

    // Rollback using Command pattern
    // Encapsulate rollback operation
    public void rollbackToCommit(String commitId) {
        try {
            Command rollbackCommand = new RollBackCommand(activeBranch, commitId); // Encapsulate rollback operation
            rollbackCommand.execute(); // Execute rollback
        } catch (Exception e) {
            System.out.println("Rollback failed: " + e.getMessage());
        }
    }

    public void showBranchCommits(){
        System.out.println("Branch '" + activeBranch.getName() + "' commits:");
        for(Commit commit: activeBranch.getCommits()){
            System.out.println(commit);
        }
    }
    public Map<String, Branch> getBranches() {
        return branches;
    }

    public Branch getActiveBranch() {
        return activeBranch;
    }
}
