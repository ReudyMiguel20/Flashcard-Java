package flashcards;

public class Flashcard {
    private String term;
    private String answer;

    private int numberMistakes;

    public Flashcard(String term, String answer) {
        this.term = term;
        this.answer = answer;
        this.numberMistakes = 0;
    }

    public Flashcard(String term, String answer, int numberMistakes) {
        this.term = term;
        this.answer = answer;
        this.numberMistakes = numberMistakes;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setNumberMistakes(int numberMistakes) {
        this.numberMistakes = numberMistakes;
    }

    public String getTerm() {
        return this.term;
    }

    public String getAnswer() {
        return this.answer;
    }

    public int getNumberMistakes() {
        return this.numberMistakes;
    }

    public String toString() {
        return this.term + ":" + this.answer + ":" + this.numberMistakes;
    }
}
