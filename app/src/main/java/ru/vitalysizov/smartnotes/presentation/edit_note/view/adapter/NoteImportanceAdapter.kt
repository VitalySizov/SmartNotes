package ru.vitalysizov.smartnotes.presentation.edit_note.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_importance_color.view.*
import ru.vitalysizov.smartnotes.R

class NoteImportanceAdapter(private val action: (color: Int) -> Unit) :
    RecyclerView.Adapter<NoteImportanceAdapter.ViewHolder>() {

    private var currentColor: Int? = null
    private val listColors = listOf(R.color.transparent_green, R.color.transparent_yellow, R.color.transparent_red, R.color.white)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_importance_color, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listColors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listColors[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(color: Int) {
            itemView.iv_color.setBackgroundResource(color)

            if (currentColor == null) {
                return
            }

            if (currentColor == color) {
                itemView.iv_color.setImageResource(R.drawable.ic_check_black_24dp)
            } else {
                itemView.iv_color.setImageResource(0)
            }

            itemView.setOnClickListener { action.invoke(color) }
        }
    }

    fun setCurrentColor(color: Int) {
        currentColor = color
        notifyDataSetChanged()
    }
}