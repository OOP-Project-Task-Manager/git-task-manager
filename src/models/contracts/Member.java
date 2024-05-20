package models.contracts;

public interface Member extends Taskable,Loggable{

    String getName();
    String getTeam();
    void setTeam(Team team);
}
