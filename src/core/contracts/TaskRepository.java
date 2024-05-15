package core.contracts;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Feedback;
import models.tasks.Contracts.Story;
import models.tasks.Contracts.Task;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;

import java.util.List;

public interface TaskRepository {
    public List<Team> getTeams();

    Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee);

    Story createStory(String title, String description, Priority priority, Size size, Member assignee);

    Feedback createFeedback(String title, String description, int rating);

    Board createBoard(String name);

    Member createNewPerson(String name);

    Team createTeam(String name);

    Board findBoardByName(String name);

    Team findTeamByName(String name);

    Member findMemberByName(String name);
    //boolean hasLoggedInTeam();

    Task findTaskById(int id);

    void addTeam(Team teamToAdd);

    void addMember(Member memberToAdd);
}
