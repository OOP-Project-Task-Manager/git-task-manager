package models.contracts;

import models.BoardImpl;
import models.MemberImpl;

import java.util.List;

public interface Team extends Loggable {

    String getName();
    List<Member> getMembers();

    void addMember(Member member);

    void removeMember(MemberImpl member);

    List<Board> getBoards();

    void addBoard(Board board);

    void removeBoard(Board board);
}
