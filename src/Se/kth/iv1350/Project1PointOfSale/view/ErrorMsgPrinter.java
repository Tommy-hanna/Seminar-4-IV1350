package Se.kth.iv1350.Project1PointOfSale.view;

/**
 * This class to print out the error message to user.
 */
public class ErrorMsgPrinter {
    /**
     * Print out error message.
     *
     * @param e
     */
    void printOutErrorMsg(Exception e) {
        System.out.println(" * Error for user * \n" + e.getMessage() + "\n---------------------------------------");
    }
}
