package lgcode.me.travelnotes.core.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity: AppCompatActivity() {

    lateinit var currentFragment: BaseFragment

    fun replaceFragment(newFragment: BaseFragment, @IdRes container: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(container, newFragment as Fragment).commit()
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

    abstract fun getRoot(): View

    abstract fun getFragmentContainer(): Int


}