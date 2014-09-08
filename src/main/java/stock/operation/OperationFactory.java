package stock.operation;

import java.util.List;

public class OperationFactory {

    public static final String PERF = "PERF";

    public static final String SALE = "SALE";

    public static final String VEST = "VEST";


    public static Operable createOperation(String opName, String opDate, List<String> args) {

        if (PERF.equals(opName)) {
            return new Perf(opName, opDate, 1, args);
        }  else if (SALE.equals(opName)) {
            return new Sale(opName, opDate, 2, args);
        }  else if (VEST.equals(opName)) {
            return new Vest(opName, opDate, 2, args);
        } else {
            throw new RuntimeException("Unrecognizeable operation " + opName );
        }

    }

}
