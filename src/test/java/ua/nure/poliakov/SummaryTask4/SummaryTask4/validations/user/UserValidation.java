package ua.nure.poliakov.SummaryTask4.SummaryTask4.validations.user;

import org.junit.Test;
import ua.nure.poliakov.SummaryTask4.dao.entity.User;
import ua.nure.poliakov.SummaryTask4.utils.exceptions.ValidationException;
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

    @Test(expected = ValidationException.class)
    public void testNoValidUser() throws Exception {
        Validator<User> validator = new ValidateUser();
        boolean expected = validator.validate(new User(
                "JohnHladush",
                "john32#",
                "john##@@nure.ua",
                "pass"
        ));
        assertEquals(true, expected);
    }
}
