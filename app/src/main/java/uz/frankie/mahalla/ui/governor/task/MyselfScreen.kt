package uz.frankie.mahalla.ui.governor.task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.adapter.governor.MyselfAdapter
import uz.frankie.mahalla.data.entity.MyselfData
import uz.frankie.mahalla.databinding.ScreenMyselfBinding
import uz.frankie.mahalla.utils.MySelfViewModel
import uz.frankie.mahalla.utils.MyselfDialog
import uz.frankie.mahalla.utils.UiStateList
import uz.frankie.mahalla.utils.UiStateObject

@AndroidEntryPoint
class MyselfScreen : Fragment(R.layout.screen_myself) {
    private val mySelfViewModel: MySelfViewModel by viewModels()
    private val myselfAdapter by lazy { MyselfAdapter() }
    private lateinit var itemList: MutableList<MyselfData> // replace Item with your data class
    private val binding by viewBinding(ScreenMyselfBinding::bind)

    private val myselfDialog by lazy { MyselfDialog(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mySelfViewModel.getListMySelfData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mySelfViewModel.listMySelfDataState.collect { mySelfListState ->
                when (mySelfListState) {
                    is UiStateList.SUCCESS -> {
                        myselfAdapter.submitData(mySelfListState.data)
                        binding.rvMyself.adapter = myselfAdapter
                        if(mySelfListState.data.isNotEmpty()){
                            binding.apply {
                            rvMyself.visibility = View.VISIBLE
                            ivEmpty.visibility = View.GONE
                            tvInfo.visibility = View.GONE
                            }
                        }
                    }

                    is UiStateList.EMPTY -> {

                    }

                    is UiStateList.ERROR -> {

                    }

                    is UiStateList.LOADING -> {

                    }
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mySelfViewModel.insertMyDataState.collect { mySelfListState ->
                when (mySelfListState) {
                    is UiStateObject.SUCCESS -> {
                    mySelfViewModel.getListMySelfData()
                    }

                    is UiStateObject.EMPTY -> {

                    }

                    is UiStateObject.ERROR -> {

                    }

                    is UiStateObject.LOADING -> {

                    }
                }
            }
        }
    }


    private fun initView() {
        myselfDialog.setOnCreateTaskListener { myselfData ->
            mySelfViewModel.insertMyData(myselfData)

        }

        binding.apply {
            floatingBtn.setOnClickListener {
                tvInfo.visibility = View.GONE
                ivEmpty.visibility = View.GONE
                rvMyself.visibility = View.VISIBLE
                myselfDialog.show()
            }
        }
    }
}