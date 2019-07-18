import pojo.ATMCard;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by Home on 18.07.2019.
 */
public interface ATMcontroller {

    void inputCreditCardNumber(BufferedReader bufferedReader, List<ATMCard> cardList);

    void inputPin(BufferedReader bufferedReader, ATMCard atmCard);
}
