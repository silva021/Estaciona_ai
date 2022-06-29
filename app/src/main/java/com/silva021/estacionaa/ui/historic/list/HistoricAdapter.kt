package com.silva021.estacionaa.ui.historic.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.silva021.estacionaa.databinding.ItemHistoricBinding
import com.silva021.estacionaa.domain.model.HistoricModel

class HistoricAdapter(
    private val historic: List<HistoricModel>,
    private val onItemClickListener: (item: HistoricModel) -> Unit,
) : RecyclerView.Adapter<HistoricAdapter.HistoricHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricHolder {
        return HistoricHolder(
            ItemHistoricBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: HistoricHolder, position: Int) {
        holder.bind(historic[position])
    }

    override fun getItemCount() = historic.size

    class HistoricHolder(
        private val binding: ItemHistoricBinding,
        private val onItemClickListener: ((item: HistoricModel) -> Unit)?,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(historic: HistoricModel) {
            with(binding) {

                root.setOnClickListener { onItemClickListener?.invoke(historic) }

                paymentValue.text = (historic.paid)
                currentTimeValue.text = historic.time
            }
        }
    }
}