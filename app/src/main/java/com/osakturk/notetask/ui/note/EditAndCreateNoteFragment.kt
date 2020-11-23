package com.osakturk.notetask.ui.note

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.osakturk.notetask.R
import com.osakturk.notetask.base.BaseFragment
import com.osakturk.notetask.databinding.FragmentEditCreateBinding
import com.osakturk.notetask.di.Injectable
import com.osakturk.notetask.model.Note
import com.osakturk.notetask.ui.component.SliderDownMessageType
import kotlinx.android.synthetic.main.fragment_notes.*
import java.util.*
import kotlin.random.Random

class EditAndCreateNoteFragment : BaseFragment<VMEditAndCreateNote, FragmentEditCreateBinding>(),
    Injectable {
    override val getLayoutId: Int = R.layout.fragment_edit_create
    override val viewModelClass = VMEditAndCreateNote::class.java
    var id: Long? = null
    var title: String? = null
    private var desc: String? = null
    private var imageUrl: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeData()
        init()
    }

    private fun subscribeData() {
        viewModel.insertNoteResponse.observe(this, Observer {
            if (it){
                popFragment()
            } else {
                slideDownMessageView.openSlideDown("Note Couldn't Create", SliderDownMessageType.WARNING)
            }
        })

        viewModel.deleteNoteResponse.observe(this, Observer {
            if (it){
                popFragment()
            } else {
                slideDownMessageView.openSlideDown("Note Couldn't Delete", SliderDownMessageType.WARNING)
            }
        })
        viewModel.getModelResponse.observe(this, Observer {
            viewModel.deleteFromDb(it)
        })
    }

    private fun init() {
        id = arguments?.getLong("id")
        title = arguments?.getString("title")
        desc = arguments?.getString("description")
        imageUrl = arguments?.getString("imageUrl")

        binding.inputTitle.setText(title)
        binding.inputDescription.setText(desc)
        binding.inputImageUrl.setText(imageUrl)

        binding.saveButton.setOnClickListener {
            if (id != null){
                val note = Note(id!!,title = binding.inputTitle.text.toString(), desc = binding.inputDescription.text.toString(),imageUrl = binding.inputImageUrl.text.toString(), createDate = Date().time, isEdited = true)
                viewModel.updateDataToDb(note)
            } else {
                id = Random.nextLong(1, 1000000000)
                val note = Note(id!!,title = binding.inputTitle.text.toString(), desc = binding.inputDescription.text.toString(),imageUrl = binding.inputImageUrl.text.toString(), createDate = Date().time, isEdited = false)
                viewModel.insertDataToDb(note)
            }
        }
        if (id == null){
            binding.deleteButton.isClickable = false
            binding.deleteButton.background = resources.getDrawable(R.drawable.disable_button)
        }
        if (binding.deleteButton.isClickable){
            binding.deleteButton.setOnClickListener {
                viewModel.getNoteModel(id)
            }
        }
    }
}