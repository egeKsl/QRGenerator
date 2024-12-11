package com.example.qrgenerator.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qrgenerator.R;
import com.example.qrgenerator.databinding.FragmentCreateBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class CreateFragment extends Fragment {
    FragmentCreateBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateBinding.inflate(inflater, container, false);

        binding.buttonGenerateCreateFragment.setOnClickListener(view ->{
            //I have to check if editext is empty
            String givenText = binding.inputEditTextCreateFragment.getText().toString();
            if(givenText == ""){
                Toast.makeText(getContext(),"I gotta input text", Toast.LENGTH_SHORT).show();
            }else{
                generateQRCode(givenText);
            }

        });

        return binding.getRoot();
    }

    private void generateQRCode(String text){
        BarcodeEncoder encoder = new BarcodeEncoder();

        try {
            //encoder will return a bitmap file
            Bitmap generatedQR = encoder.encodeBitmap(text, BarcodeFormat.QR_CODE,300,300);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }
}