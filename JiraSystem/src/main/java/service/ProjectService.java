package main.java.service;

import main.java.model.Issue;
import main.java.model.Project;
import main.java.repository.ProjectRepository;

import java.util.List;
import java.util.UUID;

public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(String name){
        Project project = new Project(UUID.randomUUID().toString(), name);
        Project project1 = projectRepository.findById(project.getId());
        if(project1 != null){
            System.out.println("Project is already present with project id " + project1.getId());
            return project;
        }
        projectRepository.saveProject(project);
        return project;
    }
    public Project getProject(String id){
        return projectRepository.findById(id);
    }

    public List<Issue> getAllIssuesForProject(String id){
        Project project = projectRepository.findById(id);
        if(project == null){
            System.out.println("Project is not found with id " + id);
            return null;
        }
        List<Issue> issues = project.getIssues();
        return issues;
    }
}
