package com.example.mvvm_clean_architecure_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_clean_architecure_2.databinding.FragmentSavedBinding
import com.example.mvvm_clean_architecure_2.presentation.adapter.NewsAdapter
import com.example.mvvm_clean_architecure_2.presentation.viewmodel.NewsViewModel


class SavedFragment : Fragment() {
    private lateinit var fragmentSavedBinding: FragmentSavedBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter :NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentSavedBinding = FragmentSavedBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity  as MainActivity).newsAdapter

        newsAdapter?.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            try {
                findNavController().navigate(
                    R.id.action_newsFragment_to_infoFragment,
                    bundle
                )

            }catch (e:Exception){
//                e.stackTrace
            }
        }
        initRecyclerView()


        viewModel.getSavedNews().observe(viewLifecycleOwner) {
            newsAdapter.differ.submitList(it)
        }

    }


    private fun initRecyclerView() {
//        newsAdapter = NewsAdapter()
        fragmentSavedBinding.rvSaved.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
//            addOnScrollListener(this@NewsFragment.onScrollListener)
        }


    }
}