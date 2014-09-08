package stock.operation;

import stock.StockAccount;

import java.util.List;


public abstract class AbstractOperation implements Operable, Comparable<AbstractOperation> {

    private String opName;

    private int numParams;

    private int opDate;

    public AbstractOperation(String opName, String opDate, int numParams) {

        this.numParams = numParams;
        this.opName = opName;
        this.opDate = Integer.valueOf(opDate);

    }

    public void checkParams(List<String> params) {

        if (params.size() != this.numParams) {
            throw new StockAccount.StockAccountException("Params number of function '" + this.opName + "' is not correct.");
        }
    }

    @Override
    public String getOpName() {
        return this.opName;

    }

    @Override
    public Integer getOpDate() {
        return this.opDate;

    }

    @Override
    public int compareTo(AbstractOperation o) {

        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if ("VEST".equals(opName)) {
            return BEFORE;    // make sure run "VEST" in the first place
        }

        if (this.opDate < o.opDate) {
            return BEFORE;
        } else if (this.opDate > o.opDate) {
            return AFTER;
        }

        return EQUAL;
    }
}
