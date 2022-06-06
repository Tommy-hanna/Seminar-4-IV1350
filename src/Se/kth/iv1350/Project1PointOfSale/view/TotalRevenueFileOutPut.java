package Se.kth.iv1350.Project1PointOfSale.view;

import Se.kth.iv1350.Project1PointOfSale.model.CurrentSaleObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

public class TotalRevenueFileOutPut extends CurrentSaleObserver {
    private PrintWriter logStream;

    /**
     * Creates a new instance with a new total revenue file.
     */
    public TotalRevenueFileOutPut() {
        try {
            logStream = new PrintWriter(new FileWriter("Total Revenue"), true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * add the specified price to the total revenue log.
     *
     * @param totalPrice The total price for the revenue.
     */
    public void newTotalPriceRevenue(double totalPrice) {
        logStream.println("Total revenue: " + totalPrice);
    }
}

