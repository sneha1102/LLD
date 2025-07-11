import main.java.controller.IssueController;
import main.java.model.Issue;
import main.java.model.Project;
import main.java.model.Status;
import main.java.model.User;
import main.java.repository.IssueRepository;
import main.java.repository.ProjectRepository;
import main.java.repository.UserRepository;
import main.java.service.IssueService;
import main.java.service.ProjectService;
import main.java.service.UserService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        IssueRepository issueRepository = new IssueRepository();

        UserService userService = new UserService(userRepository);
        ProjectService projectService = new ProjectService(projectRepository);
        IssueService issueService = new IssueService(issueRepository);

        IssueController issueController = new IssueController(userService, projectService, issueService);

        User u1 = issueController.createUser("User1");
        User u2 = issueController.createUser("User2");

        Project p1 = issueController.createProject("Project1");
        Project p2 = issueController.createProject("Project2");

        Issue issue1 = issueController.createIssue("Fix bug", "desc 1", u1, p1);
        Issue issue2 = issueController.createIssue("fix bug 2", "desc 2", u2, p2);
        Issue issue3 = issueController.createIssue("fix bug 3", "desc 3", u1, p2);

        issueController.updateStatus(issue1.getIssueId(), Status.OPEN);
        issueController.updateStatus((issue2.getIssueId()), Status.INPROGRESS);

        List<Issue> issues = issueController.getAllIssuesForProject(p2.getId());
        issues.forEach(issue -> {
            System.out.println(issue.getDescription());
        });
    }
}