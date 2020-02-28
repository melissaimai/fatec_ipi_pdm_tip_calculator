package com.example.fatec_ipi_pdm_tip_calculator;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;

    private double billAmount = 0.0;
    private double percent = 0.15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  //inflar a view
        amountTextView = findViewById((R.id.amountTextView));
        percentTextView = findViewById((R.id.percentTextView));
        tipTextView= findViewById((R.id.tipTextView));
        totalTextView = findViewById((R.id.totalTextView));

        zerarTudo();

        EditText amountEditText = findViewById(R.id.amountEditText);
        SeekBar percentSeekBar = findViewById(R.id.percentSeekBar);

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percent = progress / 100d;
                percentTextView.setText(percentFormat.format(percent));
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    billAmount = Double.parseDouble(s.toString()) / 100;
                    amountTextView.setText(currencyFormat.format(billAmount));
                    calcular();
                }
                catch (NumberFormatException e){
                    zerarTudo();

                }
            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calcular (){
        double tip = billAmount * percent;
        double total = billAmount + tip;
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));

    }

    private void zerarTudo (){
        billAmount = 0;
        amountTextView.setText(currencyFormat.format(billAmount));
        tipTextView.setText(currencyFormat.format(billAmount * percent));
        totalTextView.setText(currencyFormat.format(billAmount * percent * billAmount));
    }
}
