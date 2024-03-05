package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
           val layoutInflater = LayoutInflater.from(parent.context)
           val view = when (viewType) {
                VIEW_TYPE_POLICE -> layoutInflater.inflate(R.layout.list_item_crime_police, parent, false)
                else -> layoutInflater.inflate(R.layout.list_item_crime, parent, false)
           }
           return CrimeHolder(view)
        }


        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<CrimeHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
        return CrimeHolder(binding)
    }
   override fun getItemViewType(position: Int): Int {
    val crime = crimes[position]
    return if (crime.requiresPolice) VIEW_TYPE_POLICE else VIEW_TYPE_NORMAL
   }

   companion object {
    private const val VIEW_TYPE_NORMAL = 0
    private const val VIEW_TYPE_POLICE = 1
   }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
        holder.bind(crime)
    }

    override fun getItemCount() = crimes.size
}
