import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private QuizManager quizManager;
    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button nextButton, submitButton;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.nextButton);
        submitButton = findViewById(R.id.submitButton);

        // Create Sample Questions (For Now)
        questions = new ArrayList<>();
        questions.add(new Question(1, "What is Java?", "Programming Language", "Food", "Animal", "Game"));
        questions.add(new Question(2, "Who developed Android?", "Google", "Apple", "Microsoft", "IBM"));
        quizManager = new QuizManager(questions);

        // Load first question
        loadQuestion();

        // Handle Next Button Click
        nextButton.setOnClickListener(view -> {
            storeSelectedAnswer();
            if (quizManager.nextQuestion()) {
                loadQuestion();
            } else {
                Toast.makeText(this, "End of Quiz!", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle Submit Button Click
        submitButton.setOnClickListener(view -> {
            storeSelectedAnswer();
            submitAnswers();
        });
    }

    private void loadQuestion() {
        Question currentQuestion = quizManager.getCurrentQuestion();
        questionText.setText(currentQuestion.getQuestionText());

        ((RadioButton) optionsGroup.getChildAt(0)).setText(currentQuestion.getOptionA());
        ((RadioButton) optionsGroup.getChildAt(1)).setText(currentQuestion.getOptionB());
        ((RadioButton) optionsGroup.getChildAt(2)).setText(currentQuestion.getOptionC());
        ((RadioButton) optionsGroup.getChildAt(3)).setText(currentQuestion.getOptionD());

        optionsGroup.clearCheck(); // Reset selection
    }

    private void storeSelectedAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedButton = findViewById(selectedId);
            quizManager.storeAnswer(selectedButton.getText().toString());
        }
    }

    private void submitAnswers() {
        ArrayList<Question> answers = quizManager.getAllAnswers();

        // Convert answers to JSON (simulated example)
        String jsonPayload = "[";
        for (Question q : answers) {
            jsonPayload += "{ \"questionId\": " + q.getQuestionId() + ", \"selectedOption\": \"" + q.getSelectedOption() + "\" },";
        }
        jsonPayload = jsonPayload.substring(0, jsonPayload.length() - 1) + "]";

        // Send JSON to backend (Later we'll do this using Retrofit API)
        Toast.makeText(this, "Sending Answers: " + jsonPayload, Toast.LENGTH_LONG).show();
    }
}
