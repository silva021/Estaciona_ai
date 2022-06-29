package com.silva021.estacionaa.ui.exit.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import com.silva021.estacionaa.R
import com.silva021.estacionaa.databinding.FragmentExitBinding
import com.silva021.toolkit.base.BaseFragment
import com.silva021.toolkit.extension.hideKeyboard
import com.silva021.toolkit.extension.navigate
import com.silva021.toolkit.extension.observe
import com.silva021.estacionaa.domain.navigation.AppNavigation.HISTORIC_LIST_SCREEN_ID
import com.silva021.estacionaa.ui.exit.out.OutDialog
import com.silva021.estacionaa.ui.exit.payment.PaymentDialog

class ExitFragment : BaseFragment<ExitViewModel>(ExitViewModel::class) {
    private val binding: FragmentExitBinding by lazy {
        FragmentExitBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewState()
        observerListener()
    }

    private fun observerListener() {
        with(binding) {
            textInputLicensePlate.addTextChangedListener {
                viewModel.dispatchViewAction(ViewAction.ValidateBoard(it.toString()))
            }

            historicButton.setOnClickListener {
                navigate(R.id.historic_screen, bundleOf(HISTORIC_LIST_SCREEN_ID to textInputLicensePlate.text.toString()))
            }

            paymentButton.setOnClickListener {
                PaymentDialog.show(childFragmentManager, textInputLicensePlate.text.toString())
//                textInputLicensePlate.setText("")
            }

            exitButton.setOnClickListener {
                OutDialog.show(childFragmentManager, textInputLicensePlate.text.toString())
//                textInputLicensePlate.setText("")
            }
        }
    }

    private fun observerViewState() {
        observe(viewModel.viewState) {
            when (it) {
                is ViewState.EnableButton -> handleEnableButton(it.state)
                is ViewState.ErrorScreen -> handleErrorScreen()
            }
        }
    }

    private fun handleErrorScreen() {
    }

    private fun handleEnableButton(state: Boolean) {
        with(binding) {
            exitButton.isEnabled = state
            historicButton.isEnabled = state
            paymentButton.isEnabled = state
            hideKeyboard()
        }
    }
}