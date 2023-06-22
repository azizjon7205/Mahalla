package uz.frankie.mahalla.ui.villages.details

import androidx.appcompat.widget.Toolbar
import uz.frankie.mahalla.adapter.village.AllInfoType
import uz.frankie.mahalla.adapter.village.VillageAllInfoAdapter
import uz.frankie.mahalla.databinding.FragmentVillageAllInformationBinding
import uz.frankie.mahalla.model.village_all_info.AllInfoDataModel
import uz.frankie.mahalla.model.village_all_info.TwoInfoDataModel
import uz.frankie.mahalla.ui.villages.BaseVillagesFragment


class VillageAllInformationFragment : BaseVillagesFragment<FragmentVillageAllInformationBinding>(
    FragmentVillageAllInformationBinding:: inflate
) {

    private val allInfoAdapter by lazy { VillageAllInfoAdapter() }

    override fun onViewCreate() {
        initViews()
        collectUiState()
    }

    private fun initViews(){
        setUpToolbar()
        allInfoAdapter.setData(getAllInfo())
        binding.rvAllInfo.adapter = allInfoAdapter
    }

    private fun collectUiState(){

    }

    private fun setUpToolbar(){
        binding.toolbarLayout.apply {
            title.isSelected = true
            title.text = "Mahalla fuqarolar yig‘ini haqida umumiy ma’lumot"
        }
    }

    override fun getToolbar(): Toolbar = binding.toolbarLayout.toolbarFragment


    private fun getAllInfo(): List<AllInfoDataModel> = listOf(
        AllInfoDataModel( "Umumiy er maydoni:", "8,9-GA", "", emptyList(), AllInfoType.TWO_INFO_FIRST_BOLD_HEADER),
        AllInfoDataModel( "Tarkibidagi aholi punktlari nomi:", "1", "", emptyList(), AllInfoType.TWO_INFO_FIRST_BOLD),
        AllInfoDataModel( "Tarkibidagi ko‘chalar soni:", "2", "", emptyList(), AllInfoType.TWO_INFO_GREEN),
        AllInfoDataModel( "", "", "", listOf(
            TwoInfoDataModel("To‘g‘ri ko‘chalar", "2"),
            TwoInfoDataModel("tor ko‘chalar soni", "0"),
            TwoInfoDataModel("tupik (berk) ko‘chalar", "0"),
            TwoInfoDataModel("shox ko‘chalar soni", "0"),
        ), AllInfoType.SUB_TWO_INFO),
        AllInfoDataModel( "Tarkibidagi ko‘p qavatli uylar soni", "14", "", emptyList(), AllInfoType.TWO_INFO_FIRST_BOLD),
        AllInfoDataModel( "Tarkibidagi yakka tartibdagi hovlilar soni", "0", "", emptyList(), AllInfoType.TWO_INFO_FIRST_BOLD),
        AllInfoDataModel( "Tarkibidagi aholi doimiy yashamaydigan xonadonlar (kvartiralar) soni", "0", "", emptyList(), AllInfoType.TWO_INFO_FIRST_BOLD),
        AllInfoDataModel( "Xo‘jaliklar soni", "0", "", emptyList(), AllInfoType.TWO_INFO_FIRST_BOLD),
        AllInfoDataModel( "Xonadonlar soni", "1131", "", emptyList(), AllInfoType.TWO_INFO_GREEN),
        AllInfoDataModel( "", "", "", listOf(
            TwoInfoDataModel("yakka tartibdagi hovlilardagi", "0"),
            TwoInfoDataModel("ko‘p qavatli uylardagi", "1131"),
        ), AllInfoType.SUB_TWO_INFO),
        AllInfoDataModel( "Oilalar soni", "1355", "", emptyList(), AllInfoType.TWO_INFO_GREEN),
        AllInfoDataModel( "", "", "", listOf(
            TwoInfoDataModel("yakka tartibdagi hovlilardagi", "0"),
            TwoInfoDataModel("ko‘p qavatli uylardagi", "1355"),
        ), AllInfoType.SUB_TWO_INFO),
        AllInfoDataModel( "Mahallalar bilan chegaradoshligi (Qaysi mahalla bilan qancha masofada chegaradosh)", "Olimpiya,CHamanbog‘,Mustaqil yurt", "", emptyList(), AllInfoType.TWO_INFO_FIRST_BOLD),
        AllInfoDataModel( "Maxallaning Demografik ko‘rsatkichlar  04.01.2023y xolati bo‘yicha", "", "", emptyList(), AllInfoType.TITLE),
        AllInfoDataModel( "Aholi soni", "4561", "2994", emptyList(), AllInfoType.THREE_INFO_FIRST_BOLD),
        AllInfoDataModel( "SHu jumladan:", "3981", "1963", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "- 0 - 2 yosh", "209", "113", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "- 3 - 6 yosh", "389", "207", emptyList(), AllInfoType.THREE_INFO),
    )

}