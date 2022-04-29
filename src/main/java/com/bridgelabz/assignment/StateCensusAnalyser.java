package com.bridgelabz.assignment;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


public class StateCensusAnalyser {
    private static List<CSVStateCensus> csvFileList = new ArrayList<>();
    private static List<CSVStates> csvStateCodeList = new ArrayList<>();

    public int loadIndianCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            if (!csvFilePath.endsWith(".csv"))
                throw new CensusAnalyserException("File must be in CSV format", CensusAnalyserException.ExceptionType.INCORRECT_FILE_FORMAT);

            CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVStateCensus.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
            Iterator<CSVStateCensus> censusCSVIterator = csvToBean.iterator();

            while (censusCSVIterator.hasNext()) {
                csvFileList.add(censusCSVIterator.next());
            }
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (NullPointerException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }

        return csvFileList.size();
    }

    public int loadIndianStateCode(String csvFilePath) throws CensusAnalyserException {
        try {
            if (!csvFilePath.endsWith(".csv"))
                throw new CensusAnalyserException("File must be in CSV format", CensusAnalyserException.ExceptionType.INCORRECT_FILE_FORMAT);
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<CSVStates> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(CSVStates.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<CSVStates> csvToBean = csvToBeanBuilder.build();
            Iterator<CSVStates> stateCodeIterator = csvToBean.iterator();

            while (stateCodeIterator.hasNext()) {
                csvStateCodeList.add(stateCodeIterator.next());
            }
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
        }
        return csvStateCodeList.size();
    }
}
