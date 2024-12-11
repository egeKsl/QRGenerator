package com.example.qrgenerator.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.qrgenerator.R;
import com.example.qrgenerator.databinding.FragmentScanBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanFragment extends Fragment {

    FragmentScanBinding binding;

    /*private final ActivityResultLauncher<Intent> qrActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    IntentResult intentResult = IntentIntegrator.parseActivityResult(result.getResultCode(), result.getData());
                    if (intentResult != null) {
                        if (intentResult.getContents() == null) {
                            Toast.makeText(requireContext(), "Scan Cancelled", Toast.LENGTH_SHORT).show();
                        } else {
                            // Show QR content and format
                            Toast.makeText(requireContext(),
                                    "Content: " + intentResult.getContents() + "\nFormat: " + intentResult.getFormatName(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });*/

    private final ActivityResultLauncher<Intent> qrActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
                if(result.getResultCode() == getActivity().RESULT_OK && result.getData() != null){
                    IntentResult intentResult = IntentIntegrator.parseActivityResult(result.getResultCode(),result.getData());
                    if(intentResult != null){
                        if(intentResult.getContents() == null){
                            Toast.makeText(requireContext(),"Scan Cancelled",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(requireContext(),
                                    "Content: " + intentResult.getContents() + "\nFormat: " + intentResult.getFormatName(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScanBinding.inflate(inflater, container, false);

        initiateQRScan();

        return binding.getRoot();
    }

    private void initiateQRScan(){
        //initalize the IntentIntegrator for fragment
        IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(ScanFragment.this);
        intentIntegrator.setPrompt("Scan a QR Code or Barcode");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setBeepEnabled(true);

        qrActivityResultLauncher.launch(intentIntegrator.createScanIntent());

    }
}