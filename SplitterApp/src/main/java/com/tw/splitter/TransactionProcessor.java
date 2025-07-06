package com.tw.splitter;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TransactionProcessor  implements ExpenseSettlementService {

    private static final Logger logger = Logger.getLogger(TransactionProcessor.class.getName());
    private Map<String, Integer> nameToIndex;
    private double[][] transactionMatrix;
    private List<Transaction> transactionList;

    public double[][] getTransactionMatrix() {
        return transactionMatrix;
    }

    public void setTransactionList(List<String> transactionStatements) {
        try {
            transactionList = transactionStatements.stream()
                    .map(TransactionProcessorUtil::parseTransactionStatement)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            logger.info("Parsed and set " + transactionList.size() + " transaction(s).");
        } catch (Exception ex) {
            logger.severe("Error parsing transaction list: " + ex.getMessage());
            throw ex;
        }
    }


    public void calculateOwedAmounts() {
        try {
            if (transactionList == null || transactionList.isEmpty()) {
                throw new IllegalStateException("Transaction list is empty.");
            }
            initializeTransactionMatrix();
            for (Transaction txnObj : transactionList) {
                applyTransactionToMatrix(txnObj);
            }
            logger.info("Debt matrix generated based on transactions.");
        } catch (Exception e) {
            logger.severe("Error generating debt matrix: " + e.getMessage());
            throw e;
        }
    }

    public void optimizeOwedAmounts() {
        try {
            int n = transactionMatrix.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (transactionMatrix[i][j] > 0 && transactionMatrix[j][i] > 0) {
                        double min = Math.min(transactionMatrix[i][j], transactionMatrix[j][i]);
                        transactionMatrix[i][j] -= min;
                        transactionMatrix[j][i] -= min;
                    }
                }
            }
            logger.info("Debts simplified");
        } catch (Exception ex) {
            logger.severe("Error simplifying debts: " + ex.getMessage());
            throw ex;
        }
    }

    public void printBalances() {
        try {
            //build reverse lookup
            String[] indexToName = new String[nameToIndex.size()];
            nameToIndex.forEach((name, index) -> indexToName[index] = name);
            System.out.println("Final simplified transactions:");
            for (int i = 0; i < transactionMatrix.length; i++) {
                for (int j = 0; j < transactionMatrix.length; j++) {
                    if (transactionMatrix[i][j] > 0) {
                        String from = indexToName[i];
                        String to = indexToName[j];
                        double amount = transactionMatrix[i][j];
                        logger.info(String.format("%s pays %s %.2f", from, to, amount));
                    }
                }
            }
        } catch (Exception ex) {
            logger.severe("Error printing balances: " + ex.getMessage());
            throw ex;
        }
    }

    private void initializeTransactionMatrix() {
        Set<String> participants = TransactionProcessorUtil.extractParticipantsSet(transactionList);
        logger.info("Extracted " + participants.size() + " unique participant(s).");
        nameToIndex = TransactionProcessorUtil.mapParticipantNameToIndex(participants);
        transactionMatrix = new double[nameToIndex.size()][nameToIndex.size()];
        logger.info("Transaction matrix initialized.");
    }

    private void applyTransactionToMatrix(Transaction txnObj) {
        //transactionMatrix[i][j] means i owes j
        int payerIdx = nameToIndex.get(txnObj.getPayer());
        double share = (txnObj.getAmount()) / (txnObj.getBeneficiaries().size());
        txnObj.getBeneficiaries().stream()
                .filter(p -> !p.equals(txnObj.getPayer()))
                .map(nameToIndex::get)
                .forEach(i -> transactionMatrix[i][payerIdx] += share);
        logger.info("Extracted transaction details for payer" + txnObj.getPayer());
    }

}
