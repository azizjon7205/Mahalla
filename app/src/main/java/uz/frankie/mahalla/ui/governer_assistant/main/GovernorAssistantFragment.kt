package uz.frankie.mahalla.ui.governer_assistant.main

import uz.frankie.mahalla.databinding.FragmentGovernorAssistantBinding
import uz.frankie.mahalla.ui.adapter.governor_assistant.PersonInfo
import uz.frankie.mahalla.ui.adapter.governor_assistant.PersonInfoAdapter
import uz.frankie.mahalla.ui.dialogs.governor_assistant.CriteriaDialog
import uz.frankie.mahalla.ui.governer_assistant.BaseGovernorAssistantFragment

class GovernorAssistantFragment : BaseGovernorAssistantFragment<FragmentGovernorAssistantBinding>(
    FragmentGovernorAssistantBinding::inflate
) {

    private val personInfoAdapter by lazy { PersonInfoAdapter() }
    override fun onViewCreate() {
        personInfoAdapter.setData(personList())
        binding.rvPersons.adapter = personInfoAdapter

        binding.ivFilter.setOnClickListener {
            CriteriaDialog(requireContext(), criteriaList()).apply {
                clickSelectCriteria { criteriaName ->
                    personList(criteriaName)
                }
            }.show()
        }
    }

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