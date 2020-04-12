package ru.vitalysizov.smartnotes.presentation.list_notes.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import ru.vitalysizov.smartnotes.R
import ru.vitalysizov.smartnotes.domain.models.NoteItem

class ListNotesAdapter(
    private val actionNavigateToDetails: (itemId: Int) -> Unit,
    private val actionNavigateToEdit: (itemId: Int) -> Unit,
    private val actionDeleteNote: (noteItem: NoteItem, position: Int) -> Unit
) :
    RecyclerView.Adapter<ListNotesAdapter.ViewHolder>() {
    private var notesList = mutableListOf<NoteItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(notesList[position])
    }

    fun update(items: List<NoteItem>) {
        notesList.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: NoteItem) {
        notesList.add(item)
        notifyItemInserted(notesList.size)
    }

    fun removeItem(item: NoteItem, position: Int) {
        notesList.remove(item)
        notifyItemRemoved(position)
    }

    fun clear() {
        notesList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(item: NoteItem) {
            itemView.background_container.setBackgroundResource(item.importanceColor)
            itemView.setOnClickListener { actionNavigateToDetails.invoke(item.id) }
            itemView.tv_title_notes.text = item.title
            itemView.ib_edit_note.setOnClickListener { actionNavigateToEdit.invoke(item.id) }
            itemView.ib_delete_note.setOnClickListener {
                actionDeleteNote.invoke(
                    item,
                    adapterPosition
                )
            }
        }
    }
}