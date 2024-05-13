package models;

import models.tasks.Contracts.EventLog;
import models.tasks.EventLogImpl;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements models.contracts.Team {

    public static final String TEAM_CONSTRUCTOR_LOG = "Team %s created";
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_LEN_ERR = "Team name must be between %d and %d";

    private String name;
    private List<MemberImpl> members;
    private List<BoardImpl> boards;
    private List<EventLog> activityHistory;

    public TeamImpl(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelper.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,
                NAME_LEN_ERR.formatted(NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        this.name = name;
    }

    @Override
    public List<MemberImpl> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public void addMember(MemberImpl member) {
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
    public List<BoardImpl> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public void addBoard(BoardImpl board) {
        boards.add(board);
        String activity = String.format("Board %s added to team %s", board.getName(), getName());
        addLog(activity);
    }


    @Override
    public void removeBoard(BoardImpl board) {
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
