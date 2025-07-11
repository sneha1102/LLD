package main.java.factory;

import main.java.model.Commit;
import main.java.model.File;

import java.util.List;
import java.util.UUID;

public class CommitFactory {
    public static Commit createCommit(String message, List<File> files){
        String commitId = UUID.randomUUID().toString(); // Generate unique ID
        return new Commit(commitId, message, files);
    }
}
