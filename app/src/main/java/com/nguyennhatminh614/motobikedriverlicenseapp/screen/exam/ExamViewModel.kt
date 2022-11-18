package com.nguyennhatminh614.motobikedriverlicenseapp.screen.exam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Exam
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.ExamState
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.StateQuestionOption
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.ExamRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.WrongAnswerRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.CountDownInstance
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.constant.AppConstant.FIRST_INDEX
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExamViewModel(
    private val examRepository: ExamRepository,
    private val wrongAnswerRepository: WrongAnswerRepository,
) : BaseViewModel() {

    private val _listExam = MutableLiveData<MutableList<Exam>>()
    val listExam: LiveData<MutableList<Exam>>
        get() = _listExam

    private val _listQuestions = MutableLiveData<MutableList<Questions>>()
    val listQuestions: LiveData<MutableList<Questions>>
        get() = _listQuestions

    private val _currentExamPosition = MutableLiveData<Int>()
    val currentExamPosition: LiveData<Int>
        get() = _currentExamPosition

    private val _currentExamQuestionPosition = MutableLiveData<Int>()
    val currentExamQuestionPosition: LiveData<Int>
        get() = _currentExamQuestionPosition

    private val _currentTimeCountDown = MutableLiveData<String>()
    val currentTimeCountDown: LiveData<String>
        get() = _currentTimeCountDown

    init {
        launchTask {
            _listExam.postValue(examRepository.getAllExam())
            hideLoading()
            examRepository.getListQuestion(
                object : IResponseListener<MutableList<Questions>> {
                    override fun onSuccess(data: MutableList<Questions>) {
                        _listQuestions.postValue(data)
                        hideLoading()
                    }

                    override fun onError(exception: Exception?) {
                        this@ExamViewModel.exception.postValue(exception)
                        hideLoading()
                    }
                }
            )
        }
    }

    fun addExamToDatabase(exam: Exam) {
        launchTask {
            examRepository.insertNewExam(exam)
            val data = examRepository.getAllExam()
            _listExam.postValue(data)
            hideLoading()
        }
    }

    fun startCountDownEvent(onFinishExamEvent: () -> Unit) {
        var lastTimeStamp = _listExam.value?.get(_currentExamPosition.value
            ?: AppConstant.NONE_POSITION)?.currentTimeStamp ?: AppConstant.EXAM_TEST_FULL_TIME

        if (lastTimeStamp == AppConstant.DEFAULT_NOT_HAVE_TIME_STAMP) {
            lastTimeStamp = AppConstant.EXAM_TEST_FULL_TIME
        }

        CountDownInstance.startCountDownFrom(
            lastTimeStamp,
            onTickEvent = {
                _currentTimeCountDown.postValue(CountDownInstance.CurrentTime)
                _listExam.value?.get(_currentExamPosition.value
                    ?: AppConstant.NONE_POSITION)?.currentTimeStamp =
                    CountDownInstance.CurrentTimeStamp
            },
            onFinishEvent = {
                processFinishExamEvent()
                onFinishExamEvent()
            }
        )
    }

    fun processFinishExamEvent() {
        launchTask {
            val currentExam = _listExam.value?.get(_currentExamPosition.value
                ?: AppConstant.NONE_POSITION)

            CountDownInstance.cancelCountDown()

            currentExam?.let { exam ->
                var index = FIRST_INDEX
                var isWrongTheQuestionThatFailedTestImmediately = false

                exam.currentTimeStamp = END_TIME_STAMP

                exam.listQuestionOptions.forEach {
                    if (it.data == exam.listQuestions[index].answer) {
                        exam.listQuestionOptions[index].stateNumber =
                            StateQuestionOption.CORRECT.type
                        exam.numbersOfCorrectAnswer++
                    } else {
                        exam.listQuestionOptions[index].stateNumber =
                            StateQuestionOption.INCORRECT.type

                        if (exam.listQuestions[index].isFailedTestWhenWrong) {
                            isWrongTheQuestionThatFailedTestImmediately = true
                        }

                        wrongAnswerRepository.insertNewWrongAnswerQuestion(
                            WrongAnswerObject(
                                exam.listQuestions[index].id,
                                System.currentTimeMillis(),
                            )
                        )
                    }
                    index++
                }

                if (exam.numbersOfCorrectAnswer <= MINIMUM_CORRECT_QUESTION_TO_PASS_EXAM ||
                    isWrongTheQuestionThatFailedTestImmediately
                ) {
                    exam.examState = ExamState.FAILED.value
                } else {
                    exam.examState = ExamState.PASSED.value
                }

                examRepository.updateExam(exam)
                val data = examRepository.getAllExam()
                _listExam.postValue(data)
            }

            _currentExamQuestionPosition.postValue(FIRST_INDEX)
            _currentExamPosition.postValue(AppConstant.NONE_POSITION)
            _currentExamQuestionPosition.postValue(AppConstant.NONE_POSITION)

            delay(DELAY_ON_FINISH_EXAM)
            hideLoading()
        }
    }

    fun setCurrentExam(index: Int) {
        _currentExamPosition.value = index
    }

    fun getCurrentExam(): Exam? {
        val currentPosition = _currentExamPosition.value

        currentPosition?.let {
            if (currentPosition != AppConstant.NONE_POSITION) {
                return _listExam.value?.get(currentPosition)
            }
        }

        return null
    }

    fun updateStateQuestion(questionsPosition: Int, item: QuestionOptions) {
        _listExam.value?.get(_currentExamPosition.value
            ?: AppConstant.NONE_POSITION)?.listQuestionOptions?.set(questionsPosition, item)
    }

    fun navigateToNextQuestion(currentPosition: Int) {
        val currentExamSize = _listExam.value?.get(_currentExamPosition.value
            ?: AppConstant.NONE_POSITION)?.listQuestionOptions?.size ?: AppConstant.EMPTY_SIZE
        if (currentPosition < currentExamSize) {
            _currentExamQuestionPosition.postValue(currentPosition + 1)
        }
    }

    fun getCurrentExamTimestampLeft() =
        _listExam.value?.get(_currentExamPosition.value
            ?: AppConstant.NONE_POSITION)?.currentTimeStamp
            ?: AppConstant.DEFAULT_NOT_HAVE_TIME_STAMP

    fun getCategoryList(questions: MutableList<Questions>, key: String, takeAmount: Int) =
        questions.filter {
            return@filter it.questionType.lowercase() == key.lowercase()
        }.shuffled().take(takeAmount)

    fun saveCurrentExamState() {
        viewModelScope.launch {
            _listExam.value?.get(_currentExamPosition.value
                ?: AppConstant.NONE_POSITION)?.let {
                examRepository.updateExam(it)
                CountDownInstance.cancelCountDown()
                _currentExamQuestionPosition.postValue(FIRST_INDEX)
                _currentExamPosition.postValue(AppConstant.NONE_POSITION)
                _currentExamQuestionPosition.postValue(AppConstant.NONE_POSITION)
            }
        }
    }

    companion object {
        const val NUMBERS_OF_MUST_NOT_WRONG_ANSWER = 5
        const val NUMBERS_OF_CONCEPT_AND_RULES = 15
        const val NUMBERS_OF_TRAFFIC_SIGNAL = 5
        const val NUMBERS_OF_SITUATION_BY_PICTURE = 0
        const val MINIMUM_CORRECT_QUESTION_TO_PASS_EXAM = 21
        const val DELAY_ON_FINISH_EXAM = 500L
        const val END_TIME_STAMP = 0L
    }
}
