Stock Option Vesting
============================

## Overview
As part of a robust compensation package, employees are given the option to buy stock (a “stock option”) at a favorable set price (the “grant” or “strike” price) after a certain period of time as passed (the “vesting period”) The date that the options are granted is known as the “grant date”, and the date the options are available is known as the “vest date”

## Input & Output Format

**Input**: The input consists of a record count N followed by N comma delimited rows. Each row will have 5 fields. The first field will be the word “VEST”. The second field is an arbitrary string representing an individual employee. The third will be the vest date in YYYYMMDD format. The fourth is the amount of units that are vesting. The fifth and final field is the grant price for those options it is a decimal number round to two places – currency is ignored in this case. There is one final input line that consists of a date in YYYYMMDD format and a market price for the stock as at that date.

**Output**: Output will be a three field comma delimited row for each employee (sorted by employee identifier).

The first field will be the employee identifier.

The second field will be the total cash gain available for that employee – ignore currency and display as a decimal number rounded to 2 places.
Total gain is calculated as market price – grant price.

The third field will be total gain through sale.


## Definitions: 5 kinds of operations

* VEST: buy stock option at a favorable price.

* PERM: For exceptional performance, occasionally companies will use a multiplier against the number of granted units as an extra bonus.

* SALE: After options have vested, to realize value, employees can sell their options on the market (they also need to buy them, but that will not be taken into account for this problem).


## Example:

Input:

```
5
VEST,001B,20120102,1000,0.45
SALE,001B,20120402,500,1.00
VEST,002B,20120102,1000,0.45
PERF,001B,20130102,1.5
PERF,002B,20130102,1.5
20140101,1.00
```

Output:

```
001B,412.50,275.00
002B,825.00,0.00
```

If final date was changed to 20130101:

```
001B,275.00,275.00
002B,550.00,0.00
```


## Source Code Explained:

* stock.Main.java,  entry point of executable jar.
* stock.StockCaculator.java, will read the csv file and run through it.  It plays the role of coordinator, getting the calculation job done.
* stock.StockAccount.java,  account object for stock.  Employee Id will be used as Account Id.  Account is a place to keep all the operations that are performed on it, and account also keeps important data like stock balance,  gain value, sale value and etc.
* stock.Operation.Perf/Sale/Vest/....,  these are definitions of operation that are performed on an account, causing changes to stock balance, gains, sale value, etc..
* stock.Operation.OperationFactory.java,  an object creation factory for Operations.

An account can has multiple operations attached to it.  Operations are added to an account and sorted by transaction date.

And in order to simplify calculation,  Price data like "5.00" will be multiple 100 and become an integer "500".  It will be converted back to "5.00" when output.