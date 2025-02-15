import java.util.ArrayList;

public class QuizManager {
    private ArrayList<Question> questionList;
    private int currentQuestionIndex = 0;

    public QuizManager(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    // Get current question
    public Question getCurrentQuestion() {
        return questionList.get(currentQuestionIndex);
    }

    // Store selected answer for current question
    public void storeAnswer(String selectedOption) {
        questionList.get(currentQuestionIndex).setSelectedOption(selectedOption);
    }

    // Move to next question
    public boolean nextQuestion() {
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            return true;
        }
        return false;
    }

    // Move to previous question (optional)
    public boolean previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            return true;
        }
        return false;
    }

    // Get all stored answers
    public ArrayList<Question> getAllAnswers() {
        return questionList;
    }
}
