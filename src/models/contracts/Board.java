package models.contracts;

public interface Board extends Taskable,Loggable{

    String getName();
    String getTeam();
    void setTeam(Team team);

}