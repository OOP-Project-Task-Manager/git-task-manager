package Models.contracts;

import Models.Board;
import Models.Member;

import java.util.List;

public interface Teamable {
    List<Member> getMembers();

    void addMember(Member member);

    void removeMember(Member member);

    List<Board> getBoards();

    void addBoard(Board board);

    void removeBoard(Board board);
}
