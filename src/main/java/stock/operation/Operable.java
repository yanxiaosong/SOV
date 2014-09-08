package stock.operation;

import stock.IAccount;
import stock.StockAccount;

import java.util.List;


public interface Operable {

    public StockAccount operate(StockAccount account);

    public String getOpName();

    public Integer getOpDate();

}
