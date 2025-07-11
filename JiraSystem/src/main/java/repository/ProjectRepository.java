package main.java.repository;

import main.java.model.Issue;
import main.java.model.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectRepository {
    private Map<String, Project> projectMap = new HashMap<>();
    public Project saveProject(Project project){
        return projectMap.put(project.getId(), project);
    }
    public Project findById(String id){
        return projectMap.getOrDefault(id, null);
    }
}
