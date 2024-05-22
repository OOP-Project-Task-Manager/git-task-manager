package models.tasks.Contracts;

import models.contracts.Board;
import models.contracts.Commentable;
import models.contracts.Identifiable;
import models.contracts.Loggable;

public interface Task extends Loggable, Identifiable, Commentable {
     String getTitle();

     String getDescription();
     String getBoard();
     void setBoard(Board baord);



}
