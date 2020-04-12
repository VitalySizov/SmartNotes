package ru.vitalysizov.smartnotes.presentation.details_note.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_details_note.*
import moxy.ktx.moxyPresenter
import ru.vitalysizov.smartnotes.AppApplication
import ru.vitalysizov.smartnotes.R
import ru.vitalysizov.smartnotes.presentation.base.BaseFragment
import ru.vitalysizov.smartnotes.presentation.details_note.mvp.DetailsNotePresenter
import ru.vitalysizov.smartnotes.presentation.edit_note.view.EditNoteFragmentArgs

class DetailsNoteFragment : BaseFragment<DetailsNotePresenter>(), IDetailsNoteView {

    private val presenter: DetailsNotePresenter by moxyPresenter {
        lazyPresenter.get().apply {
            this.args = this@DetailsNoteFragment.args
        }
    }

    private val args: DetailsNoteFragmentArgs by navArgs()

    override val layoutId: Int
        get() = R.layout.fragment_details_note

    override fun performInject() {
        AppApplication.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edit_note -> {
                    navigateToEditNote()
                }
                R.id.delete_note -> {
                    presenter.onDeleteNoteClick()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun setTitle(title: String) {
        toolbar.title = title
    }

    override fun setText(text: String) {
        tv_note_text.text = text
    }

    private fun navigateToEditNote() {
        val args = EditNoteFragmentArgs(args.noteId)
        findNavController().navigate(
            R.id.action_detailsNoteFragment_to_editNoteFragment,
            args.toBundle()
        )
    }

    override fun showDeleteDialog(title: String) {
        val builder = AlertDialog.Builder(appActivity)
        builder.setMessage(getString(R.string.confirm_message_delete, title))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, which ->
            presenter.deleteNote()
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }
}