package com.barmej.culturalwords;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    private ImageView mImageViewQuestion,btn;
    private int[] QUESTIONS = {
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_5,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10,
            R.drawable.icon_11,
            R.drawable.icon_12,
            R.drawable.icon_13,
            R.drawable.icon_14,
            R.drawable.icon_15,

    };
    private String[] ANSWER;
    private String[] ANSWER_DESCRIPTION;
    private String mCurrentAnswer,mCurrentAnswerDescription;
    private int mCurrentQuestion,randomQuestionIndex;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("app pref",MODE_PRIVATE);
        String appLang = sharedPreferences.getString("app_lang","");
        assert appLang != null;
        if(!appLang.equals(""))
            LocaleHelper.setLocale(this,appLang);
        setContentView(R.layout.activity_main);
        mImageViewQuestion = findViewById(R.id.image_view_question);
        ANSWER = getResources().getStringArray(R.array.answers);
        ANSWER_DESCRIPTION = getResources().getStringArray(R.array.answer_description);
        showNewQuestion();
        btn = findViewById(R.id.button_change_language);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(MainActivity.this,btn);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                    @Override
                    public boolean onMenuItemClick(MenuItem item){
                        Toast.makeText(MainActivity.this,"" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        String language = "ar";
                        if (item.getItemId() == R.id.arabic) {
                            language = "ar";
                        }else {
                            language = "en";
                        }
                        saveLanguage(language);
                        LocaleHelper.setLocale(MainActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }
    private void saveLanguage(String lang){
        SharedPreferences sharedPreferences = getSharedPreferences("app pref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang",lang);
        editor.apply();
    }
    private void showNewQuestion() {
        Random random = new Random();
        randomQuestionIndex = random.nextInt(QUESTIONS.length);
        mCurrentQuestion = QUESTIONS[randomQuestionIndex];
        mCurrentAnswer = ANSWER[randomQuestionIndex];
        mCurrentAnswerDescription = ANSWER_DESCRIPTION[randomQuestionIndex];
        Drawable drawable = ContextCompat.getDrawable(MainActivity.this, mCurrentQuestion);
        mImageViewQuestion.setImageDrawable(drawable);    }
    public void onChangeQuestionClicked(View view){
        showNewQuestion();
    }
    public void onAnswerClicked(View view){
        Intent intent = new Intent(MainActivity.this,AnswerActivity.class);
        intent.putExtra("answer",mCurrentAnswer + ":" + mCurrentAnswerDescription);
        startActivity(intent);
    }
    public void onShareQuestionClicked(View view){
        Intent intent = new Intent(MainActivity.this,ShareActivity.class);
        intent.putExtra("question_extra",mCurrentQuestion);
        startActivity(intent);
    }
}
