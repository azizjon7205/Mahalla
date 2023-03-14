package uz.frankie.mahalla.ui.governor.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.frankie.mahalla.R
import uz.frankie.mahalla.databinding.ScreenMyselfBinding

class MyselfScreen:Fragment(R.layout.screen_myself) {

    private val binding by viewBinding(ScreenMyselfBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

    }
}