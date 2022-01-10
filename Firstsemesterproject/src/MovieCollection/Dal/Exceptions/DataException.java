package MovieCollection.Dal.Exceptions;

public class DataException extends Exception{

    public DataException(String message, Exception exception)
    {
        super(message, exception);
    }
}
