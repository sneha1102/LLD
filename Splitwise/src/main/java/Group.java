package main.java;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Group {
    private String groupId;
    private String name;
    private List<User> members;
    private Date createdAt;

    public Group(String name, List<User> members) {
        this.groupId = UUID.randomUUID().toString();
        this.name = name;
        this.members = members;
        this.createdAt = new Date();
    }
    public String getGroupId() {
        return groupId;
    }

    public String getName() {
        return name;
    }
}
