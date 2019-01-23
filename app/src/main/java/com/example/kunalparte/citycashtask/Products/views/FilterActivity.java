package com.example.kunalparte.citycashtask.Products.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kunalparte.citycashtask.Products.Consts;
import com.example.kunalparte.citycashtask.R;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    private Button saveFilterButton;
    private String filterText = "";
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        saveFilterButton = (Button) findViewById(R.id.saveFilterButton);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(this);
        saveFilterButton.setOnClickListener(this);
        id = getIntent().getIntExtra(Consts.RADIO_ID,0);
        if (id != 0){
            RadioButton radioButton = (RadioButton) radioGroup.findViewById(id);
            radioButton.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saveFilterButton:
                Intent intent = new Intent();
                intent.putExtra(Consts.FILTER_KEY, filterText);
                intent.putExtra(Consts.RADIO_ID,id);
                setResult(2, intent);
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(checkedId);
        filterText = radioButton.getText().toString();
        id = radioButton.getId();

    }
}
