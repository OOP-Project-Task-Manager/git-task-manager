package models;

import models.contracts.Board;
import models.tasks.Contracts.EventLog;
import models.tasks.Contracts.Task;
import models.tasks.EventLogImpl;
import utilities.ValidationHelper;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    //TODO implement logging to all methods that change something.


    //Constants

    public static final String BOARD_CONSTRUCTOR_LOG = "Board %s created";
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 10;
    public static final String NAME_LEN_ERR = "Board name must be between %d and %d";
    public static final String NON_EXISTENT_TASK_ERR = "Cant remove non-existent task";

    //Attributes
    private String name;
    private final List<Task> tasks;
    private final List<EventLog> activityHistory;

    //Constructor

    public BoardImpl(String name) {
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
        String activity = "Task %s added to board %s".formatted(task.getTitle(), getName());
        addLog(activity);
    }


    @Override
    public void removeTask(Task task) {
        if (!tasks.contains(task)) {
            throw new IllegalArgumentException(NON_EXISTENT_TASK_ERR);
        }
        tasks.remove(task);
        String activity = "Task %s removed from board %s".formatted(task.getTitle(), getName());
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
