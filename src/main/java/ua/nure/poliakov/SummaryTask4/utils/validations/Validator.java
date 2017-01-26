package ua.nure.poliakov.SummaryTask4.utils.validations;

import ua.nure.poliakov.SummaryTask4.utils.exceptions.ValidationException;

public interface Validator<T> {
    boolean validate(T t) throws ValidationException;
}
