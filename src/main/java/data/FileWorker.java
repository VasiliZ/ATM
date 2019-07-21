package data;

import atm.ATMControllerImpl;
import atm.Observer;
import atm.Subject;
import model.ATMCard;
import utils.UserMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker extends AbstractFileWorker<File> implements Subject<ATMCard> {

    private final List<ATMCard> cardList = new ArrayList<>();
    private final List<Observer> observers;
    private final String PATH;

    public FileWorker(ATMControllerImpl atmControllerImpl, String path) {
        this.observers = new ArrayList<>();
        register(atmControllerImpl);
        this.PATH = path;
    }


    public File readFile() {
        return new File(PATH);
    }

    public void readDataFromFile(final File file) throws FileNotFoundException {
        String line;
        try (java.io.FileReader fileReader = new java.io.FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineWithData = line.split(" ");
                int id = Integer.parseInt(lineWithData[0]);
                String creditCard = lineWithData[1];
                long balance = Long.valueOf(lineWithData[3]);
                int pin = Integer.valueOf(lineWithData[2]);
                boolean status = Boolean.parseBoolean(lineWithData[4]);
                long blockingTime = Long.parseLong(lineWithData[5]);
                ATMCard atmCard = new ATMCard(id, creditCard, pin, balance, status, blockingTime);
                cardList.add(atmCard);
            }
            notifyAllObServers(cardList);
        } catch (IOException e) {
            System.out.println(UserMessage.FILE_NOT_FOUND);
        }
    }

    public void writeDataToFile() {
        try {
            FileWriter fileWriter = new FileWriter(readFile());
            for (ATMCard atmCard1 : cardList) {
                fileWriter.write(atmCard1.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(UserMessage.ERROR_WRITING);
        }
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
