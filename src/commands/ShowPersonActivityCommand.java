package commands;

import core.contracts.TaskRepository;
import models.contracts.Member;
import models.contracts.Team;
import utilities.ValidationHelper;

import java.util.List;

public class ShowPersonActivityCommand extends BaseCommand {

    public static final int EXPECTED_ARGUMENTS_COUNT = 1;
    protected ShowPersonActivityCommand(TaskRepository repository) {
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_ARGUMENTS_COUNT);
        Member member = getRepository().findMemberByName(parameters.get(0));
        return showPersonActivity(member);
    }

    private String showPersonActivity(Member member) {
        return member.getLogs().toString();//toString() override not sure if it should be overridden?
    }
}
