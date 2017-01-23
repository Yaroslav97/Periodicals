package ua.nure.poliakov.SummaryTask4.utils.validations;

import javax.xml.bind.ValidationException;

public interface Validator<T> {
    boolean validate(T t) throws ValidationException;
}
