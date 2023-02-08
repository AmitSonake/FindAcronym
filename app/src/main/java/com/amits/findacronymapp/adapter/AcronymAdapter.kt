package com.amits.findacronymapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amits.findacronymapp.databinding.AdapterAcronymBinding
import com.amits.findacronymapp.models.Acronym

class AcronymAdapter : RecyclerView.Adapter<AcronymViewHolder>() {

    private var acronymsList = mutableListOf<Acronym>()
    private var acronymsVarsList = mutableListOf<Acronym.Lf.Var>()
    private lateinit var adapterVars: AcronymVarsAdapter

    fun setAcronymList(acronyms: List<Acronym>,adapterVars: AcronymVarsAdapter) {
        this.acronymsList = acronyms.toMutableList()
        this.adapterVars=adapterVars
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        val binding =
            AdapterAcronymBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcronymViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        val acronymObject= acronymsList[position].lfs!![position]
        holder.bind(acronymObject!!)
        acronymsVarsList= acronymsList[position].lfs?.get(position)?.vars as MutableList<Acronym.Lf.Var>
        loadVariationList(acronymsVarsList, adapterVars)
    }

    override fun getItemCount(): Int {
        return acronymsList.size
    }

    private fun loadVariationList(list:List<Acronym.Lf.Var>, adapterVars: AcronymVarsAdapter) {
        adapterVars.setAcronymVarsList(list)
    }
}
class AcronymViewHolder(private val binding:AdapterAcronymBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(lfObject: Acronym.Lf){
            binding.lfObject=lfObject
        }
}


