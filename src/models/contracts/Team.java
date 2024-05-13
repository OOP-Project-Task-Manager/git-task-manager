package models.contracts;

import models.BoardImpl;
import models.MemberImpl;

import java.util.List;

public interface Team extends Loggable{
    List<MemberImpl> getMembers();

    void addMember(MemberImpl member);

    void removeMember(MemberImpl member);

    List<BoardImpl> getBoards();

    void addBoard(BoardImpl board);

    void removeBoard(BoardImpl board);
}
