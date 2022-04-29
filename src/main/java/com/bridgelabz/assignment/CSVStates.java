package com.bridgelabz.assignment;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {
    @CsvBindByName (column = "SrNo", required =true)
    public int SrNo;
    @CsvBindByName (column = "State Name", required =true)
    public String state;
    @CsvBindByName (column = "TIN", required =true)
    public int TIN;
    @CsvBindByName (column = "StateCode", required =true)
    public String stateCode;

    @Override
    public String toString() {
        return "CSVStates [SrNo= " + this.SrNo + ", State= " + this.state+ ", TIN= "+ this.TIN +", StateCode= "+ this.stateCode;
    }
}