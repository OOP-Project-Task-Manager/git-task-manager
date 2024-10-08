package commands.create;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Member;
import utilities.ValidationHelper;

import java.util.List;

public class CreateNewPersonCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final static String PERSON_CREATED = "Person %s created successfully!";

    public CreateNewPersonCommand(TaskRepository repository){
        super(repository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        return createPerson(name);
    }

    private String createPerson(String name){
        Member member = getRepository().createNewPerson(name);
        //getRepository().addMember(member);
        return String.format(PERSON_CREATED, member.getName());
    }
}
