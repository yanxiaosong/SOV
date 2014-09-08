package stock;


import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class StockCalculatorTest {

    @Test
    public void testStockCalculation() {

        StockCalculator calculator = new  StockCalculator();

        // TEST for 11.data
        String datafile11 = "11.data";
        HashMap<String, IAccount> accounts =  calculator.run(datafile11);
        assertEquals(accounts.size(),2);

        for (String accountId : accounts.keySet()) {

            StockAccount account  = (StockAccount) accounts.get(accountId);
            if ("001B".equals(accountId)) {
                assertEquals("412.50", StockAccount.outputPrice(account.getGainValue()));
                assertEquals("275.00", StockAccount.outputPrice(account.getSaleValue()));
            }

            if ("002B".equals(accountId)) {
                assertEquals("825.00", StockAccount.outputPrice(account.getGainValue()));
                assertEquals("0.00", StockAccount.outputPrice(account.getSaleValue()));
            }

        }

        // TEST for 12.data
        String datafile12 = "12.data";

        accounts =  calculator.run(datafile12);
        assertEquals(accounts.size(),2);

        for (String accountId : accounts.keySet()) {

            StockAccount account  = (StockAccount) accounts.get(accountId);
            if ("001B".equals(accountId)) {
                assertEquals("275.00", StockAccount.outputPrice(account.getGainValue()));
                assertEquals("275.00", StockAccount.outputPrice(account.getSaleValue()));
            }

            if ("002B".equals(accountId)) {
                assertEquals("550.00", StockAccount.outputPrice(account.getGainValue()));
                assertEquals("0.00", StockAccount.outputPrice(account.getSaleValue()));
            }

        }


    }

}
