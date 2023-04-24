package com.miu.movieapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.miu.movieapp.R
import com.miu.movieapp.data.local.MovieDatabase
import com.miu.movieapp.data.local.MovieItem
import com.miu.movieapp.databinding.FragmentFavoriteBinding
import com.miu.movieapp.other.toastShort
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteFragment : BaseFragment() {
    lateinit var binding : FragmentFavoriteBinding
    var array = arrayOf("").toList()

    override fun onCreateView(): View {
        binding = FragmentFavoriteBinding.bind(rootView)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        reloadData()

        binding.btnAdd.setOnClickListener {
            val noteTitle = "Note : " + array.size
            val noteBody = binding.edtFavs.text.toString()
            // Check the input values are empty, then set the error message and give the focus

            if(noteBody.isEmpty()){
                binding.edtFavs.error = "Body Required"
                binding.edtFavs.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }

            launch {
                /* NoteDatabase needs an context argument, if it is not null
                let perform add or update query
                */
                context?.let {
                    // Create a new Note object
                    val mNote = MovieItem(noteTitle, noteBody)

                    MovieDatabase(it).getNoteDao().addNote(mNote)
                    it.toastShort("Note Saved")
                    reloadData()
                }
            }
        }
    }

    fun reloadData() {
        launch {
            /* here context is the getContext() from the Fragment, if the context
            * is not null, let's execute the block of code using let scope function
            * with the argument it's context object - Inline functions
            * similar like  if(context!=null){}*/
            context?.let{
                val notes = MovieDatabase(it).getNoteDao().getAllNotes()
                array = notes.map { it.title + it.note }

                var adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, array)
                adapter.setNotifyOnChange(true)
                binding.listviewFavs.adapter = adapter
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_favorite
    }

}