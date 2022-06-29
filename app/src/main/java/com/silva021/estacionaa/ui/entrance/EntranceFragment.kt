package com.silva021.estacionaa.ui.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.silva021.estacionaa.R
import com.silva021.toolkit.base.BaseFragment
import com.silva021.estacionaa.databinding.FragmentEntranceBinding
import com.silva021.toolkit.extension.gone
import com.silva021.toolkit.extension.hideKeyboard
import com.silva021.toolkit.extension.observe
import com.silva021.toolkit.extension.startAnimation
import com.silva021.toolkit.extension.stopAnimation
import com.silva021.toolkit.extension.visible

class EntranceFragment : BaseFragment<EntranceViewModel>(EntranceViewModel::class) {
    private val binding: FragmentEntranceBinding by lazy {
        FragmentEntranceBinding.inflate(layoutInflater)
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

    private fun FragmentEntranceBinding.startAnimation() {
        containerInput.gone()
        imageLoading.visible()
        textLoading.visible()
        imageLoading.setImageResource(R.drawable.ic_loading)
        textLoading.setText(R.string.entrance_title_loading)
        imageLoading.startAnimation(com.silva021.toolkit.R.anim.rotate)
    }

    private fun observerListener() {
        with(binding) {
            textInputLicensePlate.doAfterTextChanged {
                viewModel.dispatchViewAction(ViewAction.ValidateBoard(it.toString()))
            }

            button.setOnClickListener {
                viewModel.dispatchViewAction(
                    ViewAction.ClickConfirmEntrance(
                        textInputLayoutLicensePlate.editText?.text.toString()
                    )
                )
            }
        }
    }

    private fun observerViewState() {
        observe(viewModel.viewState) {
            when (it) {
                is ViewState.EnableButton -> handleEnableButton(it.state)
                is ViewState.Loading -> handleLoading()
                is ViewState.LoadingSuccess -> handleLoadingSuccess()
                is ViewState.SetupScreen -> handleSetupScreen()
                is ViewState.ErrorScreen -> handleErrorScreen()
            }
        }
    }


    private fun handleErrorScreen() {
        Snackbar.make(binding.root, "Não foi possível reservar esse carro", Snackbar.ANIMATION_MODE_SLIDE).show()
    }

    private fun handleSetupScreen() {
        with(binding) {
            imageLoading.stopAnimation()
            imageLoading.gone()
            textLoading.gone()
            containerInput.visible()
        }
    }

    private fun handleLoading() {
        with(binding) {
            startAnimation()
        }
    }

    private fun handleLoadingSuccess() {
        with(binding) {
            imageLoading.stopAnimation(R.drawable.ic_round_done_button)
            textLoading.setText(R.string.entrance_title_loading_success)
        }
    }

    private fun handleEnableButton(state: Boolean) {
        with(binding) {
            button.isEnabled = state
            if (state)
                hideKeyboard()
        }
    }
}