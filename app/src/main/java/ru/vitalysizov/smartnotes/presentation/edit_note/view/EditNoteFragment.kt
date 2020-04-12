package ru.vitalysizov.smartnotes.presentation.edit_note.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_edit_note.*
import moxy.ktx.moxyPresenter
import ru.vitalysizov.smartnotes.presentation.AppActivity
import ru.vitalysizov.smartnotes.AppApplication
import ru.vitalysizov.smartnotes.R
import ru.vitalysizov.smartnotes.presentation.base.BaseFragment
import ru.vitalysizov.smartnotes.presentation.edit_note.mvp.EditNotePresenter
import ru.vitalysizov.smartnotes.presentation.edit_note.view.adapter.NoteImportanceAdapter

class EditNoteFragment : BaseFragment<EditNotePresenter>(), IEditNoteView {

    private val presenter: EditNotePresenter by moxyPresenter {
        lazyPresenter.get().apply {
            this.args = this@EditNoteFragment.args
        }
    }

    private val args: EditNoteFragmentArgs by navArgs()

    private val noteImportanceAdapter: NoteImportanceAdapter =
        NoteImportanceAdapter { choiceColor(it) }

    override fun performInject() {
        AppApplication.appComponent.inject(this)
    }

    override val layoutId: Int
        get() = R.layout.fragment_edit_note

    private fun choiceColor(color: Int) {
        presenter.choiceColorClick(color)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImportanceColorRecyclerView()
        initNavigationOnToolbar()
    }

    private fun initNavigationOnToolbar() {
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete_note -> {
                    presenter.onDeleteNoteClick()
                }
                R.id.cancel_edit_note -> {
                    presenter.onCancelEditClick()
                }
                R.id.save_note -> {
                    presenter.onSaveClick()
                }
            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun initImportanceColorRecyclerView() {
        rv_importance_colors.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = noteImportanceAdapter
        }
    }

    override fun setTitle(title: String) {
        et_title.setText(title)
    }

    override fun setText(text: String) {
        et_text.setText(text)
    }

    override fun setCurrentImportanceColor(color: Int) {
        noteImportanceAdapter.setCurrentColor(color)
    }

    override fun restartApp() {
        AppActivity.startWithClearStack(appActivity)
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

    override fun showCancelDialog() {
        val builder = AlertDialog.Builder(appActivity)
        builder.setMessage(getString(R.string.confirm_message_cancel))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, which ->
            presenter.setUiItems()
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    override fun showSaveDialog() {
        val builder = AlertDialog.Builder(appActivity)
        builder.setMessage(getString(R.string.confirm_message_save))
        builder.setPositiveButton(getString(R.string.yes)) { dialog, which ->
            presenter.saveNote(et_title.text.toString(), et_text.text.toString())
        }

        builder.setNegativeButton(getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }
}