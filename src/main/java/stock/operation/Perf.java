package stock.operation;

import stock.StockAccount;

import java.math.BigDecimal;
import java.util.List;


public class Perf extends AbstractOperation {

    private double perfMultiplier;

    public Perf(String opName, String opDate, int numParams, List<String> args) {

        super(opName, opDate, numParams);

        this.checkParams(args);

        try {
            this.perfMultiplier = Double.valueOf(args.get(0));
        } catch (Exception e) {
            throw new StockAccount.StockAccountException("Cannot parse parameters. " + e.getMessage());
        }

    }



    @Override
    public StockAccount operate(StockAccount account) {

        int originalBalance = account.getBalance();

        // calculate balance
        BigDecimal newBalance  = BigDecimal.valueOf( originalBalance * this.perfMultiplier).setScale(2, BigDecimal.ROUND_HALF_UP);
        account.setBalance(newBalance.intValue());

        return account;
    }
}
