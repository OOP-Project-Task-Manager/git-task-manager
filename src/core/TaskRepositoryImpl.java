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
import models.tasks.FeedbackImpl;
import models.tasks.StoryImpl;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    //private static final String NO_LOGGED_IN_TEAM = "There is no logged in team.";
    private final static String TEAM_ALREADY_EXIST = "Team %s already exist.";
    private final static String NO_SUCH_TEAM = "There is no team with name %s!";
    public static final String NO_SUCH_MEMBER = "No member with name %s";
    private final List<Team> teams;
    //private final List<Member> members;
    //private Team loggedTeam;//без няма логика по скоро мембър...

    public TaskRepositoryImpl(){
        teams = new ArrayList<>();
        //members = new ArrayList<>();
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }
    /*public List<Member> getMembers(){
        return new ArrayList<>(members);
    }*/

    @Override
    public Bug createBug(int id, String title, String description, Priority priority, Severity severity, Member assignee) {
        return new BugImpl(id, title, description, priority, severity, assignee);
    }

    @Override
    public Story createStory(int id, String title, String description, Priority priority, Size size, Member assignee) {
        return new StoryImpl(id, title, description, priority, size, assignee);
    }

    @Override
    public Feedback createFeedback(int id, String title, String description, int rating) {
        return new FeedbackImpl(id, title, description, rating);
    }
    @Override
    public Board createBoard(String name) {
        return new BoardImpl(name);
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
    public Team findTeamByName(String username) {
        Team team = teams
                .stream()
                .filter(u -> u.getName().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_TEAM, username)));
        return team;
    }

    @Override
    public Member findMemberByName(String username, Team originTeam) {
        for (Member member : /*getMembers()*/ originTeam.getMembers()){
            if (member.getName().equalsIgnoreCase(username)){
                return member;
            }
        }
        throw new IllegalArgumentException(String.format(NO_SUCH_MEMBER, username));
    }
    @Override
    public void addTeam(Team teamToAdd) {
        if (teams.contains(teamToAdd)){
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXIST, teamToAdd.getName()));
        }
        teams.add(teamToAdd);
    }
}
