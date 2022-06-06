package Se.kth.iv1350.Project1PointOfSale.integration;
        import Se.kth.iv1350.Project1PointOfSale.model.ItemRegistrationException;

        import java.util.ArrayList;
        import java.util.List;


/**
 * This class to handle all available items.
 * @author tommy
 */
public class InventorySystem {


    private static List<ItemDTO> listOfItems = new ArrayList<ItemDTO>();

    /**
     * This instance to add all items in an arraylist.
     */

    public  InventorySystem() {
        addItems();

    }


    /**
     * This instance to look after the items.
     * @param itemID
     * @throws RuntimeException if unable to connect the database.
     */

    public static boolean searchItem(String itemID){
        if (itemID == null)
            throw new RuntimeException(" We can not connect the database.");

        for(ItemDTO  listOfItems: listOfItems){
            if (listOfItems.getItemID().equals(itemID)){
                return true;
            }
        }

        return false;
    }



    /**
     * this instance to get the item with his information.
     * @param itemID
     * @param quantity
     * @throws ItemRegistrationException if the itemId is not found in the System.
     */
    public static ItemDTO getItemInfo(String itemID, int quantity) throws ItemRegistrationException {
        for (ItemDTO item : listOfItems) {
            if (item.getItemID().equals(itemID) && item.getItemQuantity() >= quantity) {
                throw new ItemRegistrationException(itemID);
            }
            return new ItemDTO(item.getItemDescription(), item.getItemID()
                    , quantity, item.getPrice(), item.getVat());
        }
        return null;
    }



    /**
     * This method to add all items that is in the inventory.
     */
    private void addItems(){
        listOfItems.add(new ItemDTO("1","Chips",10, 20.0, 0.01));
        listOfItems.add(new ItemDTO("2","Sallad",20, 10.0, 0.01));
        listOfItems.add(new ItemDTO("3","Candy",10,5.0, 0.01));
        listOfItems.add(new ItemDTO("4","Cola",10,5.0, 0.01));
        listOfItems.add(new ItemDTO("5","Juce",10,5.0, 0.01));
    }

    /**
     * This instance to update the inventory System after sale finished.
     */
    public void updateInventorySystem(){

    }

}



