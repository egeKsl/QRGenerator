package com.example.qrgenerator.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qrgenerator.R;
import com.example.qrgenerator.databinding.FragmentGeneratedQrScreenBinding;


public class GeneratedQrScreen extends Fragment {

    FragmentGeneratedQrScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeneratedQrScreenBinding.inflate(inflater, container, false);

        if (getArguments() != null){
            String qrBase64 = getArguments().getString("qrBase64");
            Bitmap qrBitmap = convertBase64toBitmap(qrBase64);
            binding.imageViewGeneratedFragment.setImageBitmap(qrBitmap);
        }


        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_generatedQrScreen_to_createFragment);
            }
        });

        return binding.getRoot();
    }

    private Bitmap convertBase64toBitmap(String qrBase64String){
        byte[] decodedBytes = Base64.decode(qrBase64String,Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.length);
    }
}