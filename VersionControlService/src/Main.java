import main.java.model.File;
import main.java.service.VersionControlService;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        VersionControlService versionControlService = new VersionControlService();

        // Add some files
        List<File> files = new ArrayList<>();
        files.add(new File("README.md", "This is the README"));
        files.add(new File("Main.java", "System.out.println('Hello World')"));

        // Commit files to the main branch
        System.out.println("Committing files to the main branch...");
        versionControlService.addCommit("Initial commit", files);

        // Show commits in active branch
        versionControlService.showBranchCommits();

        // Create and switch to a new branch
        versionControlService.addBranch("feature-xyz");
        versionControlService.switchBranch("feature-xyz");

        // Add some files for the new branch
        files.add(new File("Feature.java", "System.out.println('Feature development')"));

        // Commit files to feature branch
        System.out.println("Committing files to feature branch...");
        versionControlService.addCommit("Feature development", files);

        // Show commits in feature branch
        versionControlService.showBranchCommits();

        // Switch back to main branch
        versionControlService.switchBranch("main");
        versionControlService.showBranchCommits();

        // Rollback in the main branch
        System.out.println("Rolling back to the first commit in the main branch...");
        versionControlService.rollbackToCommit(versionControlService.getActiveBranch().getCommits().get(0).getId());

        // Show commits in feature branch
        versionControlService.showBranchCommits();
    }
}