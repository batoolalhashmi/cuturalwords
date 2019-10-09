package com.barmej.culturalwords;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.barmej.culturalwords.constants.ANS;

public class AnswerActivity extends AppCompatActivity {
    private TextView mTextViewAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        mTextViewAnswer = findViewById(R.id.text_view_answer);
        String answer = getIntent().getStringExtra(ANS);
        if (answer != null) {
            mTextViewAnswer.setText(answer);
        } else {
            Toast.makeText(this, "No answer", Toast.LENGTH_SHORT).show();
        }
    }

    public void onReturnClicked(View view) {
        finish();
    }
}