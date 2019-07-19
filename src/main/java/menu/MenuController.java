package menu;

import data.FileActions;
import model.ATMCard;
import utils.UserMessage;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MenuController implements Menu {
    private ATMCard atmCard;
    private long atmCache;
    private FileActions fileActions;
    private static final int MAX_SUM_FOR_REFILL = 1000000;

    public MenuController(final ATMCard atmCard, final FileActions fileActions) {
        this.atmCard = atmCard;
        atmCache = Integer.MAX_VALUE;
        this.fileActions = fileActions;
    }

    @Override
    public void checkBalance() {
        System.out.println(UserMessage.BALANCE + atmCard.getBalance());
        backToMainMenu();
    }

    @Override
    public void getMoney() {
        System.out.println(UserMessage.INPUT_SUM);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            long enterSum = Long.parseLong(bufferedReader.readLine());
            if (atmCache > enterSum && atmCard.getBalance() >= enterSum) {
                atmCache = atmCache - enterSum;
                long newBalance = atmCard.getBalance() - enterSum;
                atmCard.setBalance(newBalance);
                System.out.println(UserMessage.TAKE_MONEY + enterSum);
                fileActions.writeDataToFile();
                backToMainMenu();
            } else {
                System.out.println(UserMessage.SORRY_NO_MONEY);
                getMoney();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }

    }

    @Override
    public void refill() {
        System.out.println(UserMessage.REFILL);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int enterSum = Integer.parseInt(bufferedReader.readLine());
            if (enterSum <= MAX_SUM_FOR_REFILL) {
                long newBalance = atmCard.getBalance() + enterSum;
                atmCard.setBalance(newBalance);
                fileActions.writeDataToFile();
                backToMainMenu();
            }
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void init() {
        System.out.println(UserMessage.MENU);
        System.out.println(UserMessage.SELECT_MENU_ITEM);
        System.out.println(UserMessage.CHECK_BALANCE);
        System.out.println(UserMessage.GET_MONEY);
        System.out.println(UserMessage.REFILL_MENU_ITEM);
        System.out.println(UserMessage.EXIT);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int position = Integer.parseInt(bufferedReader.readLine());
            switch (position) {
                case 1:
                    this.checkBalance();
                    break;
                case 2:
                    getMoney();
                    break;
                case 3:
                    refill();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println(UserMessage.WRONG_MENU_POSITION + "\n");
                    init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void backToMainMenu() {
        System.out.println(UserMessage.BACK_TO_MAIN_MENU);
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String backSymbol = bufferedReader.readLine();
            if (Utils.checkBackSymbol(backSymbol)) {
                init();
            } else {
                System.out.println(UserMessage.WRONG_BACK_SYMBOL);
                backToMainMenu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
