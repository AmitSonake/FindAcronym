package com.amits.findacronymapp.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amits.findacronymapp.databinding.AdapterAcronymVarsBinding
import com.amits.findacronymapp.models.Acronym

class AcronymVarsAdapter : RecyclerView.Adapter<AcronymVarsViewHolder>() {

    private var acronymsList = mutableListOf<Acronym.Lf.Var>()

    fun setAcronymVarsList(acronyms: List<Acronym.Lf.Var>) {
        notifyDataSetChanged()
        this.acronymsList = acronyms.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymVarsViewHolder {
        val binding =
            AdapterAcronymVarsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcronymVarsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: AcronymVarsViewHolder, position: Int) {
        holder.bind(acronymsList[position])
    }
    override fun getItemCount(): Int {
        return acronymsList.size
    }
}
class AcronymVarsViewHolder(private val binding:AdapterAcronymVarsBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(varsObject: Acronym.Lf.Var){
        binding.varsObject=varsObject
    }
}


