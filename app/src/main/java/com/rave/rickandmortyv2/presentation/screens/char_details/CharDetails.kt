package com.rave.rickandmortyv2.presentation.screens.char_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rave.rickandmortyv2.R

class CharDetails : Fragment() {

    companion object {
        fun newInstance() = CharDetails()
    }

    private lateinit var viewModel: CharDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_char_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}