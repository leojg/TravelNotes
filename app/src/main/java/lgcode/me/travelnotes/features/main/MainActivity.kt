package lgcode.me.travelnotes.features.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.ui.BaseActivity
import lgcode.me.travelnotes.core.ui.BaseFragment
import lgcode.me.travelnotes.databinding.ActivityMainBinding
import lgcode.me.travelnotes.features.noteslist.NotesListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: BaseActivity() {

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

}