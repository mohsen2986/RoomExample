package com.example.roomexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWord extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText mEditTextWordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditTextWordView = findViewById(R.id.edit_word);
        Button button =findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reply=new Intent();
                if(TextUtils.isEmpty(mEditTextWordView.getText()))
                    setResult(RESULT_CANCELED,reply);
                else{
                    String word=mEditTextWordView.getText().toString();
                    reply.putExtra(EXTRA_REPLY,word);
                    setResult(RESULT_OK,reply);
                }
                finish();
            }
        });
    }
}
