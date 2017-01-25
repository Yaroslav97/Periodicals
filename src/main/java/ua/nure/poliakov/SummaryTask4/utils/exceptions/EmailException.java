package ua.nure.poliakov.SummaryTask4.utils.exceptions;

public class EmailException extends PeriodicalsException {

    public EmailException(){}

    public EmailException(String message, Throwable clause){
        super(message, clause);
    }
}
