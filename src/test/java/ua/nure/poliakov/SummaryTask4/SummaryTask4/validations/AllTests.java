package ua.nure.poliakov.SummaryTask4.SummaryTask4.validations;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.nure.poliakov.SummaryTask4.SummaryTask4.validations.edition.EditionValidationTest;
import ua.nure.poliakov.SummaryTask4.SummaryTask4.validations.edition.NameValidationTest;
import ua.nure.poliakov.SummaryTask4.SummaryTask4.validations.edition.PriceValidationTest;
import ua.nure.poliakov.SummaryTask4.SummaryTask4.validations.edition.SubjectValidationTest;
import ua.nure.poliakov.SummaryTask4.SummaryTask4.validations.moc.MockitoTest;
import ua.nure.poliakov.SummaryTask4.SummaryTask4.validations.user.*;

@RunWith(Suite.class)

@Suite.SuiteClasses({EditionValidationTest.class, NameValidationTest.class, PriceValidationTest.class, SubjectValidationTest.class,
        EditionValidationTest.class, FullNameValidationTest.class, LoginValidationTest.class, PasswordlValidationTest.class,
        ScoreValidationTest.class, UserValidationTest.class, MockitoTest.class})

public class AllTests {
}