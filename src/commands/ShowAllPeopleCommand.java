package commands;

import commands.BaseCommand;
import core.contracts.TaskRepository;
import models.contracts.Member;

import java.util.List;

public class ShowAllPeopleCommand extends BaseCommand {

    public ShowAllPeopleCommand(TaskRepository repository){
        super(repository);
    }
    @Override
    protected String executeCommand(List<String> parameters) {
        return showPeople();
    }
    private String showPeople(){
        List<Member> people = getRepository().getMembers();
        StringBuilder result = new StringBuilder();
        result.append("People:\n");
        for (Member member : people) {
            result.append("Person ").append(member.getName()).append(System.lineSeparator());
        }
        return result.toString();
    }
}
