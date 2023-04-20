package com.miu.movieapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


abstract  class BaseFragment : Fragment(), CoroutineScope {
    protected lateinit var rootView : View
    abstract fun onCreateView()  : View
    abstract fun getLayout() : Int

    // Instance in the Co-routine Context. It's a background task runs under Coroutine scope
    private lateinit var job: Job
    /*  public val coroutineContext: CoroutineContext declared in the CoroutineScope interface
        Need to override in the subclass to initialize the value */
    override val coroutineContext: CoroutineContext
        // To perform the Job, Dispatchers.Main is used for CoroutineContext to perform UI operations
        // + is the operator fun plus
    get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create an Instance for the Job()
        job = Job()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(getLayout(), container, false)
        return onCreateView()
    }

    // Cancel the Job in onDestroy()
    override fun onDestroy() {
        super.onDestroy()
        // Cancel the Job
        job.cancel()
    }
}