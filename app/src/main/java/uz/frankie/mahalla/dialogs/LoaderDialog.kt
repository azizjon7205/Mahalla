package uz.frankie.mahalla.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import uz.frankie.mahalla.databinding.DialogLoaderBinding

class LoaderDialog(context: Context) : AlertDialog(context) {
    val binding = DialogLoaderBinding.inflate(LayoutInflater.from(context))

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setView(binding.root)
    }
}