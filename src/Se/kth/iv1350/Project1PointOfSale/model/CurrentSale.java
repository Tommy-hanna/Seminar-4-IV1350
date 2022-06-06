package Se.kth.iv1350.Project1PointOfSale.model;
        import Se.kth.iv1350.Project1PointOfSale.integration.*;
        import java.util.ArrayList;
        import java.util.List;
        import java.time.LocalTime;

/**
 * This class current sale to handle the shoping that the customer made.
 * @author tommy
 */

public class CurrentSale {
    private LocalTime saleTime;
    private double vat;
    private int paidAmount;
    private double change;
    private List<ItemDTO> items = new ArrayList<ItemDTO>();
    private double totalPrice;
    private int quantity;
    private double discount = 0.1;
    private CustomerDTO customerDTO;
    private String customerID;
    private ItemDTO itemDTO;
    private List<CurrentSaleObserver> currentSaleObserver = new ArrayList<>();


    /**
     * this instance to make the time of current sale.
     */
    public CurrentSale() {

    }

    /**
     * This method to get the information of item and quantity.
     * @param itemDTO
     * @param quantity
     * @return
     */
    public String count(ItemDTO itemDTO, int quantity) {
        itemDTO.setQuantity(quantity);
        items.add(itemDTO);
        return itemDTO.getItemID()+ itemDTO.getItemDescription()+ itemDTO.getItemQuantity() ;
    }
    /**
     * This instance to show
     * if the item is already scanned so it multiply it.
     * @param itemBefore
     * @return
     */
    private Boolean itemInSaleAreadyExsist(ItemDTO itemBefore) {
        for(ItemDTO item: items){
            if(itemBefore.getItemID() == item.getItemID())
                return true;
        }
        return false;
    }
    /**
     * This instance to update the total price.
     * @return
     */
    public String updateTotalPrice() {
        double totalVAT = 0;
        for (ItemDTO item : items) {
            totalVAT += ((item.getPrice() * item.getVat() * item.getItemQuantity()));
            totalPrice += (item.getPrice() * item.getItemQuantity());
        }
        totalPrice = (totalVAT + totalPrice);
        return ("total price: " + (totalPrice + totalVAT));
    }
    /**
     * This instance to get items list.
     * @return
     */
    public List<ItemDTO> getItems(){
        return items;
    }

    /**
     * This instance to get total price.
     * @return
     */
    public  double getTotalPrice(){
        return totalPrice;
    }


    /**
     * This instance to get vat.
     * @return
     */
    public double getVat(){
        return vat;
    }
    public  String getTimeOfSale(){
        return String.valueOf(this.saleTime);
    }

    /**
     * This method to print out the receipt.
     * @param printer
     * @param storDTO
     */
    public void printOutTheReceipt(Printer printer, StorDTO storDTO){
        Receipt receipt = new Receipt(this, storDTO);
        printer.printOutTheReceipt(receipt.toString());
    }

    /**
     * This metod to get discount.
     * @return discount.
     */
    public double getDiscount (){
        return this.discount;
    }

    /**
     * This method to get total price after discount.
     * @return total price after discount.
     */

    public double getTotalPriceAfterDiscount(){

        return this.discount * totalPrice;

    }

    /**
     * This method to get change between price and discount.
     * @return change.
     */
    public double getChange(){
        change = totalPrice- this.getTotalPriceAfterDiscount();
        return change;
    }

    /**
     * Updatera the total revenue in the observers.
     *
     */
    private void notifyObservers() {
        for (CurrentSaleObserver obs : currentSaleObserver) {
            obs.updateTotalRevenue(getTotalPriceAfterDiscount());
        }
    }

    /**
     * Observers going to notifies when total paid.
     * @param obs
     */
    public void addCurrentSaleObserver(List<CurrentSaleObserver> obs) {
        currentSaleObserver.addAll(obs);
    }
}

