import pojo.ATMCard;
import utils.UserMessage;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

class ATMControllerImpl implements Observer<ATMCard>, ATMcontroller {

    void start() {
        System.out.println(UserMessage.HELLO_USER);
        FileActions fileActions = new FileReader(this);
        fileActions.openFile();
    }


    @Override
    public void logIn(List<ATMCard> data) {
        if (!data.isEmpty()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            inputCreditCardNumber(bufferedReader, data);
        }
    }


    @Override
    public void inputCreditCardNumber(BufferedReader bufferedReader, List<ATMCard> atmCards) {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println(UserMessage.INPUT_CARD_NUMBER);
                String cardNumber = bufferedReader.readLine();
                if (Utils.checkCreditCardNumber(cardNumber)) {
                    for (ATMCard card : atmCards) {
                        if (card.getCardNumber().equals(cardNumber)) {
                            inputPin(bufferedReader, card);
                            flag = false;
                        }
                    }
                } else {
                    System.out.println(UserMessage.WRONG_CARD_NUMBER);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void inputPin(BufferedReader bufferedReader, ATMCard atmCard) {
        System.out.println(UserMessage.ENTER_PIN);
        try {
            String pin = bufferedReader.readLine();

            if (Utils.checkPin(pin)) {
                int pinCode = Integer.valueOf(pin);
                if (pinCode == atmCard.getPin()) {
                    System.out.println("Menu");
                } else {
                    System.out.println(UserMessage.WONG_PIN);
                    inputPin(bufferedReader, atmCard);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
