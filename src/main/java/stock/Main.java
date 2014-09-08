package stock;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {

            System.out.println("Please specify the data file name.");
            System.exit(1);

        }

        StockCalculator calculator = new StockCalculator();

        String datafile = args[0];
        HashMap<String, IAccount> accounts = calculator.run(datafile);

    }
}
