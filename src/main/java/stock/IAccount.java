package stock;

import stock.operation.Operable;

import java.io.Serializable;


public interface IAccount extends Serializable {

    public String getAccountNum();

    public void setAccountNum(String accountNum);

    public int getBalance();

    public void setBalance(int balance);

    public void addOperation(Operable op);

}
