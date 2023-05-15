package flashcards;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;

public class FlashcardManager {
    ArrayList<Flashcard> flashcardDeck;
    ArrayList<String> log;

    public FlashcardManager() {
        this.flashcardDeck = new ArrayList<>();
        this.log = new ArrayList<>();
    }

    public String askUser(Scanner scanner) {
        System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        this.log.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        String userInput = scanner.nextLine();
        this.log.add(userInput);
        return userInput;
    }

    public void addFlashcard(String term, String answer) {
        this.flashcardDeck.add(new Flashcard(term, answer));
    }

    public void removeFlashcard(int index) {
        this.flashcardDeck.remove(index);
    }

    public void removeFlashcard(Scanner scanner) {
        System.out.println("Which card?");
        this.log.add("Which card?");
        String term = scanner.nextLine();
        this.log.add(term);

        for (int i = 0; i <= this.flashcardDeck.size(); i++) {
            if (i == this.flashcardDeck.size()) {
                System.out.println("Can't remove \"" + term + "\": there is no such card.");
                this.log.add("Can't remove \"" + term + "\": there is no such card.");
                break;
            } else if (this.flashcardDeck.get(i).getTerm().equals(term)) {
                removeFlashcard(i);
                System.out.println("The card has been removed.");
                this.log.add("The card has been removed.");
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
        this.log.add("Input the number of cards");
        int amountOfFlashcard = scanner.nextInt();
        this.log.add(String.valueOf(amountOfFlashcard));
        scanner.nextLine();

        for (int i = 1; i <= amountOfFlashcard; i++) {
            System.out.println("Card #" + i + ":");
            this.log.add("Card #" + i + ":");
            String term = scanner.nextLine();


            //Verifying if the term already exist on any flashcard
            while (isTermDuplicate(term)) {
                term = scanner.nextLine();
            }
            this.log.add(term);

            System.out.println("The definition for card #" + i + ":");
            String answer = scanner.nextLine();

            //Verifying if the definition already exist on any flashcard
            while (isAnswerDuplicate(answer)) {
                answer = scanner.nextLine();
            }
            this.log.add(answer);

            addFlashcard(term, answer);
        }
    }

    public void userAddFlashCard(Scanner scanner) {
        System.out.println("The card:");
        this.log.add("The card:");
        String term = scanner.nextLine();

        if (isTermDuplicate(term)) {
            return;
        }
        this.log.add(term);

        System.out.println("The definition of the card:");
        this.log.add("The definition of the card:");
        String answer = scanner.nextLine();

        if (isAnswerDuplicate(answer)) {
            return;
        }
        this.log.add(answer);

        this.flashcardDeck.add(new Flashcard(term, answer));
        System.out.println("The pair (\"" + term + "\":\"" + answer + "\") has been added.");
        this.log.add("The pair (\"" + term + "\":\"" + answer + "\") has been added.");
    }

    public void printTermsAndAnswers(Scanner scanner) {
        for (int i = 0; i < this.flashcardDeck.size(); i++) {
            System.out.println("Print the definition of " + "\"" + this.flashcardDeck.get(i).getTerm() + "\":");
            this.log.add("Print the definition of " + "\"" + this.flashcardDeck.get(i).getTerm() + "\":");
            String answer = scanner.nextLine();
            this.log.add(answer);

            if (this.flashcardDeck.get(i).getAnswer().equals(answer)) {
                System.out.println("Correct!");
                this.log.add("Correct!");
            } else {
                for (int j = 0; j <= this.flashcardDeck.size(); j++) {
                    if (j == this.flashcardDeck.size()) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"");
                        this.log.add("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"");
                        int mistakeNumber = this.flashcardDeck.get(i).getNumberMistakes();
                        this.flashcardDeck.get(i).setNumberMistakes(mistakeNumber + 1);
                    } else if (this.flashcardDeck.get(j).getAnswer().equals(answer)) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"," + " but your definition is correct for \"" + this.flashcardDeck.get(j).getTerm() + "\".");
                        this.log.add("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"," + " but your definition is correct for \"" + this.flashcardDeck.get(j).getTerm() + "\".");
                        int mistakeNumber = this.flashcardDeck.get(i).getNumberMistakes();
                        this.flashcardDeck.get(i).setNumberMistakes(mistakeNumber + 1);
                        break;
                    }
                }

            }
        }
    }

    public void askCardsByUser(Scanner scanner) {
        System.out.println("How many times to ask?");
        this.log.add("How many times to ask?");
        int cardsQuantity = scanner.nextInt();
        this.log.add(String.valueOf(cardsQuantity));
        scanner.nextLine();

        for (int i = 0; i < cardsQuantity; i++) {
            System.out.println("Print the definition of " + "\"" + this.flashcardDeck.get(i).getTerm() + "\":");
            this.log.add("Print the definition of " + "\"" + this.flashcardDeck.get(i).getTerm() + "\":");
            String answer = scanner.nextLine();
            this.log.add(answer);

            if (this.flashcardDeck.get(i).getAnswer().equals(answer)) {
                System.out.println("Correct!");
                this.log.add("Correct!");
            } else {
                for (int j = 0; j <= cardsQuantity; j++) {
                    if (j == cardsQuantity) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"");
                        this.log.add("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"");
                        int mistakeNumber = this.flashcardDeck.get(i).getNumberMistakes();
                        this.flashcardDeck.get(i).setNumberMistakes(mistakeNumber + 1);
                    } else if (this.flashcardDeck.get(j).getAnswer().equals(answer)) {
                        System.out.println("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"," + " but your definition is correct for \"" + this.flashcardDeck.get(j).getTerm() + "\".");
                        this.log.add("Wrong. The right answer is \"" + this.flashcardDeck.get(i).getAnswer() + "\"," + " but your definition is correct for \"" + this.flashcardDeck.get(j).getTerm() + "\".");
                        int mistakeNumber = this.flashcardDeck.get(i).getNumberMistakes();
                        this.flashcardDeck.get(i).setNumberMistakes(mistakeNumber + 1);
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
                this.log.add("The card " + "\"" + termToCheck + "\" already exists.");
                return true;
            }
        }
        return false;
    }

    public boolean isAnswerDuplicate(String answerToCheck) {
        for (Flashcard x : this.flashcardDeck) {
            if (x.getAnswer().equals(answerToCheck)) {
                System.out.println("The definition " + "\"" + answerToCheck + "\" already exists.");
                this.log.add("The definition " + "\"" + answerToCheck + "\" already exists.");
                return true;
            }
        }
        return false;
    }

    public void hardestCard() {
        //Initializing new string builder and counter to append the names of the cards with mistakes and count the errors
        //Also counting the cards, this will affect the way it prints the message.
        StringBuilder sb = new StringBuilder();
        String mostMistakesCard = "";
        int testNumberOfMistakes = 0;
        int numberOfMistakes = 0;
        int numberOfCards = 0;

        //What I want to do is to put first the biggest term with the most number of mistakes on the sb
        for (int x = 0; x < this.flashcardDeck.size(); x++) {
            if (this.flashcardDeck.get(x).getNumberMistakes() >= 1) {
                testNumberOfMistakes = this.flashcardDeck.get(x).getNumberMistakes();
                mostMistakesCard = this.flashcardDeck.get(x).getTerm();
            }
        }

        sb.append("\"").append(mostMistakesCard).append("\"");

        //This is for grabbing the names of the cards with 1 or more mistakes, then summing the tries and printing them out
        for (int i = 0; i < this.flashcardDeck.size(); i++) {
            if (this.flashcardDeck.get(i).getNumberMistakes() >= 1 && i == this.flashcardDeck.size() - 1) {
                if (numberOfMistakes < this.flashcardDeck.get(i).getNumberMistakes()) {
                    numberOfMistakes = this.flashcardDeck.get(i).getNumberMistakes();
                }
//                numberOfMistakes += this.flashcardDeck.get(i).getNumberMistakes();
//                sb.append("\"").append(this.flashcardDeck.get(i).getTerm()).append("\"").append(".");
                sb.append(".");
                numberOfCards++;
            } else if (this.flashcardDeck.get(i).getNumberMistakes() >= 1) {
                if (sb.toString().contains(this.flashcardDeck.get(i).getTerm())) {
                    continue;
                }

                if (numberOfMistakes < this.flashcardDeck.get(i).getNumberMistakes()) {
                    numberOfMistakes = this.flashcardDeck.get(i).getNumberMistakes();
                }
//                numberOfMistakes += this.flashcardDeck.get(i).getNumberMistakes();
                sb.append(", ").append("\"").append(this.flashcardDeck.get(i).getTerm()).append("\"");
                numberOfCards++;
            }
        }

        //Printing the result depending on the number of cards
        if (numberOfCards > 1) {
            System.out.println("The hardest cards are " + sb.toString() + " You have " + numberOfMistakes + " errors answering them.\n");
            this.log.add("The hardest cards are " + sb.toString() + " You have " + numberOfMistakes + " errors answering them.");
        } else if (numberOfCards == 1) {
            if (numberOfMistakes == 1) {
                System.out.println("The hardest card is " + sb.toString() + " You have " + numberOfMistakes + " error answering it.\n");
                this.log.add("The hardest card is " + sb.toString() + " You have " + numberOfMistakes + " error answering it.");
            } else {
                System.out.println("The hardest card is " + sb.toString() + " You have " + numberOfMistakes + " errors answering it.\n");
                this.log.add("The hardest card is " + sb.toString() + " You have " + numberOfMistakes + " errors answering it.");
            }
        } else {
            System.out.println("There are no cards with errors.");
            this.log.add("There are no cards with errors.");
        }


    }

    public void exportFile(Scanner scanner) throws IOException {
        System.out.println("File name:");
        this.log.add("File name:");
        String fileName = scanner.nextLine();
        this.log.add(fileName);
        FileWriter fw = new FileWriter(fileName);
        fw.write(cardsInCSVFormat());
        fw.close();
        System.out.println(this.flashcardDeck.size() + " cards have been saved.");
        this.log.add(this.flashcardDeck.size() + " cards have been saved.");
    }

    public String cardsInCSVFormat() {
        StringBuilder sb = new StringBuilder();
        for (Flashcard x : this.flashcardDeck) {
            sb.append(x).append("\n");
        }
        this.log.add(sb.toString());
        return sb.toString();
    }

    public void importFile(Scanner scanner) {
        System.out.println("File name:");
        this.log.add("File name:");
        String fileName = scanner.nextLine();
        this.log.add(fileName);
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
                    this.flashcardDeck.add(new Flashcard(splitter[0], splitter[1], Integer.parseInt(splitter[2])));
                    counter++;
                } else if (!isListEmpty) {
                    for (Flashcard x : this.flashcardDeck) {
                        if (x.getTerm().equals(splitter[0])) {
                            x.setAnswer(splitter[1]);
                            x.setNumberMistakes(Integer.parseInt(splitter[2]));
                            counter++;
                            break;
                        } else {
                            this.flashcardDeck.add(new Flashcard(splitter[0], splitter[1], Integer.parseInt(splitter[2])));
                            counter++;
                            break;
                        }
                    }
                }
            }
            System.out.println(counter + " cards have been loaded.");
            this.log.add(counter + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            this.log.add("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLog(Scanner scanner) throws IOException {
        StringBuilder sb = new StringBuilder();

        System.out.println("File name:");
        this.log.add("File name:");
        String fileName = scanner.nextLine();
        this.log.add(fileName);
        FileWriter fw = new FileWriter(fileName);
        this.log.add("The log has been saved.");

        for (String x : this.log) {
            sb.append(x).append("\n");
        }

        fw.write(sb.toString());
        fw.close();
        System.out.println("The log has been saved.");

    }

    public void resetStats() {
        for (Flashcard x : this.flashcardDeck) {
            x.setNumberMistakes(0);
        }
        System.out.println("Card statistics have been reset.");
    }

}
