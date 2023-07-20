package uz.frankie.mahalla.ui.villages.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentVillageWorkersBinding
import uz.frankie.mahalla.model.Staff

class VillageWorkersScreen : Fragment(R.layout.fragment_village_workers) {
    private val binding by viewBinding(FragmentVillageWorkersBinding::bind)
    var staffList = ArrayList<Staff>()
    var staff = Staff("","", "", "", "", "", "", "", "", "", "",R.drawable.iv_mahalla_raisi)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        createMockStaff()
    }

    private fun initView() {

        binding.apply {
            ivBack.setOnClickListener { findNavController().popBackStack() }
            mvRais.setOnClickListener {
                staff = staffList[0]
                navigateToStaffInfo(staff)
            }

            mvHokimYordamchisi.setOnClickListener {
                staff = staffList[1]
                navigateToStaffInfo(staff)
            }

            mvYoshlarYetakchisi.setOnClickListener {
                staff = staffList[4]
                navigateToStaffInfo(staff)
            }

            mvHuquqTargibot.setOnClickListener {
                staff = staffList[2]
                navigateToStaffInfo(staff)
            }

            mvXotinQizlarFaoli.setOnClickListener {
                staff = staffList[3]
                navigateToStaffInfo(staff)
            }
        }
    }

    fun navigateToStaffInfo(staff: Staff) {
        val action =
            VillageWorkersScreenDirections.actionVillageStaffToVillageWorkerInfoScreen(staff)
        findNavController().navigate(action)
    }

    private fun createMockStaff() {
        val staff1 = Staff(
            "MAXALLA FUQAROLAR YIG‘INI RAISI",
            "YUNUSOVA SAYYORA TOSHKENBOEVNA",
            "Mahalla fuqarolar yig‘ini raisi lavozimida - 22.05.2022 yildan",
            "23.10.1972 ",
            "Namangan viloyati ",
            "O`zbek",
            "Oliy",
            "Sistematexnik muxandis",
            "Beshqo‘rg‘on - 3  27 – uy 22 - xonadon ",
            "93 564-08-72",
            "Oilali",
            R.drawable.iv_mahalla_raisi
        )

        val staff2 = Staff(
            "XOKIM YORDAMCHISI",
            "MUSTAFAEV   YUNUSBEK  MUZAFFAR O‘G‘LI",
            "Xokim yordamchisi  lavozimida –01.01.2020 yildan",
            "25.01.1993  yil ",
            "Navoiy viloyati ",
            "O`zbek",
            "Oliy",
            "Paxtani qayta ishlash",
            "Toshkent shahar Olmazor tumani Sebzor dahasi     44-uy, 138-xonadon ",
            "93 612 24 07",
            "Oilali",
            R.drawable.iv_hokim_yordamchi
        )


        val staff3 = Staff(
            "MAXALLA FUQAROLAR YIG‘INI RAISINING \n" +
                    "xuquq-tartibot masalalari bo‘yicha o‘rinbosari\n",
            "MUSAEV QAXRAMON QURBONOVICH   ",
            "Mahalla fuqarolar yig‘ini profilaktika inspektori lavozimida –15.07.2022   yildan",
            "15.12.1969",
            "Qashqadaryo viloyati ",
            "O`zbek",
            "Oliy",
            "Rus o‘zbek fors tili pedagogika va tarjimon ",
            "CHilonzor  tumani Zarqand tor 24-uy  ",
            "90-932-44-14  ",
            "Oilali",
            R.drawable.iv_inspektor
        )


        val staff4 = Staff(
            "MAXALLA FUQAROLAR YIG‘INI \n" +
                    "Xotin-qizlar faoli\n",
            "ABDULLAEVA GULNOZA MIRDALILOVNA  ",
            "Mahalla fuqarolar yig‘ini raisining o‘rinbosari lavozimida – 06.09.2022 yildan",
            "15.03.1977",
            "Toshkent shahri",
            "O`zbek",
            "o‘rta-maxsus",
            "xisobchi",
            "Toshkent shahri, Olmazor tumani, Beshqo‘rg‘on 1 berk  ko‘chasi -17  uy",
            "97 756-78-97   ",
            "Oilali",
            R.drawable.iv_xotin_qizlar_faoli
        )


        val staff5 = Staff(
            "MAXALLA FUQAROLAR YIG‘INI \n" +
                    "Yоshlar etakchisi \n",
            "MIRAXMEDOV SARDOR MIRODILOVICH ",
            "Yоshlar etakchisi lavozimda - 24.01.2022 yildan",
            "13.10.1988 ",
            "Toshkent shaxar",
            "O`zbek",
            "O‘rta-maxsus",
            "Avtotransport vositalariga texnik xizmat ko‘rsatish",
            "Olmazor tumani Qora qamish 2/4 dahasi, 21-uy 15-xonadon",
            "97  409-94-99",
            "Oilali",
            R.drawable.iv_yoshlar_yetakchisi
        )
        val staff6 = Staff(
            "MAHALLA FUQAROLAR YIG‘INI RAISINING keksalar va faxriylar ishlari bo‘yicha maslahatchisi",
            "Turaboev Djuraboy",
            "Mahalla fuqarolar yig‘ini raisining maslaxatchisi lavozimida – 13.05.2019 yildan",
            "05.08.1951",
            "Toshkent viloyati",
            "O`zbek",
            "Oliy",
            "",
            "Beshqo‘rg‘on-2 10-uy 81-xona ",
            "71 229-83-15",
            "Oilali",
            R.drawable.iv_mahalla_raisi
        )
        staffList.add(staff1)
        staffList.add(staff2)
        staffList.add(staff3)
        staffList.add(staff4)
        staffList.add(staff5)
        staffList.add(staff6)

    }

}