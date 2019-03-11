package lgcode.me.travelnotes.core.ui

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    val TAG = this.javaClass.name

    open fun getPreviousFragment(): BaseFragment? {
        return null
    }

    open fun getNextFragment(): BaseFragment? {
        return null
    }
}