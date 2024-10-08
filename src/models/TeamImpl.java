package models;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import models.tasks.Contracts.EventLog;
import models.tasks.EventLogImpl;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    public static final String TEAM_CONSTRUCTOR_LOG = "Team %s created";
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_LEN_ERR = "Team name must be between %d and %d";

    private String name;
    private List<Member> members;
    private List<Board> boards;
    private List<EventLog> activityHistory;

    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        addLog(TEAM_CONSTRUCTOR_LOG.formatted(name));
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelper.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,
                NAME_LEN_ERR.formatted(NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        this.name = name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
        String activity = String.format("Member %s added to team %s", member.getName(), getName());
        addLog(activity);

    }


    @Override
    public void removeMember(MemberImpl member) {
        members.remove(member);
        String activity = String.format("Member %s removed from team %s", member.getName(), getName());
        addLog(activity);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
        String activity = String.format("Board %s added to team %s", board.getName(), getName());
        addLog(activity);
    }


    @Override
    public void removeBoard(Board board) {
        boards.remove(board);
        String activity = String.format("Board %s removed from team %s", board.getName(), getName());
        addLog(activity);
    }

    @Override
    public void addLog(String description) {
        activityHistory.add(new EventLogImpl(description));
    }

    @Override
    public List<EventLog> getLogs() {
        return new ArrayList<>(activityHistory);
    }


}
