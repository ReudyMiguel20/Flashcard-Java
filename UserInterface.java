package flashcards;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface extends FlashcardManager {
    private FlashcardManager flashcardManager;
    private Scanner scanner;

    public UserInterface(FlashcardManager flashcardManager, Scanner scanner) {
        this.flashcardManager = flashcardManager;
        this.scanner = scanner;
    }

    public void start() throws IOException {
        while (true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "add" -> {
                    this.flashcardManager.userAddFlashCard(scanner);
                }
                case "remove" -> {
                    this.flashcardManager.removeFlashcard(scanner);
                }
                case "import" -> {

                }
                case "export" -> {
                    this.flashcardManager.exportFile(scanner);
                }
            }
        }
    }
}
