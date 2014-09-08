package stock.operation;

import stock.StockAccount;

import java.util.List;

public class Sale extends AbstractOperation{

    private Integer amount;

    private Integer price;


    public Sale(String opName, String opDate, int numParams, List<String> args) {

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
        account.setBalance(account.getBalance()-this.amount);

        // calculate sale value
        int gain = (this.price - account.getBuyInPrice()) * this.amount;
        account.setSaleValue(account.getSaleValue() + gain);

        return account;
    }
}
