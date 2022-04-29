package com.bridgelabz.assignment;

public class CensusAnalyserException extends Exception {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM, CSV_FILE_INTERNAL_ISSUES, INCORRECT_FILE_FORMAT, NO_CENSUS_DATA
    }

    ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}