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

import com.tamucc.msd.metricconvertor.databinding.FragmentCurrencyBinding;

public class CurrencyFragment extends Fragment {

    private static final String DOLLAR = "dollar";
    FragmentCurrencyBinding currencyBinding;
    private Double dollar;

    public CurrencyFragment() {
        // Required empty public constructor
    }

    public static CurrencyFragment newInstance(double param1) {
        CurrencyFragment fragment = new CurrencyFragment();
        Bundle args = new Bundle();
        args.putDouble(DOLLAR, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dollar = getArguments().getDouble(DOLLAR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currencyBinding = FragmentCurrencyBinding.inflate(inflater, container, false);
        return currencyBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Currency Metrics");
        SharedPreferences preferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        currencyBinding.textViewUSD.setText(String.format("%.2f",dollar)+" $ (United States Dollar)");

        currencyBinding.textViewEuro.setText(String.format("%.2f", dollar * Conversions.euro) + " € (Euro)");
        currencyBinding.textViewINR.setText(String.format("%.2f",dollar * Conversions.indianRupee) + " ₹ (Indian Rupee)");
        currencyBinding.textViewAUS.setText(String.format("%.2f",dollar * Conversions.australianDollar) + "$ (Australian Dollar)");
        currencyBinding.textViewCAD.setText(String.format("%.2f",dollar * Conversions.canadianDollar) + "$ (Canadian Dollar)");
    }
}