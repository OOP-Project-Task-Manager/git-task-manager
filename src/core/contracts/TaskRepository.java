package core.contracts;

import models.contracts.Board;
import models.contracts.Member;
import models.contracts.Team;
import models.tasks.Contracts.Bug;
import models.tasks.Contracts.Feedback;
import models.tasks.Contracts.Story;
import models.tasks.enums.Priority;
import models.tasks.enums.Severity;
import models.tasks.enums.Size;

import java.util.List;

public interface TaskRepository {
    public List<Team> getTeams();
    Bug createBug(int id, String title, String description, Priority priority, Severity severity, Member assignee);
    Story createStory(int id, String title, String description, Priority priority, Size size, Member assignee);
    Feedback createFeedback(int id, String title, String description, int rating);
    Board createBoard(String name);
    Member createNewPerson(String name);
    Team createTeam(String name);
    boolean hasLoggedInTeam();

    void login(Team team);

    void logout();
    Team getLoggedInTeam();
    void addTeam(Team teamToAdd);
}
