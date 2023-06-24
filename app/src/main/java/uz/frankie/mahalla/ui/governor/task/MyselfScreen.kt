package uz.frankie.mahalla.ui.governor.task

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.frankie.mahalla.R
import uz.frankie.mahalla.adapter.governor.MyselfAdapter
import uz.frankie.mahalla.data.entity.MyselfData
import uz.frankie.mahalla.databinding.ScreenMyselfBinding
import uz.frankie.mahalla.utils.MySelfViewModel
import uz.frankie.mahalla.utils.MyselfDialog
import uz.frankie.mahalla.utils.UiStateList
import uz.frankie.mahalla.utils.UiStateObject
import java.sql.Time

@AndroidEntryPoint
class MyselfScreen : Fragment(R.layout.screen_myself) {
    private val mySelfViewModel: MySelfViewModel by viewModels()
    private val myselfAdapter by lazy { MyselfAdapter() }
    private val myselData by lazy { MyselfData(-1,"","","") }
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
            mySelfViewModel.insertMyDataState.collect { insertedMySelfData ->
                when (insertedMySelfData) {
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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mySelfViewModel.updateMyDataState.collect { updatedMyData ->
                when (updatedMyData) {
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
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        mySelfViewModel.deleteMyDataState.collect { deleteMyData ->
            when (deleteMyData) {
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
        myselfDialog.setOnSaveTaskListener { myselfData ->
            Snackbar.make(requireView(),"Muvaffaqiyatli qo`shildi",Snackbar.LENGTH_LONG).show()
            mySelfViewModel.insertMyData(myselfData)
        }

        binding.apply {
            floatingBtn.setOnClickListener {
                tvInfo.visibility = View.GONE
                ivEmpty.visibility = View.GONE
                rvMyself.visibility = View.VISIBLE
                showCreateDialog()
            }
        }

        myselfAdapter.apply {
            itemClicked{data,view->
            myselfAdapter.showPopupMenu(requireActivity(),view,data)
            }
            optionsClicked {
                Log.d("optionsClicked", "initView: ${it}")
                showEditDialog(it)
            }
            deleteClicked {
                mySelfViewModel.deleteMyData(it)
            }
        }


    }


    private fun showEditDialog(item: MyselfData?) {
        val myselfDialogEdit = MyselfDialog(requireContext(), item)
        myselfDialogEdit.setOnSaveTaskListener { updatedItem ->
            mySelfViewModel.updateMyData(updatedItem)
        }
        myselfDialogEdit.show()
    }

    private fun showCreateDialog() {
        val myselfDialogCreate = MyselfDialog(requireContext(), null)
        myselfDialogCreate.setOnSaveTaskListener {
            mySelfViewModel.insertMyData(it)
        }
        myselfDialogCreate.show()
    }

}

