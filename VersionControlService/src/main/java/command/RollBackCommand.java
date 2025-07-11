package main.java.command;

import main.java.model.Branch;

public class RollBackCommand implements Command{
    private Branch branch;
    private String commitId;

    public RollBackCommand(Branch branch, String commitId) {
        this.branch = branch;
        this.commitId = commitId;
    }

    @Override
    public void execute() throws Exception {
        branch.rollBackToCommit(commitId); // Perform rollback operation
        System.out.println("Rollback successful: Branch '" + branch.getName() + "' rolled back to commit ID: " + commitId);
    }
}
