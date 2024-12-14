package com.example;

public class Question extends AbstractQuestion {
    private String opsiA;
    private String opsiB;
    private String opsiC;
    private String opsiD;
    private String opsiE;
    private String jawaban;

    public Question(int id, String bunyiSoal, String opsiA, String opsiB, String opsiC, String opsiD, String opsiE, String jawaban) {
        super(id, bunyiSoal);
        this.opsiA = opsiA;
        this.opsiB = opsiB;
        this.opsiC = opsiC;
        this.opsiD = opsiD;
        this.opsiE = opsiE;
        this.jawaban = jawaban;
    }

    @Override
    public String getOpsiA() {
        return opsiA;
    }

    @Override
    public String getOpsiB() {
        return opsiB;
    }

    @Override
    public String getOpsiC() {
        return opsiC;
    }

    @Override
    public String getOpsiD() {
        return opsiD;
    }

    @Override
    public String getOpsiE() {
        return opsiE;
    }

    @Override
    public String getJawaban() {
        return jawaban;
    }
}
