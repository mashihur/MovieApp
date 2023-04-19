package com.miu.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract  class BaseFragment : Fragment() {
    protected lateinit var rootView : View
    abstract fun onCreateView()  : View
    abstract fun getLayout() : Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(getLayout(), container, false)
        return onCreateView()
    }
    
}