package com.example.dialogs_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.dialogs_android.databinding.FragmentMyDialogBinding


class MyDialogFragment : DialogFragment() {

    lateinit var binding: FragmentMyDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_dialog, container, false)
        binding = FragmentMyDialogBinding.bind(view)

        binding.btn.setOnClickListener {
            val name = binding.et.text.toString()
            Toast.makeText(requireContext(), name, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}