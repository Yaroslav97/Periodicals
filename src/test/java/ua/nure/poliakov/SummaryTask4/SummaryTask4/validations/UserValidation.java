package ua.nure.poliakov.SummaryTask4.SummaryTask4.validations;

import org.junit.Test;
import ua.nure.poliakov.SummaryTask4.dao.entity.User;

import ua.nure.poliakov.SummaryTask4.utils.validations.Validator;
import ua.nure.poliakov.SummaryTask4.utils.validations.user.ValidateUser;

import static org.junit.Assert.assertEquals;

public class UserValidation {

    @Test
    public void testValidUser() throws Exception {
        Validator<User> validator = new ValidateUser();
        boolean expected = validator.validate(new User(
                "John Hladush",
                "john",
                "john@gmail.com",
                "pass"
        ));
        assertEquals(true, expected);
    }

    @Test
    public void testValidUser1() throws Exception {
        Validator<User> validator = new ValidateUser();
        boolean expected = validator.validate(new User(
                "John Hladush",
                "john32",
                "john@nure.ua",
                "bbd4378@"
        ));
        assertEquals(true, expected);
    }

    @Test
    public void testValidScore() throws Exception {
        ValidateUser validator = new ValidateUser();
        boolean expected = validator.score(4.5);
        assertEquals(true, expected);
    }
}
