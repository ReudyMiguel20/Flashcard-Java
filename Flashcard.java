package flashcards;

public class Flashcard {
    private String term;
    private String answer;

    public Flashcard(String term, String answer) {
        this.term = term;
        this.answer = answer;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTerm() {
        return this.term;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String toString() {
        return this.term + ":" + this.answer;
    }
}
