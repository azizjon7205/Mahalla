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
        AllInfoDataModel( "-0-2 yosh", "209", "113", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-3-6 yosh", "389", "207", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-7-13 yosh", "553", "286", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-14-17 yosh", "317", "168", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-18-30 yosh", "996", "528", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Jami 0-30 yoshlilar", "2464", "1134", emptyList(), AllInfoType.THREE_INFO_FIRST_BOLD),
        AllInfoDataModel( "-31-45 yosh", "798", "427", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-46-60 yosh", "786", "428", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-61-70 yosh", "392", "264", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-71-80 yosh", "58", "37", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-81-90 yosh", "7", "5", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-91-100 yosh", "2", "0", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "-100 yoshdan kattalar", "0", "0", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Aholining milliy tarkibi", "", "", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "o‘zbek", "3526", "1856", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "qoraqalpoq", "26", "11", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "rus", "224", "132", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Pensionerlar soni", "721", "387", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "shundan huquqni muhofaza qiluvchi organlardan", "5", "1", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Fuqaroligi bo‘lmagan shaxslar soni", "18", "7", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Fuqarolar yig‘inida ro‘yxatda turib, hozirgi kunda yashamayotganlar soni", "38", "21", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Vaqtincha ro‘yxatdan o‘tib yashayotganlar soni", "68", "29", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Fuqarolar yig‘inida ro‘yxatdan o‘tmasdan yashayotganlar soni", "0", "0", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Ijara shartnomasi asosida yashayotganlar soni", "68", "29", emptyList(), AllInfoType.THREE_INFO),
        AllInfoDataModel( "Aholining ijtimoiy holati", "", "", emptyList(), AllInfoType.TITLE),
        AllInfoDataModel( "Boquvchisini yo‘qotgan oilalar soni", "19", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Yоlg‘iz onalar/otalar soni", "46", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Yolg`iz keksalar soni", "0", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Kam ta’minlangan oilalar soni", "51", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "2 yoshgacha nafaqa oluvchi oilalar soni", "14", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "14 yoshgacha nafaqa oluvchi oilalar soni", "25", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "moddiy yordam oluvchilar soni", "12", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Ko‘p bolali oilalar soni", "10", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Nogironligi bo‘lgan shaxslar soni", "50", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "", "", "", listOf(
            TwoInfoDataModel("1-guruh","2"),
            TwoInfoDataModel("2-guruh","41"),
            TwoInfoDataModel("3-guruh","7")
        ), AllInfoType.SUB_TWO_INFO),
        AllInfoDataModel( "Nogironlik nafaqasi oluvchilar soni", "43", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Og‘ir sharoitga tushib qolgan ayollar soni", "0", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Ikkinchi jahon urushi davrida front va front ortida qatnashgan hamda ularga tenglashtirilgan fuqarolar soni", "1", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Baynalminal jangchilar va CHernobl AES halokatini bartaraf etishda ishtirok etganlar soni", "6", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Aholi bandligi", "", "", emptyList(), AllInfoType.TITLE),
        AllInfoDataModel( "Korxona va tashkilotlarda ishlovchilar soni", "598", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Tadbirkorlik bilan band bo‘lganlar", "256", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "", "", "", listOf(
            TwoInfoDataModel("kasanachilik bilan band bo‘lganlar soni","0"),
            TwoInfoDataModel("milliy hunarmandchilik bilan shug‘ullanuvchilar soni","5"),
            TwoInfoDataModel("savdo-sotiq bilan shug‘ullanuvchilar soni","175"),
            TwoInfoDataModel("chorvachilik, parrandachilik va asalarichilik bilan band bo‘lganlar soni","0"),
            TwoInfoDataModel("tadbirkorlikning boshqa soxalarida band bo‘lganlar soni","41")
        ), AllInfoType.SUB_TWO_INFO),
        AllInfoDataModel( "Mavsumiy ishlar bilan band bo‘lganlar soni", "132", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Uzoq muddat xorijiy davlatlarga ish izlab ketganlar coni", "38", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Bola parvarishi bilan band bo‘lganlar soni", "186", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Talabalar soni", "25", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Ishlovchi pensionerlar soni", "58", "", emptyList(), AllInfoType.TWO_INFO),
        AllInfoDataModel( "Ishsizlar soni", "13", "", emptyList(), AllInfoType.TWO_INFO),
    )

}