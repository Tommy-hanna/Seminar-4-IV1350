package Se.kth.iv1350.Project1PointOfSale.view;
        import Se.kth.iv1350.Project1PointOfSale.controller.*;
        import Se.kth.iv1350.Project1PointOfSale.model.ItemRegistrationException;

        import java.io.FileWriter;
        import java.io.IOException;
        import java.io.PrintWriter;

/**
 * This is a placeholder for real view.
 * It contains a hard coded execution with calls to all systems operations in the controller.
 * @author tommy
 */
public class View {
    private Controller controller;
    private PrintWriter log_File;
    private ErrorMsgPrinter errorMsgPrinter = new ErrorMsgPrinter();
    /**
     * Creat a new instance that uses the specified controller for all calls to other layer.
     * @param controller
     */
    public View(Controller controller) throws IOException {
        this.controller = controller;
        controller.addCurrentSaleObserver(new TotalRevenueView());
        controller.addCurrentSaleObserver(new TotalRevenueFileOutPut());
        log_File = new PrintWriter(new FileWriter("log-Error"), true);
    }

    /**
     * Perform a sale fake by calling all systems operations in the controller.
     */
    public void fakeExecution() throws OperationFailedException {
        System.out.println("Cashier starts new sale");
        controller.startNewSale();
        System.out.println("Cashier enter items:");
        try {
            EnterItemView("Chips", 1);
            EnterItemView("Chips", 1);
            EnterItemView("popcorn", 2);
            controller.displayTotaPrice();
            controller.displayTotalPriceAfterDiscount();
            controller.printOutTheReceipt();
        } catch (ItemRegistrationException e) {
            System.out.println(" This item is not found");
        } catch (RuntimeException e) {
            System.out.println(" Database is not found ");
        }
    }

    /**
     * Create a new fake execution.
     * @param itemID
     * @param quantity
     * @throws ItemRegistrationException if itemID is not valid.
     */
    public void EnterItemView(String itemID, int quantity)throws ItemRegistrationException {
        try {
            System.out.println(controller.scanItem(itemID, quantity));
        } catch (ItemRegistrationException e){
                    errorMsgPrinter.printOutErrorMsg(e);
                    logWriter(e);
        }catch (OperationFailedException e){
            errorMsgPrinter.printOutErrorMsg(e);
            logWriter(e);
        }
    }

    /**
     * Here to write a Logs the error message for the developers.
     * @param e
     */
        public void logWriter(Exception e){
            String msg = " * Error for developer * ";
            System.out.println(msg);
            log_File.println(msg);
            e.printStackTrace(System.out);
            e.printStackTrace(log_File);
            msg = "---------------------------------------\n";
            log_File.println(msg);
            System.out.println(msg);
        }
    }

