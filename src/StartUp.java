import core.TaskEngineImpl;
import models.tasks.Contracts.EventLog;
import models.tasks.EventLogImpl;

public class StartUp {
    public static void main(String[] args) {

        TaskEngineImpl engine = new TaskEngineImpl();
        engine.start();


    }
}