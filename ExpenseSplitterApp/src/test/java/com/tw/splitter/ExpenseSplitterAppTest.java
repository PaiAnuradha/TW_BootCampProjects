package com.tw.splitter;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseSplitterAppTest {

    final String filePath = "/testTransactions.txt";
    @Test
    void testReadTransactionsFromValidFile() throws FileNotFoundException {
        List<String> lines = ExpenseSplitterApp.readTransactionsFromFile(filePath);
        assertNotNull(lines, "Transaction list should not be null");
        assertFalse(lines.isEmpty(), "Transaction list should not be empty");
        assertTrue(lines.getFirst().contains("spent"), "First line should contain the word 'spent'");
    }

    @Test
    void testReadTransactionsFromInvalidFile_shouldThrowException() {
        String invalidPath = "/non-existent-file.txt";

        Exception exception = assertThrows(FileNotFoundException.class, () ->
                ExpenseSplitterApp.readTransactionsFromFile(invalidPath)
        );

        assertTrue(exception.getMessage().contains("File not found"), "Expected 'File not found' message");
    }
}
