package lgcode.me.travelnotes.core.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.lgcode.balance.core.repository.Result

abstract class BaseViewModel: ViewModel() {
    val TAG = this.javaClass.canonicalName

    val error = MutableLiveData<String>()

    fun handleError(failure: Result.Failure) {
        val e = failure.exception
        Log.d(TAG, e.message)
        error.value = e.message
    }
}