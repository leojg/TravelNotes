package lgcode.me.travelnotes.features.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import lgcode.me.travelnotes.R
import lgcode.me.travelnotes.core.ui.BaseActivity
import lgcode.me.travelnotes.databinding.ActivityMainBinding
import lgcode.me.travelnotes.features.noteslist.NotesListFragment

class MainActivity: BaseActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        replaceFragment(NotesListFragment.newInstance(), getFragmentContainer())
    }

    override fun getRoot(): View = mainBinding.root
    override fun getFragmentContainer(): Int = R.id.main_container


}