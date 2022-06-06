package Se.kth.iv1350.Project1PointOfSale.model;

import Se.kth.iv1350.Project1PointOfSale.integration.InventorySystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRegistrationExceptionTest {
    private InventorySystem instance;

    @BeforeEach
    void setUp() { instance = new InventorySystem();
    }

    @AfterEach
    void tearDown() { instance= null;
    }
    @Test
    void testItemIsNotExist(){
        String notvalid = "there is no item";
        try {
            instance.getItemInfo(notvalid, 0);

        } catch (ItemRegistrationException   e) {
            assertTrue(e.getMessage().contains(e.getItemNotExist()));
            assertEquals(e.getItemNotExist(), notvalid);
        }
    }
    @Test
    void getMessage() {
        String notvalid = "tere is no item";
        String expResult = notvalid ;
        try {
            instance.getItemInfo(notvalid, 0);
        } catch (ItemRegistrationException e) {
            assertTrue(e.getMessage().contains(notvalid));
            assertEquals(e.getMessage(), expResult);
        }
    }
}