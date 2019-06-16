package lgcode.me.travelnotes.features.note

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.databinding.DialogPictureSourceBinding
import lgcode.me.travelnotes.features.main.MainActivity
import java.lang.IllegalStateException

class NotePictureSourceDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(activity)

            val binding = DialogPictureSourceBinding.inflate(LayoutInflater.from(activity))
            binding.openCameraButton.setOnClickListener {
                (targetFragment as NoteFragment).onPictureDialogResponse(1)
                dismiss()
            }
            binding.openGalleryButton.setOnClickListener {
                (targetFragment as NoteFragment).onPictureDialogResponse(2)
                dismiss()
            }
            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException()
    }

}