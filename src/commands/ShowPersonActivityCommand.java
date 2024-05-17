package commands;

import core.contracts.TaskRepository;
import models.contracts.Member;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class ShowPersonActivityCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 2;
    public static final String INVALID_MEMBER_ERR = "No such member %s in team %s";

    protected ShowPersonActivityCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Member member = getRepository().findMemberByName(parameters.get(0));
        Team team = getRepository().findTeamByName(parameters.get(1));

        return showPersonActivity(member, team);
    }


    private String showPersonActivity(Member member, Team team) {
        for (Member member1 : team.getMembers()) {
            if (member1.equals(member)) {
                return member1.getLogs().toString();
            }
        }
        throw new IllegalArgumentException(INVALID_MEMBER_ERR.formatted(member.getName(), team.getName()));
    }
}
