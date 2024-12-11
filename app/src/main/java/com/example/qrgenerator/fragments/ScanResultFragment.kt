package com.example.qrgenerator.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qrgenerator.databinding.FragmentScanResultBinding

class ScanResultFragment : Fragment() {

    private var _binding: FragmentScanResultBinding? = null
    private val binding get() = _binding!!
    private var qrResult: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanResultBinding.inflate(inflater, container, false)

        arguments?.let {
            qrResult = it.getString("qrResult")
        }

        qrResult?.let { binding.textViewResultScanResultFragment.text = it }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

