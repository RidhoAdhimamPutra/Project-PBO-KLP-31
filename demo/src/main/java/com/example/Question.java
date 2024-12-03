package com.example;

public class Question {
    private int id;
    private String bunyiSoal;
    private String opsiA;
    private String opsiB;
    private String opsiC;
    private String opsiD;
    private String opsiE;
    private String jawaban;

    public Question(int id, String bunyiSoal, String opsiA, String opsiB, String opsiC, String opsiD, String opsiE,
            String jawaban) {
        this.id = id;
        this.bunyiSoal = bunyiSoal;
        this.opsiA = opsiA;
        this.opsiB = opsiB;
        this.opsiC = opsiC;
        this.opsiD = opsiD;
        this.opsiE = opsiE;
        this.jawaban = jawaban;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public String getBunyiSoal() {
        return bunyiSoal;
    }

    public String getOpsiA() {
        return opsiA;
    }

    public String getOpsiB() {
        return opsiB;
    }

    public String getOpsiC() {
        return opsiC;
    }

    public String getOpsiD() {
        return opsiD;
    }

    public String getOpsiE() {
        return opsiE;
    }

    public String getJawaban() {
        return jawaban;
    }
}
