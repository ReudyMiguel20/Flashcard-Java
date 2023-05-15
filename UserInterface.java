package flashcards;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface extends FlashcardManager {
    private FlashcardManager flashcardManager;
    private Scanner scanner;

    public UserInterface(FlashcardManager flashcardManager, Scanner scanner) {
        this.flashcardManager = flashcardManager;
        this.scanner = scanner;
    }

    public void start() throws IOException {
        boolean isRunning = true;
        while (isRunning) {

            switch (this.flashcardManager.askUser(scanner)) {
                case "add" -> {
                    this.flashcardManager.userAddFlashCard(scanner);
                }
                case "remove" -> {
                    this.flashcardManager.removeFlashcard(scanner);
                }
                case "import" -> {
                    this.flashcardManager.importFile(scanner);
                }
                case "export" -> {
                    this.flashcardManager.exportFile(scanner);
                }
                case "ask" -> {
                    this.flashcardManager.askCardsByUser(scanner);
                }
                case "exit" -> {
                    System.out.println("Bye bye!");
                    isRunning = false;
                }
                case "log" -> {
                    this.flashcardManager.saveLog(scanner);
                }
                case "hardest card" -> {
                    this.flashcardManager.hardestCard();
                }
                case "reset stats" -> {
                    this.flashcardManager.resetStats();
                }
            }
        }
    }

}
