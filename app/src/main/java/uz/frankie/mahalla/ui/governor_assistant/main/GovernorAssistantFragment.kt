package uz.frankie.mahalla.ui.governor_assistant.main

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.adapter.governor_assistant.PersonInfoAdapter
import uz.frankie.mahalla.databinding.FragmentGovernorAssistantBinding
import uz.frankie.mahalla.dialogs.LoaderDialog
import uz.frankie.mahalla.dialogs.governor_assistant.CriteriaDialog
import uz.frankie.mahalla.ui.governor_assistant.BaseGovernorAssistantFragment
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import uz.frankie.mahalla.utils.extentions.collectLA
import uz.frankie.mahalla.utils.extentions.navigateSafely
import javax.inject.Inject
import uz.frankie.mahalla.utils.extentions.snackBar
import uz.frankie.mahalla.viewmodels.PopulationVM

@AndroidEntryPoint
class GovernorAssistantFragment : BaseGovernorAssistantFragment<FragmentGovernorAssistantBinding>(
    FragmentGovernorAssistantBinding::inflate
) {

    private val viewModel by viewModels<PopulationVM>()
    @Inject
    lateinit var preferences: SharedPreferenceHelper

    private val personInfoAdapter by lazy { PersonInfoAdapter() }
    private val loaderDialog by lazy { LoaderDialog(requireContext()) }
    override fun onViewCreate() {
        setUpToolbar()
        initViews()
        collectUiState()
    }

    private fun initViews() {
        viewModel.getPopulationList()

        binding.apply {
            rvPersons.adapter = personInfoAdapter

            ivFilter.setOnClickListener {
                CriteriaDialog(requireContext(), criteriaList()).apply {
                    clickSelectCriteria { criteriaName ->

                    }
                }.show()
            }

            ivAddUser.setOnClickListener {
                navController.navigateSafely(R.id.action_governorAssistantFragment_to_addUserFragment)
            }

            llDetail.setOnClickListener {
                navController.navigateSafely(R.id.action_governorAssistantFragment_to_detailsFragment)
            }
        }
        personInfoAdapter.setRootClickListener {
            navController.navigateSafely(R.id.action_governorAssistantFragment_to_detailsFragment)
        }
    }

    private fun collectUiState() {
        viewModel.uiState.collectLA(viewLifecycleOwner) { uiState ->
            personInfoAdapter.setData(uiState.populationList)

            if (uiState.isLoading) {
                loaderDialog.show()
            } else {
                loaderDialog.dismiss()
            }

            if (uiState.errorMessage != null) {
                snackBar(binding.root, uiState.errorMessage.asString(requireContext()))
                viewModel.clearErrorMessage()
            }

        }
    }

    private fun setUpToolbar() {
        binding.toolbarLayout.apply {
            title.text = resources.getString(R.string.xotin_qizlar)
            rightIv.setImageResource(R.drawable.ic_notification)
            rightIv.setOnClickListener {
                // todo
            }
            when(preferences.getRole()){
                "hokim" -> rightIv.visibility = View.GONE
                "rais" -> {
                    rightIv.visibility = View.VISIBLE
                }
                "worker" -> {
                    rightIv.visibility = View.GONE
                }

            }
        }
    }

    override fun getToolbar(): Toolbar = binding.toolbarLayout.toolbarFragment

    private fun criteriaList(): ArrayList<String> {
        val items = ArrayList<String>()
        items.add("Ijtimoiy komakka muhtoj")
        items.add("Boquvchisi yoq")
        items.add("Ajrashganlar")
        items.add("Manaviy-marifiy bilimlarga muhtoj")
        items.add("Ma`lum bir ish faoliyatiga ega bo`lomagan")
        items.add("Moliyaviy va manaviy komakka muhtoj")
        items.add("Tarbiyalanuchilar")
        items.add("Sinov muddatidagilar")
        items.add("Ijtimoiy komakka muhtoj")
        items.add("Boquvchisi yoq")
        items.add("Ajrashganlar")
        items.add("Manaviy-marifiy bilimlarga muhtoj")
        items.add("Ma`lum bir ish faoliyatiga ega bo`lomagan")
        items.add("Moliyaviy va manaviy komakka muhtoj")
        items.add("Tarbiyalanuchilar")
        items.add("Sinov muddatidagilar")
        items.add("Ijtimoiy komakka muhtoj")
        items.add("Boquvchisi yoq")
        items.add("Ajrashganlar")
        items.add("Manaviy-marifiy bilimlarga muhtoj")
        items.add("Ma`lum bir ish faoliyatiga ega bo`lomagan")
        items.add("Moliyaviy va manaviy komakka muhtoj")
        items.add("Tarbiyalanuchilar")
        items.add("Sinov muddatidagilar")
        items.add("Ijtimoiy komakka muhtoj")
        items.add("Boquvchisi yoq")
        items.add("Ajrashganlar")
        items.add("Manaviy-marifiy bilimlarga muhtoj")
        items.add("Ma`lum bir ish faoliyatiga ega bo`lomagan")
        items.add("Moliyaviy va manaviy komakka muhtoj")
        items.add("Tarbiyalanuchilar")
        items.add("Sinov muddatidagilar")
        return items
    }

}