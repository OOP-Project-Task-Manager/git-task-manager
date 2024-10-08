package core;

import models.BoardImpl;
import models.MemberImpl;
import models.TeamImpl;
import models.contracts.Board;
import models.contracts.Member;
import core.contracts.TaskRepository;
import models.contracts.Team;
import models.tasks.*;
import models.tasks.Contracts.*;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    public static final String ERROR_TASK_ID = "No task with ID %d";
    public static final String NO_SUCH_TASK = "No such task {%s}";
    public static final String MEMBER_ALREADY_EXIST = "Member %s already exists";
    public static final String BUG_ERR = "No Bug with id %d";
    public static final String STORY_ERR = "No story with id %S";
    public static final String FEEDBACK_ERR = "No feedback with id %s";
    public static final String BOARD_ALREADY_EXIST = "Board with name %s already exists";
    private int id;
    private final static String TEAM_ALREADY_EXIST = "Team %s already exist.";
    private final static String NO_SUCH_TEAM = "There is no team with name %s!";
    public static final String NO_SUCH_MEMBER = "No member with name %s";
    public static final String NO_SUCH_BOARD = "No board with name %s";

    private final List<Team> teams;
    private final List<Member> members;

    private final List<Board> boards;

    private final List<Task> tasks;
    private final List<Story> stories;
    private final List<Bug> bugs;
    private final List<Feedback> feedbacks;


    public TaskRepositoryImpl() {
        id = 0;
        teams = new ArrayList<>();
        members = new ArrayList<>();
        boards = new ArrayList<>();
        tasks = new ArrayList<>();
        stories = new ArrayList<>();
        bugs = new ArrayList<>();
        feedbacks = new ArrayList<>();
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
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public Comment createComment(Member author, String message) {
        return new CommentImpl(author, message);
    }

    @Override
    public void addTaskToMember(Task task, Member member) {
        member.addTask(task);
    }

    @Override
    public void addTaskToBoard(Task task, Board board) {
        board.addTask(task);
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
    }


    @Override
    public Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee) {
        Bug bug = new BugImpl(++id, title, description, priority, severity, assignee);
        tasks.add(bug);
        bugs.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, Member assignee) {

        Story story = new StoryImpl(++id, title, description, priority, size, assignee);
        tasks.add(story);
        stories.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {

        Feedback feedback = new FeedbackImpl(++id, title, description, rating);
        tasks.add(feedback);
        feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public Board createBoard(String name) {
        if (boardExist(name)) {
            throw new IllegalArgumentException(String.format(BOARD_ALREADY_EXIST, name));
        }
        Board board = new BoardImpl(name);
        boards.add(board);
        return board;
    }

    @Override
    public boolean boardExist(String name) {
        for (Board board : boards) {
            if (board.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Member createNewPerson(String name) {
        boolean personExist = members.stream().anyMatch(u -> u.getName().equalsIgnoreCase(name));
        if (personExist) {
            throw new IllegalArgumentException(String.format(MEMBER_ALREADY_EXIST, name));
        }
        Member member = new MemberImpl(name);
        members.add(member);
        return member;
    }

    @Override
    public Team createTeam(String name) {
        boolean teamExist = teams.stream().anyMatch(u -> u.getName().equalsIgnoreCase(name));
        if (teamExist) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXIST, name));
        }
        Team team = new TeamImpl(name);
        teams.add(team);
        return team;
    }

    @Override
    public Task findTaskByTitle(String title) {
        Task task = tasks.stream().filter(u -> u.getTitle().equalsIgnoreCase(title)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_TASK, title)));
        return task;
    }

    @Override
    public Board findBoardByName(String name) {

        Board board = boards.stream().filter(u -> u.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_BOARD, name)));
        return board;
    }

    @Override
    public Team findTeamByName(String name) {
        Team team = teams.stream().filter(u -> u.getName().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_TEAM, name)));
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
    public Bug findBugById(int id) {
        Bug bug = bugs.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(BUG_ERR, id)));
        return bug;
    }

    @Override
    public Story findStoryById(int id) {
        Story story = stories.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(STORY_ERR, id)));
        return story;
    }

    @Override
    public Feedback findFeedbackById(int id) {
        Feedback feedback = feedbacks.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new IllegalArgumentException(String.format(FEEDBACK_ERR, id)));
        return feedback;
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
            throw new IllegalArgumentException(String.format(MEMBER_ALREADY_EXIST, memberToAdd.getName()));
        }
        members.add(memberToAdd);
    }

    @Override
    public void addTask(Task taskToAdd) {
        tasks.add(taskToAdd);
    }

    @Override
    public void addStory(Story storyToAdd) {
        stories.add(storyToAdd);
    }

    @Override
    public void addBug(Bug bugToAdd) {
        bugs.add(bugToAdd);
    }

    @Override
    public void addFeedback(Feedback feedbackToAdd) {
        feedbacks.add(feedbackToAdd);
    }


}
