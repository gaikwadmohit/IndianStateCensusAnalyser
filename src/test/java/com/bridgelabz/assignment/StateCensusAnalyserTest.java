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
    private static final String CORRECT_STATE_CODE_FILE_PATH = "src/main/resources/IndiaStateCode.csv";
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
            assertEquals(e.type, CensusAnalyserException.ExceptionType.INCORRECT_FILE_FORMAT);
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


    @Test
    public void givenIndianStateCodeCSVFile_WhenCorrect_ReturnsNumberOfRows() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int rows = stateCensusAnalyser.loadIndianStateCode(CORRECT_STATE_CODE_FILE_PATH);
            Assert.assertEquals(36,rows);
        }
        catch(CensusAnalyserException e) {

        }
    }

    @Test
    public void givenIndianStateCodeCSVFile_WhenWrongPath_ReturnsException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            int rows = stateCensusAnalyser.loadIndianStateCode(WRONG_CSV_FILE_PATH);
        }
        catch( CensusAnalyserException e) {
            assertEquals(e.type, CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            e.getMessage();
        }
    }
    @Test
    public void givenIndianStateCodeCSVFile_WhenCorrectPathButWrongFileFormat_ShouldThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            int rows = stateCensusAnalyser.loadIndianStateCode(INCORRECT_FILE_FORMAT);
        }
        catch (CensusAnalyserException e) {
            System.out.println(e.getMessage());
            assertEquals(e.type, CensusAnalyserException.ExceptionType.INCORRECT_FILE_FORMAT);
        }
    }

    @Test
    public void givenWrongDelimiter_InIndiaStateCodeCSV_ShouldReturnCustomExceptionType() {
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
    public void givenIndianStateCodeCSVFile_WhenIncorrectHeader_ShouldThrowException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
            int rows = stateCensusAnalyser.loadIndianStateCode(CSV_WITH_INCORRECT_HEADER);
        } catch (CensusAnalyserException e) {
            assertEquals(e.type, CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
    }
}