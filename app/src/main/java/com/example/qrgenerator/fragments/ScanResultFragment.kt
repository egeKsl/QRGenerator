package com.example.qrgenerator.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.qrgenerator.R
import com.example.qrgenerator.databinding.FragmentScanResultBinding
import com.google.android.material.snackbar.Snackbar

class ScanResultFragment : Fragment() {

    private var _binding: FragmentScanResultBinding? = null
    private val binding get() = _binding!!
    private var qrResult: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanResultBinding.inflate(inflater, container, false)

        // Geri tuşu için callback ekle
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Navigation ile bir önceki ekrana geçiş
                findNavController().navigate(R.id.action_scanResultFragment_to_mainScreenFragment)
            }
        })

        // Gelen veriyi arguments ile al
        arguments?.let {
            qrResult = it.getString("qrResult")
        }

        // Gelen veriyi TextView'e yazdır
        qrResult?.let { binding.textViewResultScanResultFragment.text = it }

        // Kopyalama butonuna tıklanma özelliği ekle
        binding.buttonCopyScanResultFragment.setOnClickListener {
            val textToCopy = binding.textViewResultScanResultFragment.text.toString()
            if (textToCopy.isNotEmpty()) {
                copyToClipboard(textToCopy)
            } else {
                Toast.makeText(context, "Nothing to copy!", Toast.LENGTH_SHORT).show()
            }
        }

        // Open Link butonuna tıklanma özelliği ekle
        binding.buttonOpenLinkScanResultFragment.setOnClickListener {
            val textToCheck = binding.textViewResultScanResultFragment.text.toString()
            if (isValidUrl(textToCheck)) {
                openUrlInBrowser(textToCheck)
            } else {
                Snackbar.make(binding.root, "This is not a URL", Snackbar.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun copyToClipboard(text: String) {
        val clipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("QR Code Result", text)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(context, "Copied to clipboard!", Toast.LENGTH_SHORT).show()
    }

    private fun isValidUrl(url: String): Boolean {
        val urlPattern = Patterns.WEB_URL
        return urlPattern.matcher(url).matches()
    }

    private fun openUrlInBrowser(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Snackbar.make(binding.root, "Unable to open the link", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
