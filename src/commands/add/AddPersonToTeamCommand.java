package commands.add;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Member;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class AddPersonToTeamCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public final static String PERSON_ADDED_SUCCESSFULLY = "%s added to team %s successfully!";
    public AddPersonToTeamCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String personName = parameters.get(0);
        String teamName = parameters.get(1);
        return addPersonInTeam(personName, teamName);
    }
    private String addPersonInTeam(String personName, String teamName){
        Team team = getRepository().findTeamByName(teamName);
        Member member = getRepository().findMemberByName(personName);
        member.setTeam(team);
        team.addMember(member);
        return String.format(PERSON_ADDED_SUCCESSFULLY, member.getName(), teamName);
    }
}
