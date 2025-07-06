package com.tw.splitter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionProcessorTest {

    private TransactionProcessor transactionProcessor;

    @BeforeEach
    void setUp() {
        transactionProcessor = new TransactionProcessor();
    }

    @Test
    void testSetTransactionList_shouldParseValidTransactions() {
        List<String> input = List.of("A spent 120 for snacks for A,B,C");

        transactionProcessor.setTransactionList(input);

        double[][] matrix = transactionProcessor.getTransactionMatrix();
        assertNull(matrix);
    }

    @Test
    void testCalculateOwedAmounts_shouldPopulateTransactionMatrix() {
        List<String> input = List.of("A spent 120 for snacks for A,B,C");

        transactionProcessor.setTransactionList(input);
        transactionProcessor.calculateOwedAmounts();

        double[][] matrix = transactionProcessor.getTransactionMatrix();
        assertNotNull(matrix);
        assertEquals(3, matrix.length);
        assertEquals(3, matrix[0].length);

        // B owes A 40, C owes A 40
        double bToA = matrix[1][0];
        double cToA = matrix[2][0];
        assertEquals(40.0, bToA);
        assertEquals(40.0, cToA);
    }

    @Test
    void testOptimizeOwedAmounts_shouldReduceMutualDebts() {
        List<String> input = List.of("A spent 120 for food for A,B,C", // B,C owe A 40 each
                "B spent 60 for drinks for A,B",  // A owes B 30
                "C spent 40 for tea for A,C"      // A owes C 20
        );

        transactionProcessor.setTransactionList(input);
        transactionProcessor.calculateOwedAmounts();
        transactionProcessor.optimizeOwedAmounts();

        double[][] matrix = transactionProcessor.getTransactionMatrix();
        assertEquals(10.0, matrix[1][0]); // B -> A
        assertEquals(20.0, matrix[2][0]); // C -> A
    }

    @Test
    void testPrintBalances_shouldNotThrow() {
        List<String> input = List.of("A spent 90 for lunch for A,B,C");

        transactionProcessor.setTransactionList(input);
        transactionProcessor.calculateOwedAmounts();
        transactionProcessor.optimizeOwedAmounts();

        assertDoesNotThrow(() -> transactionProcessor.printBalances());
    }

    @Test
    void testSetTransactionList_withInvalidStatement_shouldIgnoreIt() {
        List<String> input = List.of("Invalid input text", "B spent 90 for snacks for B,C");

        transactionProcessor.setTransactionList(input);
        transactionProcessor.calculateOwedAmounts();

        double[][] matrix = transactionProcessor.getTransactionMatrix();
        assertEquals(45.0, matrix[1][0]); // C -> B
    }

    @Test
    void testEmptyTransactionList_shouldThrow() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            transactionProcessor.calculateOwedAmounts();
        });

        assertEquals("Transaction list is empty.", exception.getMessage());
    }
}