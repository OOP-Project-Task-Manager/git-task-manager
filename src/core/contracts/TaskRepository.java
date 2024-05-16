package core.contracts;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import models.tasks.Contracts.*;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;

import java.util.List;

public interface TaskRepository {
    List<Team> getTeams();

    List<Board> getBoards();

    List<Task> getTasks();

    List<Member> getMembers();
    Comment createComment(Member author, String message);


    void addTaskToMember(Task task, Member member);

    void addTaskToBoard(Task task, Board board);

    Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee);

    Story createStory(String title, String description, Priority priority, Size size, Member assignee);

    Feedback createFeedback(String title, String description, int rating);

    Board createBoard(String name);

    Member createNewPerson(String name);

    Team createTeam(String name);
    Task findTaskByTitle(String title);

    Board findBoardByName(String name);

    Team findTeamByName(String name);

    Member findMemberByName(String name);


    Task findTaskById(int id);

    void addTeam(Team teamToAdd);

    void addMember(Member memberToAdd);
}
