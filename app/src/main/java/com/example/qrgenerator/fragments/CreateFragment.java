package com.example.qrgenerator.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qrgenerator.R;
import com.example.qrgenerator.databinding.FragmentCreateBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;

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

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_createFragment_to_mainScreenFragment);
            }
        });

        return binding.getRoot();
    }

    private void generateQRCode(String text){
        BarcodeEncoder encoder = new BarcodeEncoder();

        try {
            //encoder will return a bitmap file
            Bitmap generatedQR = encoder.encodeBitmap(text, BarcodeFormat.QR_CODE,300,300);
            String qrBase64 = convertBitmapToBase64(generatedQR);

            navigateToDisplayFragment(qrBase64);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }

    private String convertBitmapToBase64(Bitmap givenBitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        givenBitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return Base64.encodeToString(byteArray,Base64.DEFAULT);
    }
    private void navigateToDisplayFragment(String qrBase64){
        Bundle bundle = new Bundle();
        bundle.putString("qrBase64",qrBase64);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_createFragment_to_generatedQrScreen,bundle);
    }


}