package com.himanshu.audiorecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button record_Btn, text_Btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        record_Btn = findViewById(R.id.record);
        text_Btn = findViewById(R.id.text);

        record_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AudioTrimmerActivity.class);
                startActivityForResult(i, 1);
                overridePendingTransition(R.anim.open_activity_from_bottom, R.anim.open_top);

            }
        });

        text_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TextActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.open_activity_from_bottom, R.anim.open_top);
            }
        });

    }

    private void showDialog() {
        final EditText galleryTv;
        final Button submit, cancel;
        View view = View.inflate(this, R.layout.dialog_box, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .show();

        galleryTv = view.findViewById(R.id.galleryTv);
        submit = view.findViewById(R.id.submit);
        cancel = view.findViewById(R.id.cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), galleryTv.getText().toString(), Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryTv.clearComposingText();
                alertDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String path = data.getExtras().getString("INTENT_AUDIO_FILE");
                    Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
