package com.example.qrgenerator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qrgenerator.R;
import com.example.qrgenerator.databinding.FragmentOldQRBinding;
import com.example.qrgenerator.databinding.FragmentOldScanBinding;


public class OldQRFragment extends Fragment {
    FragmentOldQRBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOldQRBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}