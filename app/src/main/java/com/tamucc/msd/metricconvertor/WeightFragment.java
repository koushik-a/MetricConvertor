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

import com.tamucc.msd.metricconvertor.databinding.FragmentWeightBinding;

import java.text.NumberFormat;


public class WeightFragment extends Fragment {

    private static final String POUNDS = "pound";

    private double pounds;
    FragmentWeightBinding weightBinding;

    public WeightFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WeightFragment newInstance(double pounds) {
        WeightFragment fragment = new WeightFragment();
        Bundle args = new Bundle();
        args.putDouble(POUNDS, pounds);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pounds = getArguments().getDouble(POUNDS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        weightBinding = FragmentWeightBinding.inflate(inflater, container, false);
        return weightBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Weight Metrics");

        SharedPreferences preferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        weightBinding.textViewPounds.setText(String.format("%.2f",pounds)+" lb (Pounds)");

        weightBinding.textViewKilograms.setText(String.format("%.2f", pounds * Conversions.kilogram) + "kg (Kilograms)");
        weightBinding.textViewGrams.setText(String.format("%.2f",pounds * Conversions.gram) + "g (Grams)");
        weightBinding.textViewOunces.setText(String.format("%.2f",pounds * Conversions.ounces) + "oz (Ounces)");
    }
}