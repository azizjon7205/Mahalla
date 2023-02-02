package uz.frankie.mahalla.ui.governer_assistant.main

import uz.frankie.mahalla.databinding.FragmentGovernorAssistantBinding
import uz.frankie.mahalla.ui.adapter.governor_assistant.PersonInfo
import uz.frankie.mahalla.ui.adapter.governor_assistant.PersonInfoAdapter
import uz.frankie.mahalla.ui.governer_assistant.BaseGovernorAssistantFragment

class GovernorAssistantFragment : BaseGovernorAssistantFragment<FragmentGovernorAssistantBinding>(
    FragmentGovernorAssistantBinding::inflate
) {

    private val personInfoAdapter by lazy { PersonInfoAdapter() }
    override fun onViewCreate() {
        personInfoAdapter.setData(personList())
        binding.rvPersons.adapter = personInfoAdapter
    }

    private fun personList(): ArrayList<PersonInfo>{
        val items = ArrayList<PersonInfo>()
        for (i in 1..50){
            items.add(PersonInfo("Xolida Raximova", "12 mavze, 12 do`m, 12 honadon", 23))
        }
        return items
    }

}