package ua.nure.poliakov.SummaryTask4.utils.validations.edition;

import javax.xml.bind.ValidationException;

public interface EditionValidation<T, D> {
    boolean name(T name) throws ValidationException;

    boolean subject(T subject) throws ValidationException;

    boolean price(D price) throws ValidationException;
}
