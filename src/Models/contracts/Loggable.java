package Models.contracts;
import Models.Tasks.Contracts.EventLog;

import java.util.List;

public interface Loggable {

     void addLog(String description);
     List<EventLog> getLogs();

}
