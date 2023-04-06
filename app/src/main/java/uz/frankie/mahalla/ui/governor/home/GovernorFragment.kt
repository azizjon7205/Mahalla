package uz.frankie.mahalla.ui.governor.home

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.model.Village
import uz.frankie.mahalla.adapter.governor.VillagesListAdapter
import uz.frankie.mahalla.databinding.FragmentGovernorBinding
import uz.frankie.mahalla.utils.extentions.collectLA
import uz.frankie.mahalla.viewmodels.NeighborhoodVM

@AndroidEntryPoint
class GovernorFragment : Fragment(R.layout.fragment_governor), SearchView.OnQueryTextListener {
    private val binding by viewBinding(FragmentGovernorBinding::bind)
    private val adapterVillages by lazy { VillagesListAdapter() }
    private var list = ArrayList<Village>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private val neighborhoodVM: NeighborhoodVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfVillages()
        initViews()
    }

    private fun initViews() {
        adapterVillages.submit(list)
        adapterVillages.onClick = {
            findNavController().navigate(R.id.action_governorFragment_to_villagesFragment)
        }

        binding.apply {
            rvVillages.adapter = adapterVillages

            flSearch.setOnClickListener{
                llMain.visibility = View.GONE
                llSearch.visibility = View.VISIBLE

                val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
                searchView.isIconified = false
            }

            flBack.setOnClickListener{
                llMain.visibility = View.VISIBLE
                llSearch.visibility = View.GONE
            }

            searchView.isIconified = false
            searchView.requestFocus()
            searchView.setOnQueryTextListener(this@GovernorFragment)


        }

        neighborhoodVM.getNeighborhoodList()
        collectUiState()
    }

    private fun collectUiState() {
        neighborhoodVM.uiState.collectLA(viewLifecycleOwner) { uiState ->
            if (uiState.neighborhoodList.isNotEmpty()) {
                // set data Adapter or UI
                val list = uiState.neighborhoodList
            }

            if (uiState.isLoading) {
                val loaderDialog = true  // loaderDialog show
            } else {
                val loaderDialog = false  // loaderDialog dismiss
            }
        }
    }

    private fun listOfVillages(){
        list.add(Village(1, "Yoshlik"))
        list.add(Village(2, "Shodlik"))
        list.add(Village(3, "Miskin"))
        list.add(Village(4, "Shodiyona"))
        list.add(Village(5, "Shodlik"))

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        // Perform search logic here
        adapterVillages.performFiltering(query)
        Log.d("@@@", "Search q: $query")
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        // Update search results here
//                filter.filter(newText)
        Log.d("@@@", "Search n: $newText")
//        adapterVillages.performFiltering(newText)
        adapterVillages.filter.filter(newText)
        return true
    }
}