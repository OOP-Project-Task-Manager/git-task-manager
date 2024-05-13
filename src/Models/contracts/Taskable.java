package Models.contracts;

import Models.Tasks.Contracts.Task;

import java.util.List;

public interface Taskable {
    void addTask(Task task);

    void removeTask(Task task);

    List<Task> getTasks();
}
