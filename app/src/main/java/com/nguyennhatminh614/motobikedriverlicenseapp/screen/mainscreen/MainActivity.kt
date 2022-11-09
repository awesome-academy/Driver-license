package com.nguyennhatminh614.motobikedriverlicenseapp.screen.mainscreen

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nguyennhatminh614.motobikedriverlicenseapp.R
import com.nguyennhatminh614.motobikedriverlicenseapp.databinding.ActivityMainBinding
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.exam.ExamViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.settings.SettingsViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.study.StudyViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.screen.wronganswer.WrongAnswerViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseActivity
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    val studyViewModel by viewModel<StudyViewModel>()
    val wrongAnswerViewModel by viewModel<WrongAnswerViewModel>()
    val examViewModel by viewModel<ExamViewModel>()
    val settingsViewModel by viewModel<SettingsViewModel>()

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.nav_main,
                R.id.nav_exam,
                R.id.nav_study,
                R.id.nav_traffic_sign,
                R.id.nav_tips_high_score,
                R.id.nav_wrong_answer,
                R.id.nav_settings,
            ),
            viewBinding.drawerLayout
        )
    }

    override fun initData() {
        settingsViewModel.getDarkModeStateFromSharedPreferences()
    }

    override fun handleEvent() {
        viewBinding.apply {
            setSupportActionBar(appBarMain.toolbar)
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)

            appBarMain.buttonFinishExam.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle(FINISH_EXAM_DIALOG_TITLE)
                    .setMessage(FINISH_EXAM_DIALOG_MESSAGE)
                    .setCancelable(false)
                    .setPositiveButton(FINISH_EXAM_YES_BUTTON) { _, _ ->
                        examViewModel.processFinishExamEvent()
                        findNavController(R.id.nav_host_fragment_content_main).navigateUp()
                    }
                    .setNegativeButton(FINISH_EXAM_NO_BUTTON) { _, _ ->
                        //Not-op
                    }

                val dialog = builder.create()
                dialog.window?.attributes = WindowManager.LayoutParams().apply {
                    width = WindowManager.LayoutParams.MATCH_PARENT
                    height = WindowManager.LayoutParams.MATCH_PARENT
                }
                dialog.show()
            }
        }
    }

    override fun bindData() {
        examViewModel.isVisibleFinishExamButton.observe(this) {
            viewBinding.appBarMain.buttonFinishExam.isVisible = it
        }

        settingsViewModel.isDarkModeOn.observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onPause() {
        settingsViewModel.saveDarkModeState()
        super.onPause()
    }

    companion object {
        private const val FINISH_EXAM_DIALOG_TITLE = "Thông báo"
        private const val FINISH_EXAM_DIALOG_MESSAGE = "Bạn có muốn kết thúc bài thi này không?"
        private const val FINISH_EXAM_YES_BUTTON = "Có"
        private const val FINISH_EXAM_NO_BUTTON = "Không"
    }
}
