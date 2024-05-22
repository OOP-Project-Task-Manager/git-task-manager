package models.contracts;
import models.tasks.Contracts.EventLog;

import java.util.List;

public interface Loggable {

     void addLog(String description);
     List<EventLog> getLogs();

}
