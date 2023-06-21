package uz.frankie.mahalla.ui.governor.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.model.Village
import uz.frankie.mahalla.adapter.governor.VillagesListAdapter
import uz.frankie.mahalla.databinding.FragmentGovernorBinding
import uz.frankie.mahalla.model.Neighborhood
import uz.frankie.mahalla.ui.BaseFragment
import uz.frankie.mahalla.utils.extentions.collectLA
import uz.frankie.mahalla.viewmodels.NeighborhoodVM

@AndroidEntryPoint
class GovernorFragment : BaseFragment<FragmentGovernorBinding>(FragmentGovernorBinding::inflate), SearchView.OnQueryTextListener {

    private val adapterVillages by lazy { VillagesListAdapter() }
    private var list = ArrayList<Neighborhood>()
    private val neighborhoodVM: NeighborhoodVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        neighborhoodVM.getNeighborhoodList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfVillages()
        initViews()
//        collectUiState()
    }

    override fun onViewCreate() {

    }

    override fun getToolbar(): Toolbar = binding.toolbar

    private fun initViews() {
        adapterVillages.onClick = {
            findNavController().navigate(R.id.action_governorFragment_to_villagesFragment)
        }

        adapterVillages.submit(list)
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

    }

    private fun collectUiState() {
        neighborhoodVM.uiState.collectLA(viewLifecycleOwner) { uiState ->
            binding.flLoading.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
            if (uiState.data.isNotEmpty()) {
                // set data Adapter or UI
                val list = uiState.data
                Log.d("@@@", "Villages list: ${list}")
                adapterVillages.submit(uiState.data)
            }

            if (uiState.isLoading) {
                val loaderDialog = true  // loaderDialog show
            } else {
                val loaderDialog = false  // loaderDialog dismiss
            }

            Log.d("@@@", "Villages list: ${uiState.errorMessage}")
        }
    }

    private fun listOfVillages(){
        list.clear()
        list.add(Neighborhood("1", "Yoshlik", "Olmazor", 1))
        list.add(Neighborhood("2", "Shodlik", "Olmazor", 1))
        list.add(Neighborhood("3", "Miskin", "Olmazor", 1))
        list.add(Neighborhood("4", "Shodiyona", "Olmazor", 1))
        list.add(Neighborhood("5", "Shodlik", "Olmazor", 1))

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