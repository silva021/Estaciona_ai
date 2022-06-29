package com.silva021.estacionaa.ui.exit.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.silva021.estacionaa.R
import com.silva021.estacionaa.databinding.DialogPaymentBinding
import com.silva021.toolkit.extension.gone
import com.silva021.toolkit.extension.observe
import com.silva021.toolkit.extension.startAnimation
import com.silva021.toolkit.extension.stopAnimation
import com.silva021.toolkit.extension.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentDialog(private val plate: String) : DialogFragment() {
    private val binding: DialogPaymentBinding by lazy {
        DialogPaymentBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<PaymentDialogViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreen()
        setupListeners()
        setupObserver()
    }

    private fun setupObserver() {
        observe(viewModel.viewState) {
            when (it) {
                is ViewState.LoadingScreen -> handleLoadingScreen()
                is ViewState.DismissScreen -> handleDismissScreen()
                is ViewState.Error -> handleError()
                is ViewState.SuccessScreen -> handleSuccessScreen()
            }
        }
    }

    private fun handleError() {
        with(binding) {
            imageLoading.stopAnimation(R.drawable.ic_baseline_warning)
            textLoading.text = "Não foi possivel finalizar o pagamento"
        }
    }

    private fun handleDismissScreen() = dismiss()

    private fun handleSuccessScreen() {
        with(binding) {
            imageLoading.stopAnimation(R.drawable.ic_round_done_button)
            textLoading.text = "Pago"
        }
    }

    private fun handleLoadingScreen() {
        with(binding) {
            containerConfirm.gone()
            imageLoading.visible()
            imageLoading.startAnimation(com.silva021.toolkit.R.anim.rotate)
            textLoading.visible()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupScreen() {
        with(binding) {
            textPlate.text = plate
        }
    }

    private fun setupListeners() {
        with(binding) {
            buttonConfirm.setOnClickListener {
                viewModel.dispatchViewAction(ViewAction.ConfirmPayment(plate))
            }
            backButton.setOnClickListener {
                dismiss()
            }
        }
    }

    companion object {
        fun show(fragmentManager: FragmentManager, plate: String) {
            val paymentDialog = PaymentDialog(plate)
            paymentDialog.isCancelable = false
            paymentDialog.show(
                fragmentManager,
                PaymentDialog::class.qualifiedName.toString()
            )
        }
    }
}