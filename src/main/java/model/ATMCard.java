package model;


public class ATMCard {
    private int id;
    private String cardNumber;
    private int pin;
    private long balance;
    private boolean isBloking;
    private long blokingUntil;

    public ATMCard(int id, String cardNumber, int pin, long balance, boolean isBloking, long blokingUntil) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.isBloking = isBloking;
        this.blokingUntil = blokingUntil;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return id
                + " " + cardNumber
                + " " + pin
                + " " + balance
                + " " + isBloking
                + " " + blokingUntil;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }


    public void setStatus(boolean blocking) {
        isBloking = blocking;
    }

    public long getBlokingUntil() {
        return blokingUntil;
    }

    public void setBlokingUntil(long blokingUntil) {
        this.blokingUntil = blokingUntil;
    }
}
