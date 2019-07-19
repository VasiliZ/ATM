package atm;

import data.FileActions;
import data.FileWorker;
import menu.Menu;
import menu.MenuController;
import model.ATMCard;
import utils.UserMessage;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

public class ATMControllerImpl implements Observer<ATMCard>, ATMcontroller {
    private FileActions fileActions;
    private int countEnterWrongPin = 3;

    public void start() {
        System.out.println(UserMessage.HELLO_USER);
        fileActions = new FileWorker(this);
        fileActions.openFile();
    }


    @Override
    public void logIn(final List<ATMCard> data) {
        if (!data.isEmpty()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            inputCreditCardNumber(bufferedReader, data);
        }
    }


    @Override
    public void inputCreditCardNumber(final BufferedReader bufferedReader, final List<ATMCard> atmCards) {
        try {
            boolean flag = true;
            long currentTime = System.currentTimeMillis();

            while (flag) {
                System.out.println(UserMessage.INPUT_CARD_NUMBER);
                String cardNumber = bufferedReader.readLine();
                if (Utils.checkCreditCardNumber(cardNumber)) {
                    for (ATMCard card : atmCards) {
                        System.out.println(card.getBlokingUntil());
                        if (card.getCardNumber().equals(cardNumber)) {
                            if (card.getBlokingUntil() != 0 && card.getBlokingUntil() > currentTime) {
                                System.out.println(UserMessage.BLOCKED);
                                exit();
                            } else {
                                if (card.getBlokingUntil() < currentTime) {
                                    card.setBlokingUntil(0);
                                    card.setStatus(false);
                                    fileActions.writeDataToFile();
                                }
                            }
                            inputPin(bufferedReader, card);
                            flag = false;
                        }
                    }
                } else {
                    System.out.println(UserMessage.WRONG_CARD_NUMBER);
                }
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    @Override
    public void inputPin(final BufferedReader bufferedReader, final ATMCard atmCard) {
        System.out.println(UserMessage.ENTER_PIN);
        try {
            String pin = bufferedReader.readLine();
            if (Utils.checkPin(pin) && countEnterWrongPin != 0) {
                int pinCode = Integer.valueOf(pin);
                if (pinCode == atmCard.getPin() && countEnterWrongPin != 0) {
                    goToMenu(atmCard);
                } else {
                    --countEnterWrongPin;
                    if (countEnterWrongPin == 0) {
                        blockCard(atmCard);
                        fileActions.writeDataToFile();
                        exit();
                    }
                    System.out.println(UserMessage.WONG_PIN);
                    inputPin(bufferedReader, atmCard);
                }
            } else {
                --countEnterWrongPin;
                if (countEnterWrongPin == 0) {
                    blockCard(atmCard);
                    fileActions.writeDataToFile();
                    exit();
                }
                System.out.println(UserMessage.WONG_PIN);
                inputPin(bufferedReader, atmCard);
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getLocalizedMessage());
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void goToMenu(ATMCard atmCard) {
        Menu menu = new MenuController(atmCard, fileActions);
        menu.init();
    }

    @Override
    public void blockCard(final ATMCard atmCard) {
        System.out.println(UserMessage.BLOCKED);
        atmCard.setStatus(true);
        long currentTime = System.currentTimeMillis();
        System.out.println(currentTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        calendar.add(Calendar.DATE, 1);
        long oneDayPlus = calendar.getTimeInMillis();
        atmCard.setBlokingUntil(oneDayPlus);
    }


}
