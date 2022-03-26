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

import com.tamucc.msd.metricconvertor.databinding.FragmentTemparatureBinding;


public class TemparatureFragment extends Fragment {

    private static final String FAHRENHEIT = "Fahrenheit";
    FragmentTemparatureBinding temperatureBinding;
    private double fahrenheit;

    public TemparatureFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TemparatureFragment newInstance(double frh) {
        TemparatureFragment fragment = new TemparatureFragment();
        Bundle args = new Bundle();
        args.putDouble(FAHRENHEIT, frh);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fahrenheit = getArguments().getDouble(FAHRENHEIT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        temperatureBinding = FragmentTemparatureBinding.inflate(inflater, container, false);
        return temperatureBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Temperature Metrics");
        SharedPreferences preferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        temperatureBinding.textViewFarahnheit.setText(fahrenheit +" F (Fahrenheit)");

        temperatureBinding.textViewDegree.setText(String.format("%.2f", ((fahrenheit-32) * (0.55))) + " °C (Degree Celsius)");
        temperatureBinding.textViewKelvin.setText(String.format("%.2f", ((fahrenheit-32) * (0.55)+(273.15))) + " k (Kelvin)");

    }
}