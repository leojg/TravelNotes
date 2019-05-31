package lgcode.me.travelnotes.core.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity: AppCompatActivity() {

    lateinit var currentFragment: BaseFragment

    fun replaceFragment(newFragment: BaseFragment, @IdRes container: Int, addToBackStack: Boolean = true) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(container, newFragment as Fragment)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(newFragment.TAG)
        }
        fragmentTransaction.commit()
        currentFragment = newFragment
    }

    override fun onBackPressed() {
        currentFragment.getPreviousFragment()?.let {
            replaceFragment(it, getFragmentContainer())
        } ?: run {
            super.onBackPressed()
        }
    }

    fun showMessage(message: String) {
        Snackbar.make(getRoot(), message, Snackbar.LENGTH_LONG).show()
    }

    fun checkPermissions(permissions: List<String>, requestCode: Int): Boolean {
        val missingPermissions = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                missingPermissions.add(permission)
            }
        }
        if (missingPermissions.isEmpty()) {
            return true
        } else {
            ActivityCompat.requestPermissions(this,
                missingPermissions.toTypedArray(), requestCode)

            return false
        }
    }

    abstract fun getRoot(): View

    abstract fun getFragmentContainer(): Int


}