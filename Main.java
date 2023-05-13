package flashcards;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FlashcardManager flashcardManager = new FlashcardManager();
        UserInterface UI = new UserInterface(flashcardManager, scanner);

        UI.start();
    }
}
