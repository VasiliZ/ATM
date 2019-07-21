package utils;

public interface UserMessage {
    String HELLO_USER = "Welcome";
    String MENU = "Menu";
    String INPUT_CARD_NUMBER = "Please input card number: XXXX-XXXX-XXXX-XXXX";
    String WRONG_CARD_NUMBER = "Wrong card number. Please enter again";
    String WONG_PIN = "Wrong pin. Please enter again";
    String ENTER_PIN = "Input pin";
    String WRONG_MENU_POSITION = "Wrong menu position";
    String SELECT_MENU_ITEM = "Please select menu item";
    String CHECK_BALANCE = "1. Check balance";
    String BALANCE = "Your balance = ";
    String BACK_TO_MAIN_MENU = "Press '<' for back to main menu ";
    String WRONG_BACK_SYMBOL = "Wrong back symbol. Please input '<' for back to main menu";
    String INPUT_SUM = "Enter the amount to issue ";
    String TAKE_MONEY = "Please take your money ";
    String SORRY_NO_MONEY = "Sorry, no so much cash. Please try again. \n";
    String REFILL = "Please enter sum for refill";
    String GET_MONEY = "2. Cash advance";
    String REFILL_MENU_ITEM = "3. Refill your bill";
    String EXIT = "4. Exit";
    String BLOCKED = "Your card is blocked";
    String SET_ARGS = "Please set command line args with path to data file";
    String FILE_NOT_FOUND = "File not found";
    String ERROR_WRITING = "Error write data to file";
}
