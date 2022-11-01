package com.nguyennhatminh614.motobikedriverlicenseapp.screen.mainscreen

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.ActivityMainBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.study.StudyViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.wronganswer.WrongAnswerViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    val studyViewModel by viewModel<StudyViewModel>()
    val wrongAnswerViewModel by viewModel<WrongAnswerViewModel>()

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.nav_main,
            ),
            viewBinding.drawerLayout
        )
    }

    override fun initData() {
        //Not-op
    }

    override fun handleEvent() {
        viewBinding.apply {
            setSupportActionBar(appBarMain.toolbar)
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }
    }

    override fun bindData() {
        // Not-op
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
