package stock;

import stock.operation.Operable;
import stock.operation.OperationFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockCalculator {

    private HashMap<String, IAccount> accounts;

    public HashMap<String, IAccount> run(String csvFile) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        accounts = new HashMap<String, IAccount>();

        try {

            br = new BufferedReader(new FileReader(csvFile));

            int recordCnt = Integer.valueOf(br.readLine());

            for (; recordCnt>0; recordCnt--) {

                line = br.readLine();
                String[] values = line.split(cvsSplitBy);

                String opName = values[0];
                String accountId = values[1];
                String opDate = values[2];

                List<String> args = new ArrayList<String>();
                for (int i=3; i<values.length; i++) {
                    args.add(values[i]);
                }

                Operable op = OperationFactory.createOperation(opName, opDate, args);
                IAccount account = this.accounts.get(accountId);
                if ( account == null ) {
                    account = new StockAccount(accountId);
                    this.accounts.put(accountId, account);
                }
                account.addOperation(op);
            }

            // last line
            line = br.readLine();
            String[] values = line.split(cvsSplitBy);
            String runDate = values[0];
            String runPrice = values[1];

            // calculate and output
            for ( String accountId : this.accounts.keySet()) {
                StockAccount account = (StockAccount) this.accounts.get(accountId);
                account.calGains(runPrice, runDate);

                System.out.println(accountId + "," + StockAccount.outputPrice(account.getGainValue())+ "," + StockAccount.outputPrice(account.getSaleValue()));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return accounts;

    }


}