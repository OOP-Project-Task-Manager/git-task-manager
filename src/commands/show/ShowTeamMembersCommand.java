package commands.show;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Member;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class ShowTeamMembersCommand extends BaseCommand {
    public static final int EXPECTED_ARGUMENTS_COUNT = 1;

    public ShowTeamMembersCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    public String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Team team = getRepository().findTeamByName(parameters.get(0));
        return showTeamMembers(team);
    }

    private String showTeamMembers(Team team){
        List<Member> members = team.getMembers();
        StringBuilder result = new StringBuilder();
        result.append("Team %s members:\n".formatted(team.getName()));
        for (Member member : members){
            result.append("Member name: ").append(member.getName()).append(System.lineSeparator());
        }
        return result.toString();
    }
}
