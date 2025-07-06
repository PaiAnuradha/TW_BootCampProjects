package com.tw.splitter;

import java.util.List;

public interface ExpenseSettlementService {
     void setTransactionList(List<String> transactionStatements);
     void calculateOwedAmounts();
     void optimizeOwedAmounts();
     void printBalances();
}
