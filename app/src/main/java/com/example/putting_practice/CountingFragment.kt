package com.example.putting_practice

import android.os.Bundle
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
import com.google.android.material.snackbar.Snackbar

class CountingFragment : Fragment() {

    private var num_shots_View: TextView? = null
    // TODO: Rename and change types of parameters
    private var numShotsRecieved = ""
    private var numShotsMade = 0
    private var numShotsMissed = 0
    private var numShotsRemaining: Int = 5
        set(value) {
            field = value
            "$value putts remaining".also { num_shots_View?.text = it }
            if (value == 0) {
                val shotsMade = numShotsMade
                val shotsMissed = numShotsMissed
                setFragmentResult("stats",
                    bundleOf("madeShots" to shotsMade, "missedShots" to shotsMissed))
                findNavController().navigate(R.id.action_CountingFragment_to_StatsFragment)
            }
        }


    private var _binding: FragmentCountingBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("requestKey") { _, bundle ->
            numShotsRecieved = bundle.getString("bundleKey") ?: "5"
            numShotsRemaining = numShotsRecieved.toInt()
            "$numShotsRemaining putts remaining!".also { num_shots_View?.text = it }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCountingBinding.inflate(inflater, container, false)

        num_shots_View = binding.numShotsText
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.madeShotButton.setOnClickListener {
            if(numShotsRemaining > 0) {
                numShotsMade += 1
                numShotsRemaining -= 1
            }
        }

        binding.missedShotButton.setOnClickListener {
            if(numShotsRemaining > 0) {
                numShotsMissed += 1
                numShotsRemaining -= 1
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}