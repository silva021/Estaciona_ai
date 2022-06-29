package com.silva021.estacionaa.ui.historic.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.silva021.estacionaa.R
import com.silva021.estacionaa.databinding.FragmentHistoricListBinding
import com.silva021.toolkit.base.BaseFragment
import com.silva021.toolkit.extension.gone
import com.silva021.toolkit.extension.navigate
import com.silva021.toolkit.extension.observe
import com.silva021.toolkit.extension.onBackPressed
import com.silva021.toolkit.extension.stopAnimation
import com.silva021.toolkit.extension.visible
import com.silva021.estacionaa.domain.model.HistoricModel
import com.silva021.estacionaa.domain.navigation.AppNavigation
import com.silva021.estacionaa.domain.navigation.AppNavigation.HISTORIC_DETAILS_SCREEN_ID
import com.silva021.toolkit.extension.startAnimation

class HistoricListFragment : BaseFragment<HistoricListViewModel>(HistoricListViewModel::class) {
    private val binding: FragmentHistoricListBinding by lazy {
        FragmentHistoricListBinding.inflate(layoutInflater)
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

        arguments?.getString(AppNavigation.HISTORIC_LIST_SCREEN_ID)?.let {
            setupScreen(it)
            viewModel.dispatchViewAction(ViewAction.SearchList(it))
        }
    }

    private fun setupScreen(titleText: String) {
        with(binding.toolbar) {
            title = titleText
            setOnClickListener {
                onBackPressed()
            }

            binding.imageLoading.startAnimation(com.silva021.toolkit.R.anim.rotate)
        }
    }

    private fun observerViewState() {
        observe(viewModel.viewState) {
            when (it) {
                is ViewState.SetupList -> handleSetupList(it.list)
                is ViewState.SetupEmptyList -> handleSetupEmptyList()
            }
        }
    }

    private fun handleSetupEmptyList() {
        with(binding) {
            recyclerView.gone()
            containerShimmer.gone()
            imageLoading.stopAnimation()
            textLoading.visible()
        }
    }

    private fun handleSetupList(list: List<HistoricModel>) {
        with(binding) {
            recyclerView.visible()
            imageLoading.stopAnimation()
            containerShimmer.gone()
            recyclerView.adapter = HistoricAdapter(list) {
                navigate(R.id.historic_details_screen, bundleOf(HISTORIC_DETAILS_SCREEN_ID to it))
            }
        }
    }
}