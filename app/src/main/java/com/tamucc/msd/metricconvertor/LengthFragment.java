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

import com.tamucc.msd.metricconvertor.databinding.FragmentLengthBinding;


public class LengthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MILES = "miles";
    FragmentLengthBinding lengthBinding;

    // TODO: Rename and change types of parameters
    private double miles;

    public LengthFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LengthFragment newInstance(double m) {
        LengthFragment fragment = new LengthFragment();
        Bundle args = new Bundle();
        args.putDouble(MILES, m);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            miles = getArguments().getDouble(MILES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        lengthBinding = FragmentLengthBinding.inflate(inflater, container, false);
        return lengthBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Length Metrics");
        SharedPreferences preferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        lengthBinding.textViewMiles.setText(String.format("%.2f",miles)+" Miles");
        lengthBinding.textViewkiloMeters.setText(String.format("%.2f", miles * Conversions.kilometers) + " Kilometers");
        lengthBinding.textViewMeters.setText(String.format("%.2f",miles * Conversions.meters) + " Meters");
        lengthBinding.textViewFeet.setText(String.format("%.2f",miles * Conversions.feet) + " Feet");
        lengthBinding.textViewInches.setText(String.format("%.2f",miles * Conversions.inches) + " Inches");

    }
}