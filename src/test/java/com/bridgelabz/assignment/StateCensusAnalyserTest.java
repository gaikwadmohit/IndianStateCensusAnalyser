package com.bridgelabz.assignment;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateCensusAnalyserTest {
    private static final String CORRECT_CSV_FILE_PATH = "src/main/resources/IndianStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "src/main/resources/IndianStateCensusData.csv";
    private static final String INCORRECT_FILE_FORMAT = "src/main/resources/IncorrectFileFormat.txt";
    private static final String CSV_WITH_WRONG_DELIMITER = "src/main/resources/CensusDataWithWrongDelimiter.csv";
    private static final String CSV_WITH_INCORRECT_HEADER = "src/main/resources/CensusDataIncorrectHeader.csv";
    @Test
    public void givenIndianCensusCSVFile_WhenCorrect_ReturnsNumberOfRows() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int rows = stateCensusAnalyser.loadIndianCensusData(CORRECT_CSV_FILE_PATH);
            Assert.assertEquals(29,rows);
        }
        catch(CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianCensusCSVFile_WhenWrongPath_ReturnsException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            int rows = stateCensusAnalyser.loadIndianCensusData(WRONG_CSV_FILE_PATH);
        }
        catch( CensusAnalyserException e) {
            e.getMessage();
        }
    }

    @Test
    public void givenIndianCensusCSVFile_WhenCorrectPathButWrongFileFormat_ShouldThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            int rows = stateCensusAnalyser.loadIndianCensusData(INCORRECT_FILE_FORMAT);
        }
        catch (CensusAnalyserException e) {
            e.getMessage();
            assertEquals(e.type, CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    @Test
    public void givenWrongDelimiter_InIndiaCensusData_ShouldReturnCustomExceptionType() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            int rows = stateCensusAnalyser.loadIndianCensusData(CSV_WITH_WRONG_DELIMITER);
        } catch (CensusAnalyserException e) {
            assertEquals(e.type, CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }

    @Test
    public void givenIndianCensusCSVFile_WhenIncorrectHeader_ShouldThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int rows = stateCensusAnalyser.loadIndianCensusData(CSV_WITH_INCORRECT_HEADER);
        } catch (CensusAnalyserException e) {
            assertEquals(e.type, CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }
}