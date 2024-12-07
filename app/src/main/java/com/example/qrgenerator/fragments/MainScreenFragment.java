package com.example.qrgenerator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qrgenerator.R;
import com.example.qrgenerator.databinding.FragmentMainScreenBinding;


public class MainScreenFragment extends Fragment {
    FragmentMainScreenBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false);



        return binding.getRoot();
    }
}