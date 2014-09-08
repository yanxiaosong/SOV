package stock.operation;

import stock.StockAccount;

import java.util.List;


public class Vest extends AbstractOperation {


    private Integer amount;

    private Integer price;


    public Vest(String opName, String opDate, int numParams, List<String> args) {

        super(opName, opDate, numParams);

        this.checkParams(args);

        try {
            this.amount = Integer.valueOf(args.get(0));
            this.price =  StockAccount.getIntPrice(args.get(1));
        } catch (Exception e) {
            throw new StockAccount.StockAccountException("Cannot parse parameters. " + e.getMessage());
        }

    }

    @Override
    public StockAccount operate(StockAccount account) {

        // calculate balance
        if (account.getBalance() > 0 ) {
            throw new StockAccount.StockAccountException("Cannot vest for multiple times.");
        }

        account.setBalance(this.amount);

        // set gains
        account.setGainValue(0);

        // set buy in price
        account.setBuyInPrice(this.price);

        return account;
    }
}
