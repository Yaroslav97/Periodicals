package ua.nure.poliakov.SummaryTask4.SummaryTask4.validations;

import org.junit.Test;
import ua.nure.poliakov.SummaryTask4.dao.entity.Edition;
import ua.nure.poliakov.SummaryTask4.utils.validations.Validator;
import ua.nure.poliakov.SummaryTask4.utils.validations.edition.ValidateEdition;

import static org.junit.Assert.assertEquals;

public class EditionValidation {

    @Test
    public void testValidUser() throws Exception {
        Validator<Edition> validator = new ValidateEdition();
        boolean expected = validator.validate(new Edition(
                "Edition 1st",
                "some subject",
                4.5
        ));
        assertEquals(true, expected);
    }
}
