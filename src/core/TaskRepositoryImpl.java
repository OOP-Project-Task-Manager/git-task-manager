package core;

import models.BoardImpl;
import models.MemberImpl;
import models.TeamImpl;
import models.contracts.Board;
import models.contracts.Member;
import core.contracts.TaskRepository;
import models.contracts.Team;
import models.tasks.BugImpl;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Feedback;
import models.tasks.Contracts.Story;
import models.tasks.Contracts.Task;
import models.tasks.FeedbackImpl;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    public static final String ERROR_TASK_ID = "No task with ID %d";
    private int id;
    private final static String TEAM_ALREADY_EXIST = "Team %s already exist.";
    private final static String NO_SUCH_TEAM = "There is no team with name %s!";
    public static final String NO_SUCH_MEMBER = "No member with name %s";
    public static final String NO_SUCH_BOARD = "No board with name %s";

    private final List<Team> teams;
    private final List<Member> members;

    private final List<Board> boards;

    private final List<Task> tasks;

    //private Team loggedTeam;//без няма логика по скоро мембър...

    public TaskRepositoryImpl() {
        id = 0;
        teams = new ArrayList<>();
        members = new ArrayList<>();
        boards = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public void addTaskToBoard(Task task, Board board) {
        board.addTask(task);
    }


    @Override
    public Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee) {
        Bug bug = new BugImpl(++id, title, description, priority, severity, assignee);
        tasks.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, Member assignee) {

        Story story = new StoryImpl(++id, title, description, priority, size, assignee);
        tasks.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {

        Feedback feedback = new FeedbackImpl(++id, title, description, rating);
        tasks.add(feedback);
        return feedback;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        boards.add(board);
        return board;
    }

    @Override
    public Member createNewPerson(String name) {
        return new MemberImpl(name);
    }

    @Override
    public Team createTeam(String name) {
        return new TeamImpl(name);
    }

    @Override
    public Board findBoardByName(String name) {

        Board board = boards.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_BOARD, name)));
        return board;
    }

    @Override
    public Team findTeamByName(String name) {
        Team team = teams.stream()
                .filter(u -> u.getName().equalsIgnoreCase(name)).
                findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_TEAM, name)));
        return team;
    }

    @Override
    public Member findMemberByName(String name) {
        Member member = members.stream().filter(u -> u.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_MEMBER, name)));
        return member;
    }

    @Override
    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException(String.format(ERROR_TASK_ID, id));
    }

    @Override
    public void addTeam(Team teamToAdd) {
        if (teams.contains(teamToAdd)) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXIST, teamToAdd.getName()));
        }
        teams.add(teamToAdd);
    }

    @Override
    public void addMember(Member memberToAdd) {
        if (members.contains(memberToAdd)) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXIST, memberToAdd.getName()));
        }
        members.add(memberToAdd);
    }


}
