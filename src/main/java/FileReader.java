import pojo.ATMCard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FileReader extends AbstractFileReader<File> implements Subject<ATMCard> {

    private final List<ATMCard> cardList = new ArrayList<>();
    private final List<Observer> observers;

    public FileReader(ATMControllerImpl atmControllerImpl) {
        this.observers = new ArrayList<>();
        register(atmControllerImpl);
    }

    public File readFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("data.txt");
        return new File(resource.getFile());
    }

    public void readDataFromFile(final File file) throws FileNotFoundException {
        String line;
        try (java.io.FileReader fileReader = new java.io.FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineWithData = line.split(" ");
                String creditCard = lineWithData[0];
                int pin = Integer.valueOf(lineWithData[1]);
                long balance = Long.valueOf(lineWithData[2]);
                ATMCard atmCard = new ATMCard(creditCard, pin, balance);
                cardList.add(atmCard);
            }
            notifyAllObServers(cardList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDataToFile(final ATMCard atmCard) {

    }

    public void errorOpenFile(Throwable throwable) {
        System.out.println("Error " + throwable.getLocalizedMessage());
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAllObServers(List<ATMCard> listData) {
        for (Observer observer : observers) {
            observer.logIn(listData);
        }
    }
}
