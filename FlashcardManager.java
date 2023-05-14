package flashcards;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FlashcardManager {
    ArrayList<Flashcard> flashcardDeck;

    public FlashcardManager() {
        this.flashcardDeck = new ArrayList<>();
    }

    public void addFlashcard(String term, String answer) {
        this.flashcardDeck.add(new Flashcard(term, answer));
    }

    public void removeFlashcard(int index) {
        this.flashcardDeck.remove(index);
    }

    public void removeFlashcard(Scanner scanner) {
        System.out.println("Which card?");
        String term = scanner.nextLine();

        for (int i = 0; i <= this.flashcardDeck.size(); i++) {
            if (i == this.flashcardDeck.size()) {
                System.out.println("Can't remove \"" + term + "\": there is no such card.");
                break;
            } else if (this.flashcardDeck.get(i).getTerm().equals(term)) {
                removeFlashcard(i);
                System.out.println("The card has been removed.");
                break;
            }
        }
    }

    public Flashcard getFlashcard(int index) {
        return this.flashcardDeck.get(index);
    }

    public int getSize() {
        return this.flashcardDeck.size();
    }

    public void userAddFlashcards(Scanner scanner) {
        System.out.println("Input the number of cards");
        int amountOfFlashcard = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= amountOfFlashcard; i++) {
            System.out.println("Card #" + i + ":");
            String term = scanner.nextLine();

            //Verifying if the term already exist on any flashcard
            while (isTermDuplicate(term)) {
                term = scanner.nextLine();
            }

            System.out.println("The definition for card #" + i + ":");
            String answer = scanner.nextLine();

            //Verifying if the definition already exist on any flashcard
            while (isAnswerDuplicate(answer)) {
                answer = scanner.nextLine();
            }

            addFlashcard(term, answer);
        }
    }

    public void userAddFlashCard(Scanner scanner) {
        System.out.println("The card:");
        String term = scanner.nextLine();
        if (isTermDuplicate(term)) {
            return;
        }
        System.out.println("The definition of the card:");
        String answer = scanner.nextLine();
        if (isAnswerDuplicate(answer)) {
            return;
        }
        this.flashcardDeck.add(new Flashcard(term, answer));
        System.out.println("The pair (\"" + term + "\":\"" + answer + "\") has been added.");
    }

    public void printTermsAndAnswers(Scanner scanner) {
        for (int i = 0; i < this.flashcardDeck.size(); i++) {
            System.out.println("Print the definition of " + "\"" + this.flashcardDeck.get(i).getTerm() + "\":");
            String answer = scanner.nextLine();

            if (this.flashcardDeck.get(i).getAnswer().equals(answer)) {
                System.out.println("Correct!");
            } else {
                for (int j = 0; j <= this.flashcardDeck.size(); j++) {
                    if (j == this.flashcardDeck.size()) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"");
                    } else if (this.flashcardDeck.get(j).getAnswer().equals(answer)) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"," + " but your definition is correct for \"" + this.flashcardDeck.get(j).getTerm() + "\".");
                        break;
                    }
                }

            }
        }
    }

    public void askCardsByUser(Scanner scanner) {
        System.out.println("How many times to ask?");
        int cardsQuantity = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cardsQuantity; i++) {
            System.out.println("Print the definition of " + "\"" + this.flashcardDeck.get(i).getTerm() + "\":");
            String answer = scanner.nextLine();

            if (this.flashcardDeck.get(i).getAnswer().equals(answer)) {
                System.out.println("Correct!");
            } else {
                for (int j = 0; j <= cardsQuantity; j++) {
                    if (j == cardsQuantity) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"");
                    } else if (this.flashcardDeck.get(j).getAnswer().equals(answer)) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"," + " but your definition is correct for \"" + this.flashcardDeck.get(j).getTerm() + "\".");
                        break;
                    }
                }

            }
        }
    }

    public boolean isTermDuplicate(String termToCheck) {
        for (Flashcard x : this.flashcardDeck) {
            if (x.getTerm().equals(termToCheck)) {
                System.out.println("The card " + "\"" + termToCheck + "\" already exists.");
                return true;
            }
        }
        return false;
    }

    public boolean isAnswerDuplicate(String answerToCheck) {
        for (Flashcard x : this.flashcardDeck) {
            if (x.getAnswer().equals(answerToCheck)) {
                System.out.println("The definition " + "\"" + answerToCheck + "\" already exists.");
                return true;
            }
        }
        return false;
    }

    public void exportFile(Scanner scanner) throws IOException {
        System.out.println("File name:");
        String fileName = scanner.nextLine();
        FileWriter fw = new FileWriter(fileName);
        fw.write(cardsInCSVFormat());
        fw.close();
        System.out.println(this.flashcardDeck.size() + " cards have been saved.");
    }

    public String cardsInCSVFormat() {
        StringBuilder sb = new StringBuilder();
        for (Flashcard x : this.flashcardDeck) {
            sb.append(x).append("\n");
        }
        return sb.toString();
    }

    public void importFile(Scanner scanner) {
        System.out.println("File name:");
        String fileName = scanner.nextLine();
        String line = "";
        BufferedReader reader = null;
        int counter = 0;
        boolean isListEmpty = true;

        //Reading lines from the file until there's none, also counting the cards added to let the user know how many
        //of them were added.
        try {
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                String[] splitter = line.split(":");
                if (this.flashcardDeck.size() >= 1) {
                    isListEmpty = false;
                }

                if (isListEmpty) {
                    this.flashcardDeck.add(new Flashcard(splitter[0], splitter[1]));
                    counter++;
                } else if (!isListEmpty){
                    for (Flashcard x : this.flashcardDeck) {
                        if (x.getTerm().equals(splitter[0])) {
                            x.setAnswer(splitter[1]);
                            counter++;
                            break;
                        } else {
                            this.flashcardDeck.add(new Flashcard(splitter[0], splitter[1]));
                            counter++;
                            break;
                        }
                    }
                }
            }
            System.out.println(counter + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
