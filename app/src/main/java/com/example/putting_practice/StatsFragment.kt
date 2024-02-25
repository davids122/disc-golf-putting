package com.example.putting_practice

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.putting_practice.databinding.FragmentCountingBinding
import com.example.putting_practice.databinding.FragmentStats1Binding
import com.google.android.material.snackbar.Snackbar
import java.lang.String.format
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round

class StatsFragment : Fragment() {

    private var madeShots = 0
        set(value) {
            field = value
            "$value".also { binding.madeShots.text = it }
        }
    private var missedShots = 0
        set(value) {
            field = value
            "$value".also { binding.missedShots.text = it }
        }

    private var _binding: FragmentStats1Binding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("stats") { _, bundle ->
            madeShots = bundle.getInt("madeShots")
            missedShots = bundle.getInt("missedShots")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStats1Binding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restartButton.setOnClickListener {
            findNavController().navigate(R.id.StatsFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
