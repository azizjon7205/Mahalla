package uz.frankie.mahalla.ui.governor_assistant.main

import android.view.View
import androidx.appcompat.widget.Toolbar
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentGovernorAssistantBinding
import uz.frankie.mahalla.adapter.governor_assistant.PersonInfo
import uz.frankie.mahalla.adapter.governor_assistant.PersonInfoAdapter
import uz.frankie.mahalla.dialogs.governor_assistant.CriteriaDialog
import uz.frankie.mahalla.ui.governor_assistant.BaseGovernorAssistantFragment
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import uz.frankie.mahalla.utils.extentions.navigateSafely
import javax.inject.Inject

@AndroidEntryPoint
class GovernorAssistantFragment : BaseGovernorAssistantFragment<FragmentGovernorAssistantBinding>(
    FragmentGovernorAssistantBinding::inflate
) {

    @Inject
    lateinit var preferences: SharedPreferenceHelper

    private val personInfoAdapter by lazy { PersonInfoAdapter() }
    override fun onViewCreate() {
        setUpToolbar()
        initViews()
    }

    private fun initViews(){
        personInfoAdapter.setData(personList())
        binding.apply {
            rvPersons.adapter = personInfoAdapter

            ivFilter.setOnClickListener {
                CriteriaDialog(requireContext(), criteriaList()).apply {
                    clickSelectCriteria { criteriaName ->
                        personList(criteriaName)
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

    private fun setUpToolbar(){
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

    private fun personList(name: String? = null): ArrayList<PersonInfo>{
        val items = ArrayList<PersonInfo>()
        for (i in 1..50){
            items.add(PersonInfo("Xolida Raximova", "12 mavze, 12 do`m, 12 honadon", 23))
        }
        return items
    }

    private fun criteriaList(): ArrayList<String>{
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