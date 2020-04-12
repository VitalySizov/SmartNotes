package ru.vitalysizov.smartnotes.presentation.list_notes.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_notes.*
import moxy.ktx.moxyPresenter
import ru.vitalysizov.smartnotes.AppApplication
import ru.vitalysizov.smartnotes.R
import ru.vitalysizov.smartnotes.domain.models.NoteItem
import ru.vitalysizov.smartnotes.presentation.base.BaseFragment
import ru.vitalysizov.smartnotes.presentation.details_note.view.DetailsNoteFragmentArgs
import ru.vitalysizov.smartnotes.presentation.list_notes.mvp.ListNotesPresenter
import ru.vitalysizov.smartnotes.presentation.list_notes.view.adapter.ListNotesAdapter
import ru.vitalysizov.smartnotes.utils.visibleOrGone

class ListNotesFragment : BaseFragment<ListNotesPresenter>(), IListNotesView {

    private val presenter: ListNotesPresenter by moxyPresenter { lazyPresenter.get() }

    private val listNotesAdapter =
        ListNotesAdapter(
            actionNavigateToEdit = this::onClickNavigateToEdit,
            actionNavigateToDetails = this::onClickNavigateToDetails,
            actionDeleteNote = this::onClickDeleteNote
        )

    override val layoutId: Int
        get() = R.layout.fragment_list_notes

    override fun performInject() {
        AppApplication.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initListNotes()
    }

    private fun onClickNavigateToEdit(noteId: Int) {
        val args = DetailsNoteFragmentArgs(noteId)
        findNavController().navigate(
            R.id.action_listNotesFragment_to_editNoteFragment,
            args.toBundle()
        )
    }

    private fun onClickNavigateToDetails(noteId: Int) {
        val args = DetailsNoteFragmentArgs(noteId)
        findNavController().navigate(
            R.id.action_listNotesFragment_to_detailsNoteFragment,
            args.toBundle()
        )
    }

    private fun onClickDeleteNote(noteItem: NoteItem, position: Int) {
        presenter.deleteNote(noteItem, position)
    }

    private fun initToolbar() {
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_note -> {
                    presenter.addNoteItem()
                }
            }
            true
        }
    }

    private fun initListNotes() {
        rv_list_notes.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listNotesAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun setItems(items: List<NoteItem>) {
        listNotesAdapter.update(items)
    }

    override fun addNoteItem(item: NoteItem) {
        listNotesAdapter.addItem(item)
    }

    override fun deleteItem(item: NoteItem, position: Int) {
        listNotesAdapter.removeItem(item, position)
    }

    override fun showEmptyList(show: Boolean) {
        tv_empty_list.visibleOrGone(show)
    }

    override fun clearItems() {
        listNotesAdapter.clear()
    }
}



