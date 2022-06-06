//
// Source code recreated from a .class file by IntelliJ IDEA
// (po
package Se.kth.iv1350.Project1PointOfSale.model;

/**
 * Thrown when the specified itemIdentifier
 * could not be found in itemRegistry.
 */
public class ItemRegistrationException extends RuntimeException {
    private String itemID;
    public ItemRegistrationException(String message) {
        super("Item "+ message+ "does not exist ");
        this.itemID = message;
    }
    /**
     * Creates a new instance with a message error.
     * @param message
     * @param throwable
     */
    public ItemRegistrationException(String message, Throwable throwable) {
        super(message, throwable);
    }
    /**
     * @return the itemIdentifier that does not exist.
     */
    public String getItemNotExist() {
        return itemID;
    }
}
