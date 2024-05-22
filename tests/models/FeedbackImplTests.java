package models;

import models.tasks.Contracts.Feedback;
import models.tasks.FeedbackImpl;
import models.tasks.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackImplTests {

    @Test
    public void should_ThrowException_When_StatusIsNotValid() {
        Feedback feedback = new FeedbackImpl(1, "ThisFeedbackIsAwesome", "ThisDescriptionShouldSuffice", 4);
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedback.setStatus(Status.ACTIVE));
    }

    @Test
    public void should_CreateFeedback_When_ArgumentsAreValid(){
        Assertions.assertDoesNotThrow(()->new FeedbackImpl(1, "ThisFeedbackIsAwesome", "ThisDescriptionShouldSuffice", 4));
    }




}
