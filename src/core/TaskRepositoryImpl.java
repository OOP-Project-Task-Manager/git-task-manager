package core;

import Models.contracts.Member;
import core.contracts.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private static final String MEMBER_DOES_NOT_EXIST = "Member %s does not exist!";
    private final List<Member> members;
    public TaskRepositoryImpl(){
        members = new ArrayList<>();
    }


    @Override
    public boolean memberExist(String memberName) {

    }
}
