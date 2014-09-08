package stock;

import stock.operation.Operable;

import java.util.TreeSet;

public class StockAccount implements IAccount {


    private String empId;
    private TreeSet<Operable> operations;
    // stock amount
    private int stockBalance;
    private int saleValue;
    private int gainValue;
    private int buyInPrice;

    public StockAccount(String empId) {
        this.empId = empId;
        this.operations = new TreeSet<Operable>();

    }

    public static int getIntPrice(String price) {

        Integer intPrice = ((Double) (Double.valueOf(price) * 100.0)).intValue();
        return intPrice;
    }

    public static String outputPrice(Integer price) {
        return String.format("%.2f", (price / 100.0));
    }

    public int getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(int saleValue) {
        this.saleValue = saleValue;
    }

    public int getGainValue() {
        return gainValue;
    }

    public void setGainValue(int gainValue) {
        this.gainValue = gainValue;
    }

    public int getBuyInPrice() {
        return buyInPrice;
    }

    public void setBuyInPrice(int buyInPrice) {
        this.buyInPrice = buyInPrice;
    }


    @Override
    public String getAccountNum() {
        return this.empId;
    }

    @Override
    public void setAccountNum(String accountNum) {
        this.empId = accountNum;
    }

    @Override
    public int getBalance() {
        return stockBalance;
    }

    @Override
    public void setBalance(int balance) {
        this.stockBalance = balance;
    }

    @Override
    public void addOperation(Operable op) {
        this.operations.add(op);
    }

    public void calGains(String price, String dt) {

        int runPrice = Integer.valueOf(StockAccount.getIntPrice(price));
        int runDate = Integer.valueOf(dt);

        Operable initVest = this.operations.first();
        if (! "VEST".equals(initVest.getOpName()) ) {
            throw new StockAccountException("There must be 1 initial VEST operation");
        }

        // run through all the operations,
        // and calculate sale gains if any.
        for (Operable op : this.operations) {

            if (op.getOpDate() > runDate) {
                break;
            }
            op.operate(this);
        }

        // calculate gains
        this.setGainValue(this.getBalance() * (runPrice - this.getBuyInPrice()));

    }

    public static class StockAccountException extends RuntimeException {

        public StockAccountException(String message) {
            super(message);
        }
    }




}
