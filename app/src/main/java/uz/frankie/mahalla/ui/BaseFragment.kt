package uz.frankie.mahalla.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import uz.frankie.mahalla.MainActivity
import uz.frankie.mahalla.utils.fragment_toolbar.ToolbarManager
import uz.frankie.mahalla.viewmodels.NeighborhoodVM

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment(){

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var navController: NavController

    val neighborhoodVM: NeighborhoodVM by viewModels()

//    val shared by lazy { SharedPreferencesHelper(context = requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        neighborhoodVM.getNeighborhoodList()
        navController = findNavController()
        onViewCreate()

        ToolbarManager(
            getToolbar(),
            navController,
            (activity as MainActivity).appBarConfiguration
        )

    }

    abstract fun onViewCreate()

    protected abstract fun getToolbar(): Toolbar?

}