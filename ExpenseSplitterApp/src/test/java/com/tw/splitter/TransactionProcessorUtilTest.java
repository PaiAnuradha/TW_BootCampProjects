package com.tw.splitter;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TransactionProcessorUtilTest {

    @Test
    void testExtractParticipantsSet() {
        Transaction t1 = new Transaction();
        t1.setPayer("A");
        t1.setBeneficiaries(List.of("A", "B", "C"));

        Transaction t2 = new Transaction();
        t2.setPayer("B");
        t2.setBeneficiaries(List.of("B", "A"));

        List<Transaction> transactionList = List.of(t1, t2);

        Set<String> expectedParticipants = Set.of("A", "B", "C");
        Set<String> actualParticipants = TransactionProcessorUtil.extractParticipantsSet(transactionList);

        assertEquals(expectedParticipants, actualParticipants);
    }


    @Test
    void testMapParticipantNameToIndex() {
        Set<String> participants = Set.of("A", "B", "C");

        Map<String, Integer> nameToIndex = TransactionProcessorUtil.mapParticipantNameToIndex(participants);

        assertEquals(3, nameToIndex.size());
        assertTrue(nameToIndex.containsKey("A"));
        assertTrue(nameToIndex.containsKey("B"));
        assertTrue(nameToIndex.containsKey("C"));

        Set<Integer> uniqueIndices = new HashSet<>(nameToIndex.values());
        assertEquals(3, uniqueIndices.size());
    }

    @Test
    void testParseTransactionStatement_ValidInput() {
        String input = "A spent 120 for snacks for A,B,C";

        Transaction txn = TransactionProcessorUtil.parseTransactionStatement(input);

        assertNotNull(txn);
        assertEquals("A", txn.getPayer());
        assertEquals(120.0, txn.getAmount());
        assertEquals("snacks", txn.getItem());
        assertEquals(List.of("A", "B", "C"), txn.getBeneficiaries());
    }

    @Test
    void testParseTransactionStatement_InvalidInput() {
        String input = "This is not a valid transaction";

        Transaction txn = TransactionProcessorUtil.parseTransactionStatement(input);

        assertNull(txn);
    }

    @Test
    void testParseTransactionStatement_WithExtraSpaces() {
        String input = "  B   spent  75 for drinks for B , A  ";

        Transaction txn = TransactionProcessorUtil.parseTransactionStatement(input.trim());

        assertNotNull(txn);
        assertEquals("B", txn.getPayer());
        assertEquals(75.0, txn.getAmount());
        assertEquals("drinks", txn.getItem());
        assertEquals(List.of("B", "A"), txn.getBeneficiaries());
    }

}