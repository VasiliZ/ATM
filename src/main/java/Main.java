import atm.ATMControllerImpl;
import utils.UserMessage;


public class Main {
    public static void main(String[] args) {
        try {
            if (args != null) {
                String strings = args[0];
                ATMControllerImpl atmControllerImpl = new ATMControllerImpl(strings);
                atmControllerImpl.start();
            } else {
                System.out.println(UserMessage.SET_ARGS);
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println(UserMessage.SET_ARGS);
        }
    }
}
