package com.example.mvvm_clean_architecure_2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_clean_architecure_2.R
import com.example.mvvm_clean_architecure_2.databinding.FragmentSavedBinding
import com.example.mvvm_clean_architecure_2.presentation.adapter.NewsAdapter
import com.example.mvvm_clean_architecure_2.presentation.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar


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

        val itemTouchHelperCallback = object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article= newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)

                Snackbar.make(view,"DELETE SUCCESSFULLY",Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo"){
                            viewModel.saveArticle(article)
                        }
                        show()
                    }
            }

        }
            ItemTouchHelper(itemTouchHelperCallback).apply {
                attachToRecyclerView(fragmentSavedBinding.rvSaved)
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