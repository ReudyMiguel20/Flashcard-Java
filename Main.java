package flashcards;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        FlashcardManager flashcardManager = new FlashcardManager();
        UserInterface UI = new UserInterface(flashcardManager, scanner);

        UI.start();
    }

}
