package com.example.mvvm_clean_architecure_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.mvvm_clean_achitecture.data.model.Article
import com.example.mvvm_clean_architecure_2.InfoFragmentArgs
import com.example.mvvm_clean_architecure_2.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {

        private lateinit var fragmentInfoBinding: FragmentInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)

//        val args : InfoFragmentArgs by navArgs()
//        val article = args.selectedArticle
//
//        fragmentInfoBinding.wvInfo.apply {
//            webViewClient = WebViewClient()
//            if(article?.url != ""){
//                loadUrl(url!!)
//            }
//        }




    }
}