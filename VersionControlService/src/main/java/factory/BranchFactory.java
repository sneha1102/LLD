package main.java.factory;

import main.java.model.Branch;

public class BranchFactory {
    public static Branch createBranch(String name){
        return new Branch(name);
    }
}
