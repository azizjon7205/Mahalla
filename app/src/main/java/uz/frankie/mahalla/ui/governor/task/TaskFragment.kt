package uz.frankie.mahalla.ui.governor.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentTaskBinding

class TaskFragment : Fragment(R.layout.fragment_task) {
    private val binding by viewBinding(FragmentTaskBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews() {

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewpager2


        val pagerAdapter = ParentFragmentPagerAdapter(requireActivity(), findNavController())
        viewPager.isUserInputEnabled = false
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager,) { tab, position ->
            val tabNames = listOf("Mahallalarga", "O'zimga")
            tab.text = tabNames[position]
        }.attach()
    }

}
class ParentFragmentPagerAdapter(
    fragmentActivity: FragmentActivity, val navController: NavController
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ToTownScreen(navController)
            1 -> MyselfScreen()
            else -> ToTownScreen(navController)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}
