package pojo;

/**
 * Created by Home on 18.07.2019.
 */
public class ATMCard {
    private String cardNumber;
    private int pin;
    private long balance;

    public ATMCard(String cardNumber, int pin, long balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
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
        return "pojo.ATMCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", pin=" + pin +
                ", balance=" + balance +
                '}';
    }
}
