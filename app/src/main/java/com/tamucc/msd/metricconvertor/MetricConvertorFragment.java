package com.tamucc.msd.metricconvertor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.NumberFormat;

public class MetricConvertorFragment extends Fragment {
    SharedPreferences preferences;
    private RadioGroup convertorRadioGroup;
    private EditText inputValueEditText;
    private Button convertBtn;
    private double input;
    static final String LENGTH = "length";
    static final String WEIGHT = "weight";
    static final String TEMPERATURE = "temp";
    static final String CURRENCY = "currency";

    public MetricConvertorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_metric_convertor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Metric Converter");
        inputValueEditText = getActivity().findViewById(R.id.editTextValue);
        convertorRadioGroup = getActivity().findViewById(R.id.radioGroupConvertor);
        inputValueEditText = getActivity().findViewById(R.id.editTextValue);
        convertBtn = getActivity().findViewById(R.id.btnConvert);
        preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (!preferences.getAll().isEmpty()) {
            String selectedRadio = preferences.getString("selected", TEMPERATURE);
            String input = preferences.getString("data", String.valueOf(0));
            if(selectedRadio.equals(LENGTH)){
                convertorRadioGroup.check(R.id.radioButtonLength);
            } else if(selectedRadio.equals(WEIGHT)){
                convertorRadioGroup.check(R.id.radioButtonWeight);
            } else if(selectedRadio.equals(TEMPERATURE)){
                convertorRadioGroup.check(R.id.radioButtonTemparature);
            } else  {
                convertorRadioGroup.check(R.id.radioButtonCurrency);
            }
            inputValueEditText.setText(input);
        }

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputValueEditText.getText().length() == 0) {
                    Toast.makeText(getActivity(), "Please enter valid input", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        input = Double.parseDouble(inputValueEditText.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(getActivity(), "Please Enter valid input", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int selected = convertorRadioGroup.getCheckedRadioButtonId();
                    if(selected == R.id.radioButtonLength) {
                        setSharedPreferences(LENGTH, input);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.containerView, LengthFragment.newInstance(input)).commit();
                    } else if (selected == R.id.radioButtonTemparature) {
                        setSharedPreferences(TEMPERATURE, input);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.containerView, TemparatureFragment.newInstance(input)).commit();
                    } else if(selected == R.id.radioButtonWeight){
                        setSharedPreferences(WEIGHT, input);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.containerView, WeightFragment.newInstance(input)).commit();
                    } else {
                        setSharedPreferences(CURRENCY, input);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .addToBackStack(null).replace(R.id.containerView, CurrencyFragment.newInstance(input)).commit();
                    }

                }
            }
        });

    }

    private void setSharedPreferences(String selectedRadioBtn, double input) {
        SharedPreferences.Editor editor = this.getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("selected", selectedRadioBtn);
        editor.putString("data", String.valueOf(input));
        editor.commit();
    }
}