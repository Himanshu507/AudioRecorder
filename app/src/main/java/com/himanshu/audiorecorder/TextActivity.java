package com.himanshu.audiorecorder;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.himanshu.audiorecorder.Custum_Text.AutoFitEditText;
import com.himanshu.audiorecorder.utils.AutoFitEditTextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextActivity extends AppCompatActivity {

    AutoFitEditText mAutoFitEditText;
    ConstraintLayout mRootView;
    Button mbackground_change, mfont_change;
    List<String> fonts;
    boolean first_time = false;
    private List<Integer> colorPickerColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        mAutoFitEditText = findViewById(R.id.text_here);
        mRootView = findViewById(R.id.root_view);
        mbackground_change = findViewById(R.id.background_change);
        mfont_change = findViewById(R.id.font_change);
        initAutoFitEditText();
        create_color_array();
        change_Background();
        mbackground_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_Background();
            }
        });

        mfont_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                font_Change();
            }
        });
    }

    private void font_Change() {
        Random r = new Random();
        int low = 0;
        int high = 4;
        int result = r.nextInt(high - low) + low;
        mAutoFitEditText.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), fonts.get(result)));
    }

    public void initAutoFitEditText() {
        mAutoFitEditText.setEnabled(true);
        mAutoFitEditText.setFocusableInTouchMode(true);
        mAutoFitEditText.setFocusable(true);
        mAutoFitEditText.setEnableSizeCache(false);
        //might cause crash on some devices
        mAutoFitEditText.setMovementMethod(null);
        // can be added after layout inflation;
        mAutoFitEditText.setMaxHeight(250);
        //don't forget to add min text size programmatically
        mAutoFitEditText.setMinTextSize(10f);

        AutoFitEditTextUtil.setNormalization(this, mRootView, mAutoFitEditText);
    }

    public void change_Background() {
        Random r = new Random();
        int low = 0;
        int high = 5;
        int result = r.nextInt(high - low) + low;
        mAutoFitEditText.setBackgroundColor(colorPickerColors.get(result));

    }

    public void create_color_array() {
        colorPickerColors = new ArrayList<>();
        colorPickerColors.add(ContextCompat.getColor(getApplicationContext(), R.color.colorGrayAlpha));
        colorPickerColors.add(ContextCompat.getColor(getApplicationContext(), R.color.colorBlue));
        colorPickerColors.add(ContextCompat.getColor(getApplicationContext(), R.color.colorSelectionBorder));
        colorPickerColors.add(ContextCompat.getColor(getApplicationContext(), R.color.colorWaveformBg));
        colorPickerColors.add(ContextCompat.getColor(getApplicationContext(), R.color.waveformSelected));
        colorPickerColors.add(ContextCompat.getColor(getApplicationContext(), R.color.colorTextGray));

        fonts = new ArrayList<>();
        fonts.add("pacifico.ttf");
        fonts.add("vacation.ttf");
        fonts.add("voice.ttf");
        fonts.add("montezregular.ttf");
        fonts.add("permanentmarker.ttf");
    }
}
