package uz.frankie.mahalla.ui.governor_assistant.main

import androidx.appcompat.widget.Toolbar
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentGovernorAssistantBinding
import uz.frankie.mahalla.adapter.governor_assistant.PersonInfo
import uz.frankie.mahalla.adapter.governor_assistant.PersonInfoAdapter
import uz.frankie.mahalla.dialogs.governor_assistant.CriteriaDialog
import uz.frankie.mahalla.ui.governor_assistant.BaseGovernorAssistantFragment
import uz.frankie.mahalla.utils.extentions.navigateSafely

class GovernorAssistantFragment : BaseGovernorAssistantFragment<FragmentGovernorAssistantBinding>(
    FragmentGovernorAssistantBinding::inflate
) {

    private val personInfoAdapter by lazy { PersonInfoAdapter() }
    override fun onViewCreate() {
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

            ivBack.setOnClickListener {
                navController.popBackStack()
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

    override fun getToolbar(): Toolbar? = null

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