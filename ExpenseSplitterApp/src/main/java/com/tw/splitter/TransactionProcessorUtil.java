package com.tw.splitter;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TransactionProcessorUtil {
    private static final Logger logger = Logger.getLogger(TransactionProcessorUtil.class.getName());

    public static Set<String> extractParticipantsSet(List<Transaction> transactionList) {
        return transactionList.stream()
                .filter(Objects::nonNull)
                .flatMap(txnObj ->
                        Stream.concat(Stream.of(txnObj.getPayer()), txnObj.getBeneficiaries().stream())
                ).collect(Collectors.toSet());
    }

    public static Map<String, Integer> mapParticipantNameToIndex(Set<String> participants) {
        List<String> participantList = participants.stream().toList();
        Map<String, Integer> nameToIndex = new HashMap<>();
        IntStream.range(0, participants.size())
                .forEach(i -> nameToIndex
                        .put(participantList.get(i), i));
        logger.info("Mapped participant names to matrix indices.");
        return nameToIndex;
    }

    public static Transaction parseTransactionStatement(String transactionStmt) {
        try {
            /*[Person] spent [Amount] for [Item] for [List of people]*/
            String transactionRegex = "(\\w+)\\s+spent\\s+(\\d+)\\s+for\\s+(\\w+)\\s+for\\s+([\\w+,\\s+]+)";
            Pattern pattern = Pattern.compile(transactionRegex);
            Matcher matcher = pattern.matcher(transactionStmt);

            if (matcher.matches()) {
                Transaction transaction = new Transaction();
                transaction.setPayer(matcher.group(1));
                transaction.setAmount(Integer.parseInt(matcher.group(2)));
                transaction.setItem(matcher.group(3));
                transaction.setBeneficiaries(Arrays.stream(matcher.group(4).split(","))
                        .map(String::trim).toList());
                logger.info(String.format("Parsed: payer=%s, amount=%f, item=%s, beneficiaries=%s",
                        transaction.getPayer(), transaction.getAmount(),
                        transaction.getItem(), transaction.getBeneficiaries().toString()));
                return transaction;
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error while reading transactions: " + ex.getMessage(), ex);
            throw ex;
        }

        return null;
    }
}
