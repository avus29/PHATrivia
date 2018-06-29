package com.example.user.phatrivia;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    int question = 1;

    boolean finalAnswer = false;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showQuestion();
        showOptions();

    }

    public void doQuiz(View view) {

        takeAnswer();
        updateScore();

        if (question <= 10) {
            showQuestion();
            showOptions();
        }
    }


    /**
     * Display the question in the R.id.question_display TextView and set the view to visible
     *
     * @ parameter - S/N of question
     */
    public void showQuestion() {
        //Track the S/N of question
        TextView questionTracker = (TextView) findViewById(R.id.question_digit);
        questionTracker.setText(question + " of 10");
        //Use a progress bar for more appeal.
        ProgressBar pBar = (ProgressBar) findViewById(R.id.progress_bar);
        pBar.setProgress(question);

        TextView displayQuestion = (TextView) findViewById(R.id.question_display);
        switch (question) {
            case 1:
                displayQuestion.setText(R.string.question_1);
                break;
            case 2:
                displayQuestion.setText(R.string.question_2);
                break;
            case 3:
                displayQuestion.setText(R.string.question_3);
                break;
            case 4:
                displayQuestion.setText(R.string.question_4);
                break;
            case 5:
                displayQuestion.setText(R.string.question_5);
                break;
            case 6:
                displayQuestion.setText(R.string.question_6);
                break;
            case 7:
                displayQuestion.setText(R.string.question_7);
                break;
            case 8:
                displayQuestion.setText(R.string.question_8);
                break;
            case 9:
                displayQuestion.setText(R.string.question_9);
                break;
            case 10:
                displayQuestion.setText(R.string.question_10);
                break;
        }

        displayQuestion.setVisibility(View.VISIBLE);
    }

    /*Display a set of checkLists for the corresponding question
    @param - string option 1, String option 2, String option 3, String Option 4
     */
    public void showCheckList(String[] options) {

        LinearLayout checkList = (LinearLayout) findViewById(R.id.checkboxes);
        //Reset each checkbox to the unchecked state and set text on each.
        CheckBox option1 = (CheckBox) findViewById(R.id.cb_1);
        option1.setChecked(false);
        option1.setText(options[0]);
        CheckBox option2 = (CheckBox) findViewById(R.id.cb_2);
        option2.setChecked(false);
        option2.setText(options[1]);
        CheckBox option3 = (CheckBox) findViewById(R.id.cb_3);
        option3.setChecked(false);
        option3.setText(options[2]);
        CheckBox option4 = (CheckBox) findViewById(R.id.cb_4);
        option4.setChecked(false);
        option4.setText(options[3]);
        //make the checklists visible
        checkList.setVisibility(View.VISIBLE);
    }

    /*Display a set of radio buttons for the corresponding question
    @param - string option 1, String option 2, String option 3, String Option 4
     */
    public void showRadioGroup(String[] options) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        //Reset each radio button to the unchecked state and set text on each.
        RadioButton rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_1.setText(options[0]);
        rb_1.setChecked(false);
        RadioButton rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_2.setText(options[1]);
        rb_1.setChecked(false);
        RadioButton rb_3 = (RadioButton) findViewById(R.id.rb_3);
        rb_3.setText(options[2]);
        rb_1.setChecked(false);
        RadioButton rb_4 = (RadioButton) findViewById(R.id.rb_4);
        rb_4.setText(options[3]);
        rb_1.setChecked(false);
        //make the radio group visible
        rg.setVisibility(View.VISIBLE);
    }

    /*Set to visible the hidden edit text to allow users type in the answer
     */
    public void showTextBox() {
        EditText textBox = (EditText) findViewById(R.id.text_box);
        textBox.setText("");

        textBox.setVisibility(View.VISIBLE);
    }

    /*Display the option accordingly
     */
    public void showOptions() {

        Resources res = getResources();

        switch (question) {
            case 1:
                showCheckList(res.getStringArray(R.array.options_1));
                break;
            case 2:
                showRadioGroup(res.getStringArray(R.array.options_2));
                break;
            case 3:
                showTextBox();
                break;
            case 4:
                showTextBox();
                break;
            case 5:
                showRadioGroup(res.getStringArray(R.array.options_all));
                break;
            case 6:
                showRadioGroup(res.getStringArray(R.array.options_all));
                break;
            case 7:
                showCheckList(res.getStringArray(R.array.options_7));
                break;
            case 8:
                showCheckList(res.getStringArray(R.array.options_8));
                break;
            case 9:
                showTextBox();
                break;
            case 10:
                showRadioGroup(res.getStringArray(R.array.options_all));
                break;
        }
    }

    /* Take answer from the user and check correctness!S
     */
    public void takeAnswer() {
        //component of cheklist question-type
        LinearLayout checkList = (LinearLayout) findViewById(R.id.checkboxes);
        CheckBox option1 = (CheckBox) findViewById(R.id.cb_1);
        CheckBox option2 = (CheckBox) findViewById(R.id.cb_2);
        CheckBox option3 = (CheckBox) findViewById(R.id.cb_3);
        CheckBox option4 = (CheckBox) findViewById(R.id.cb_4);
        //component of radio group question-type
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        RadioButton rb_1 = (RadioButton) findViewById(R.id.rb_1);
        RadioButton rb_2 = (RadioButton) findViewById(R.id.rb_2);
        RadioButton rb_3 = (RadioButton) findViewById(R.id.rb_3);
        RadioButton rb_4 = (RadioButton) findViewById(R.id.rb_4);
        //Textbox for questions requiring text input
        EditText textBox = (EditText) findViewById(R.id.text_box);
        textBox.setText("");

        if (clicked == false) {
            /*Decide the logic for the correct answers to each of the question*/
            if (question == 3) {
                String answer = textBox.getText().toString();
                if (answer.equalsIgnoreCase(getString(R.string.answer_3))) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "The moiety that is responsible for the " +
                            "pharmacodynamic action of a group of related compounds is known as?" +
                            "\n\nAnswer: Pharmacophore", Toast.LENGTH_LONG).show();
                }

            } else if (question == 4) {
                String answer = textBox.getText().toString();
                if (answer.equalsIgnoreCase(getString(R.string.answer_4))) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "The interaction between two drugs that " +
                            "results  in the effect of one drug being annulled or reduced by the " +
                            "other is known as? \n\nAnswer: Antagonism", Toast.LENGTH_LONG).show();
                }

            } else if (question == 9) {
                String answer = textBox.getText().toString();
                if (answer.equalsIgnoreCase(getString(R.string.answer_9))) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "what is the full meaning of DASH?\n\nAnswer: " +
                            "Dietary Approach  to stop Hypertension", Toast.LENGTH_LONG).show();
                }
            } else if (question == 1) {
                boolean a = option1.isChecked();
                boolean b = option2.isChecked();
                boolean c = option3.isChecked();
                boolean d = option4.isChecked();

                if ((a == true) && (b == true) && (c == true) && (d == false)) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "Which of the following is/are components of " +
                            "pharmacokinetics?\n\nAnswer: Absorption,Distribution,Metabolism", Toast.LENGTH_LONG).show();
                }
            } else if (question == 7) {
                boolean a = option1.isChecked();
                boolean b = option2.isChecked();
                boolean c = option3.isChecked();
                boolean d = option4.isChecked();

                if ((a == true) && (b == true) && (c == false) && (d == true)) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "Which of the following is/are HMA-CoA reductase" +
                            " inhibitors?\n\nAnswer: Simvastatin, Rosuvastatin,Atorvastatin", Toast.LENGTH_LONG).show();
                }
            } else if (question == 8) {
                boolean a = option1.isChecked();
                boolean b = option2.isChecked();
                boolean c = option3.isChecked();
                boolean d = option4.isChecked();

                if ((a == true) && (b == false) && (c == true) && (d == false)) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "Which of the following is/are bacteriostatic?\n\nAnswer: Levofloxacin, Azithromycin", Toast.LENGTH_LONG).show();
                }
            } else if (question == 2) {
                boolean a = rb_1.isChecked();
                boolean b = rb_2.isChecked();
                boolean c = rb_3.isChecked();
                boolean d = rb_4.isChecked();

                if (a == true) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "A molecule that  binds to the receptor site " +
                            "to ellicit a response is called?\n\nAnswer: Agonist", Toast.LENGTH_LONG).show();
                }
            } else if (question == 5) {
                boolean a = rb_1.isChecked();
                boolean b = rb_2.isChecked();
                boolean c = rb_3.isChecked();
                boolean d = rb_4.isChecked();

                if (b == true) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "The half-life of a drug is the time taken for" +
                            " half of the drug to be absorbed into the system?\n\nAnswer: False", Toast.LENGTH_SHORT).show();
                }
            } else if (question == 6) {
                boolean a = rb_1.isChecked();
                boolean b = rb_2.isChecked();
                boolean c = rb_3.isChecked();
                boolean d = rb_4.isChecked();

                if (a == true) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "Other factors being considered, drugs with a high therapeutic index\n" +
                            "    require measurement of plasma concentration and clearance?\n\nAnswer: True", Toast.LENGTH_SHORT).show();
                }
            } else if (question == 10) {
                boolean a = rb_1.isChecked();
                boolean b = rb_2.isChecked();
                boolean c = rb_3.isChecked();
                boolean d = rb_4.isChecked();

                if (b == true) {
                    finalAnswer = true;
                } else {
                    Toast.makeText(this, "Phase I metabolism of drugs are mostly conjugation" +
                            " reactions?\n\nAnswer: False", Toast.LENGTH_SHORT).show();
                }
            }
        }
        clicked = false;
    }

    /*update scores and the question to be displaced next
     */
    public void updateScore() {

        if (finalAnswer == true) {
            score++;
            Toast.makeText(this, "Good job! Your understaning of Pharmacology is " +
                    "dope...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "oops! That was incorrect...", Toast.LENGTH_SHORT).show();
        }

        TextView scoreCounter = findViewById(R.id.score_digit);
        scoreCounter.setText(score + "/" + question);

        question++;
        finalAnswer = false;
        resetOptions();


        if (question == 11) {
            Toast.makeText(this, "Thank you for playing Pharmacology Trivia, We " +
                    "encourage you to continue on this path to excellence.", Toast.LENGTH_LONG).show();
            Button submit = (Button) findViewById(R.id.submit_btn);
            submit.setVisibility(View.INVISIBLE);
            TextView displayQuestion = (TextView) findViewById(R.id.question_display);
            displayQuestion.setTextColor(getResources().getColor(R.color.colorAccent));
            displayQuestion.setText("End of Pharmacology Trivia!");
            displayQuestion.setVisibility(View.VISIBLE);
        }
    }

    /*Hide the options and the answer text box
     */
    public void resetOptions() {
        EditText textBox = (EditText) findViewById(R.id.text_box);
        textBox.setVisibility(View.INVISIBLE);

        LinearLayout checkList = (LinearLayout) findViewById(R.id.checkboxes);
        checkList.setVisibility(View.INVISIBLE);

        RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        rg.setVisibility(View.INVISIBLE);

        TextView displayQuestion = (TextView) findViewById(R.id.question_display);
        displayQuestion.setVisibility(View.INVISIBLE);
    }
}
