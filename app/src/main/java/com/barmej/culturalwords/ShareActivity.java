package com.barmej.culturalwords;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {
    public EditText mEditTextShare;
    ImageView mQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mEditTextShare = findViewById(R.id.edit_text_share_title);
        mQuestion = findViewById(R.id.image_view_question);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            int QuestionImage = bundle.getInt("question_extra");
            mQuestion.setImageResource(QuestionImage);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        String questionTitle = sharedPreferences.getString("share_title","");
        mEditTextShare.setText(questionTitle);
    }
    public void onShareQuestionClicked (View view){
        String questionTitle = mEditTextShare.getText().toString();
        Intent shareIntent =new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,questionTitle+"/n" + mQuestion );
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("share_title", questionTitle);
        editor.apply();


    }
}
