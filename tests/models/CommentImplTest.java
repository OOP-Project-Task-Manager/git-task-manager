package models;

import models.contracts.Member;
import models.tasks.CommentImpl;
import models.tasks.Contracts.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentImplTest {


    private static final String validMessage = "A";
    private static final String inValidMessage = "";

    Member author = new MemberImpl("Johny");


    @Test
    public void should_ThrowException_WhenMessage_Is_Not_Valid(){

        Assertions.assertThrows(IllegalArgumentException.class,()->new CommentImpl(author,inValidMessage));
    }



    @Test
    public void should_CreateComment_WhenArguments_Are_Valid(){
        Assertions.assertDoesNotThrow(()->new CommentImpl(author,validMessage));
    }









}
