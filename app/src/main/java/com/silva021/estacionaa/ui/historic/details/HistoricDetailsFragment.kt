package com.silva021.estacionaa.ui.historic.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silva021.estacionaa.databinding.FragmentHistoricDetailsBinding
import com.silva021.toolkit.extension.onBackPressed
import com.silva021.estacionaa.domain.model.HistoricModel
import com.silva021.estacionaa.domain.navigation.AppNavigation

class HistoricDetailsFragment : Fragment() {
    private val binding: FragmentHistoricDetailsBinding by lazy {
        FragmentHistoricDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getSerializable(AppNavigation.HISTORIC_DETAILS_SCREEN_ID)?.let {
            val historicModel = it as HistoricModel
            setupScreen(historicModel)
            setupListener()
        }
    }

    private fun setupListener() {
        with(binding) {
            toolbarDetails.setOnClickListener { onBackPressed() }
        }
    }

    private fun setupScreen(historicModel: HistoricModel) {
        with(binding) {
            plateDetails.text = historicModel.plate
            statusDetails.text = historicModel.left
            paymentDetails.text = historicModel.paid
            currentTimeDetails.text = historicModel.time
        }
    }
}