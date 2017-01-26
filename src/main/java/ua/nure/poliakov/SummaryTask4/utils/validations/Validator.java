package ua.nure.poliakov.SummaryTask4.utils.validations;

import ua.nure.poliakov.SummaryTask4.utils.exceptions.PeriodicalsException;

public interface Validator<T> {
    boolean validate(T t) throws PeriodicalsException;
}
