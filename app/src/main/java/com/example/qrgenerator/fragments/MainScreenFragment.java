package com.example.qrgenerator.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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


        binding.buttonScanMainScreenFragment.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_scanFragment);
        });
        binding.buttonQRCreateMainScreenFragment.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_createFragment);
        });
        binding.buttonOldScansMainScreenFragment.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_oldScanFragment);
        });
        binding.buttonOldQRMainScreenFragment.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.action_mainScreenFragment_to_oldQRFragment);
        });


        return binding.getRoot();
    }
}