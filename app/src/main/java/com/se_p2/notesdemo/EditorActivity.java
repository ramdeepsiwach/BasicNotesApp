package com.se_p2.notesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditorActivity extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        EditText editText=findViewById(R.id.notesEditText);
        Button backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.adapter.notifyDataSetChanged();
                Intent intent=new Intent(EditorActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Intent intent=getIntent();
        noteId=intent.getIntExtra("noteId",-1);
        if(noteId != -1)
        {
            editText.setText(MainActivity.notes.get(noteId));
        }
        else
        {
            MainActivity.notes.add("New Note");
            noteId = MainActivity.notes.size() - 1;
            MainActivity.adapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteId,String.valueOf(charSequence));
                MainActivity.adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}