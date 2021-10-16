package com.soten.sotenshopclient.ui.home.search

import android.view.inputmethod.EditorInfo
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.soten.sotenshopclient.R
import com.soten.sotenshopclient.adapater.HistoryEntityAdapter
import com.soten.sotenshopclient.databinding.FragmentSearchBinding
import com.soten.sotenshopclient.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    override fun getDataBinding() = FragmentSearchBinding.inflate(layoutInflater)

    private val viewModel by viewModels<SearchViewModel>()

    private val historyAdapter by lazy {
        HistoryEntityAdapter(
            itemClickListener = {
                val bundle = bundleOf(
                    KEY_KEYWORD to it.keyword
                )

                viewModel.insertHistory(it.keyword)
                findNavController().navigate(R.id.navigationSearchResultFragment, bundle)
            },
            deleteClickListener = {
                viewModel.deleteHistory(it)
            }
        )
    }

    override fun initViews() {
        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.insertHistory(getKeyword())
                val bundle = bundleOf(
                    KEY_KEYWORD to getKeyword()
                )
                binding.searchEditText.text.clear()
                findNavController().navigate(R.id.navigationSearchResultFragment, bundle)
                true
            } else {
                false
            }
        }

    }

    override fun bindViews() {
        binding.historyRecyclerView.apply {
            adapter = historyAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun observeData() {
        viewModel.historyLiveData.observe(viewLifecycleOwner) {
            historyAdapter.submitList(it)
        }
    }

    private fun getKeyword() = binding.searchEditText.text.toString()

    companion object {
        const val KEY_KEYWORD = "KEY_KEYWORD"
    }

}