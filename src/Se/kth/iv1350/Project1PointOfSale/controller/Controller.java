package Se.kth.iv1350.Project1PointOfSale.controller;
        import Se.kth.iv1350.Project1PointOfSale.integration.*;
        import Se.kth.iv1350.Project1PointOfSale.model.*;
        import Se.kth.iv1350.Project1PointOfSale.view.TotalRevenueView;

        import java.io.PrintStream;
        import java.util.ArrayList;
        import java.util.List;

        import static Se.kth.iv1350.Project1PointOfSale.integration.InventorySystem.*;

/**
 * This is the applications only controller.
 * All calls to the model path through this class.
 * @author tommy
 */
public class Controller {

    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private InformationForDiscount informationForDiscount;
    private Printer printer;
    private CurrentSale currentSale;
    private StorDTO storDTO;
    private ItemDTO itemDTO;
    private CustomerDTO customerDTO;
    private List<CurrentSaleObserver> currentSaleObserver = new ArrayList<>();


    public Controller(Creator creator, Printer printer, StorDTO storDTO){
        this.accountingSystem = creator.getAccountingSystem();
        this.inventorySystem = creator.getInventorySystem();
        this.informationForDiscount = creator.getInformationForDiscount();
        this.printer = printer;
        this.storDTO = storDTO;

    }

    /**
     * This method to start new sale.
     */
    public void startNewSale(){

        this.currentSale = new CurrentSale();
        currentSale.addCurrentSaleObserver(currentSaleObserver);
    }

    /**
     * This method to search to the item and then to scan the specified item.
     * @param itemID this is the id of item that the search item looking for to scan the item.
     * @param quantity the amount of quantity.
     * @return return the string of items if the item is existed and if it not,
     * so its return the item is not valid.
     * @throws ItemRegistrationException if the itemId is not valid.
     * @throws OperationFailedException if unable to connect the Server.
     */
    public String scanItem(String itemID, int quantity) throws ItemRegistrationException, OperationFailedException {
        try {
            itemDTO = inventorySystem.getItemInfo(itemID, quantity);
            return currentSale.count(itemDTO, quantity);
        } catch (ItemRegistrationException e) {
            throw new OperationFailedException("Can not connect the Server", e);
        }
    }


    /**
     * This method shows the total price with vat.
     * @return a string that shows total price with vat.
     */
    public String displayTotaPrice(){
        return new StringBuilder().append("total with VAT: ").append(currentSale.getTotalPrice()).toString();
    }

    /**
     * This method to set all information for discount.
     * @param customerID represent the customer id.
     */
    public void setInformationForDiscount(String customerID){
        customerDTO.getCustomerID();
        informationForDiscount.getDiscount();
        currentSale.getDiscount();
    }

    /**
     * This method to accept the discount
     * if the customer is member in this supermarket.
     * @return
     */
    public void acceptDiscount(){
        if(customerDTO.getCustomerID() == "1"){
            displayTotalPriceAfterDiscount();
        }
        if(customerDTO.getCustomerID() == "2"){
            displayTotalPriceAfterDiscount();        }

    }

    /**
     * This method to display the total price after discount.
     * @return string builder that represent the total price after discount.
     */
    public String displayTotalPriceAfterDiscount(){
        return new StringBuilder().append("total with Discount: ").append(currentSale.getTotalPrice() * currentSale.getDiscount()).toString();
    }

    /**
     * This method to print out the receipt.
     */

    public void printOutTheReceipt(){
        currentSale.printOutTheReceipt(printer, storDTO);
    }

    /**
     * this method to end the sale.
     */
    public void endTheSale(){
        double totalPrice = currentSale.getTotalPrice();

    }

    public void addCurrentSaleObserver(CurrentSaleObserver obs) {
        currentSaleObserver.add(obs);
    }
}

