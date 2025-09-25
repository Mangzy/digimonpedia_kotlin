package com.example.digimonpedia.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digimonpedia.R
import com.example.digimonpedia.core.data.Resource
import com.example.digimonpedia.core.ui.DigimonAdapter
import com.example.digimonpedia.databinding.FragmentHomeBinding
import com.example.digimonpedia.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val digimonAdapter = DigimonAdapter()
            var fullList: List<com.example.digimonpedia.core.domain.model.Digimon> = emptyList()
            digimonAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }



            homeViewModel.digimon.observe(viewLifecycleOwner) { digimon ->
                if (digimon != null) {
                    when (digimon) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            fullList = digimon.data ?: emptyList()
                            digimonAdapter.submitList(fullList)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                digimon.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

            with(binding.rvDigimon) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = digimonAdapter
            }

            binding.etSearch.addTextChangedListener { text ->
                val q = text?.toString()?.trim()?.lowercase().orEmpty()
                val filtered = if (q.isEmpty()) fullList else fullList.filter {
                    it.name.lowercase().contains(q) || it.level.lowercase().contains(q)
                }
                digimonAdapter.submitList(filtered)
                binding.viewError.root.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
                if (filtered.isEmpty()) {
                    binding.viewError.tvError.text = getString(R.string.something_wrong)
                }
            }
        }
    }
}