package Se.kth.iv1350.Project1PointOfSale.controller;
/**
 * Get that when the database calling is failed.
 */
public class OperationFailedException extends Exception {
    public OperationFailedException(String msg, Exception cause)
    {
        super(msg, cause);
    }
    /**
     * Creates a new instance with a message specifying the error.
     * @param message
     * @param throwable
     */
    public OperationFailedException(String message, Throwable throwable) {
        super(message, throwable);
    }

}




