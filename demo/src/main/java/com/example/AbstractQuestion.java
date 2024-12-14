package com.example;

public abstract class AbstractQuestion {
    private int id;
    private String bunyiSoal;

    public AbstractQuestion(int id, String bunyiSoal) {
        this.id = id;
        this.bunyiSoal = bunyiSoal;
    }

    public int getId() {
        return id;
    }

    public String getBunyiSoal() {
        return bunyiSoal;
    }

    // Metode abstract yang harus diimplementasikan oleh subclass
    public abstract String getOpsiA();
    public abstract String getOpsiB();
    public abstract String getOpsiC();
    public abstract String getOpsiD();
    public abstract String getOpsiE();
    public abstract String getJawaban();
}
