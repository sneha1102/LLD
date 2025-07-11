package main.java.model;

import java.util.List;

public class Commit {
    private String id;
    private String message;
    private List<File> files;

    public Commit(String id, String message, List<File> files) {
        this.id = id;
        this.message = message;
        this.files = files;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "Commit{id='" + id + "', message='" + message + "', files=" + files + '}';
    }

}
