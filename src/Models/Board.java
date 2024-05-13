package Models;

import Models.Tasks.Contracts.EventLog;
import Models.Tasks.Contracts.Task;
import Models.Tasks.EventLogImpl;
import Models.contracts.Loggable;
import Models.contracts.Taskable;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class Board implements Loggable, Taskable {

    //TODO implement logging to all methods that change something.


    //Constants

    public static final String BOARD_CONSTRUCTOR_LOG = "Board %s created";
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 10;
    public static final String NAME_LEN_ERR = "Board name must be between %d and %d";

    //Attributes
    private String name;
    private final List<Task> tasks;
    private final List<EventLog> activityHistory;

    //Constructor

    public Board(String name) {
        setName(name);
        tasks = new ArrayList<>();
        activityHistory = new ArrayList<>();
        addLog(BOARD_CONSTRUCTOR_LOG.formatted(name));

    }


    //Methods


    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelper.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,
                NAME_LEN_ERR.formatted(NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        this.name = name;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
        String activity = "Task \"" + task.getTitle() + "\" added to board \"" + name + "\"";
        addLog(activity);
    }


    @Override
    public void removeTask(Task task) {
        tasks.remove(task);
        String activity = "Task \"" + task.getTitle() + "\" removed from board \"" + name + "\"";
        addLog(activity);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }


    @Override
    public void addLog(String description) {
        activityHistory.add(new EventLogImpl(description));
    }

    @Override
    public List<EventLog> getLogs() {
        return new ArrayList<>(activityHistory);
    }
}
