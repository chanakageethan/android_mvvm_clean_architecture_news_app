package com.example.mvvm_clean_architecure_2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mvvm_clean_architecure_2.R
import com.example.mvvm_clean_architecure_2.databinding.FragmentInfoBinding
import com.example.mvvm_clean_architecure_2.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


class InfoFragment : Fragment() {

        private lateinit var fragmentInfoBinding: FragmentInfoBinding
        private lateinit var viewModel:NewsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)

        val args : InfoFragmentArgs by navArgs()
        val article = args.selectedArticle

        viewModel =(activity as MainActivity).viewModel

//        fragmentInfoBinding.wvInfo.apply {
//            webViewClient = WebViewClient()
//            if(article?.url != null){
//                loadUrl(article?.url!!)
//            }
//        }

        fragmentInfoBinding.fabSave.setOnClickListener(){
            viewModel.saveArticle(article)

            Snackbar.make(view,"Saved Successfully",Snackbar.LENGTH_LONG).show()
        }




    }
}