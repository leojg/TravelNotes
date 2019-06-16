package lgcode.me.travelnotes.features.main

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.ui.BaseActivity
import lgcode.me.travelnotes.core.ui.BaseFragment
import lgcode.me.travelnotes.databinding.ActivityMainBinding
import lgcode.me.travelnotes.features.note.NoteFragment
import lgcode.me.travelnotes.features.noteslist.NotesListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.jar.Manifest

class MainActivity: BaseActivity() {

    companion object {
        const val SHOW_GALLERY_PERMISSION_REQUEST_CODE = 1
        const val OPEN_CAMERA_PERMISSION_REQUEST_CODE = 2
    }

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(mainBinding.toolbar)

        replaceFragment(NotesListFragment.newInstance(), getFragmentContainer(), false)
    }

    override fun getRoot(): View = mainBinding.root
    override fun getFragmentContainer(): Int = R.id.main_container

    fun replaceFragment(fragment: BaseFragment) {
        replaceFragment(fragment, getFragmentContainer())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun checkGalleryPermissions(): Boolean {
        return checkPermissions(listOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE), SHOW_GALLERY_PERMISSION_REQUEST_CODE)
    }

    fun checkCameraPermissions(): Boolean {
        return checkPermissions(listOf(CAMERA), OPEN_CAMERA_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            SHOW_GALLERY_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    (currentFragment as NoteFragment).showGallery()
                } else {
                    Toast.makeText(this, "W U NO GIB", Toast.LENGTH_LONG).show()
                }
            }
            OPEN_CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    (currentFragment as NoteFragment).openCamera()
                } else {
                    Toast.makeText(this, "W U NO GIB CAM", Toast.LENGTH_LONG).show()
                }
            }

            else -> {
            }
        }
    }

}