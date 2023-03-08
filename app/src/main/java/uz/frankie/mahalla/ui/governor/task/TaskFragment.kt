package uz.frankie.mahalla.ui.governor.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout.Tab
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.FragmentTaskBinding

class TaskFragment : Fragment(R.layout.fragment_task) {
    private val binding by viewBinding(FragmentTaskBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.apply {

            val tab = tabLayout.getTabAt(0)

        }
    }


}