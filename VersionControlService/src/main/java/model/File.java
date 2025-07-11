package main.java.model;

public class File {
    private String name;
    private String content;
    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    @Override
    public String toString() {
        return "File{name='" + name + "', content='" + content + "'}";
    }
}
