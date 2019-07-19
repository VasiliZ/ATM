package atm;

import model.ATMCard;

import java.io.BufferedReader;
import java.util.List;

public interface ATMcontroller {

    void inputCreditCardNumber(BufferedReader bufferedReader, List<ATMCard> cardList);

    void inputPin(BufferedReader bufferedReader, ATMCard atmCard);

    void exit();

    void goToMenu(ATMCard atmCard);

    void blockCard(ATMCard atmCard);

}
