package core;

import models.Team;
import models.contracts.Board;
import models.contracts.Member;
import core.contracts.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private static final String MEMBER_DOES_NOT_EXIST = "Member %s does not exist!";
    private final List<Member> members;
    private final List<Team> teams;
    private final List<Board> boards;

    public TaskRepositoryImpl(){
        members = new ArrayList<>();
        teams = new ArrayList<>();
        boards = new ArrayList<>();
    }
}
