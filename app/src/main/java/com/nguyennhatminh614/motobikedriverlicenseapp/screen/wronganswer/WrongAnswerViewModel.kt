package com.nguyennhatminh614.motobikedriverlicenseapp.screen.wronganswer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.QuestionOptions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.Questions
import com.nguyennhatminh614.motobikedriverlicenseapp.data.model.WrongAnswerObject
import com.nguyennhatminh614.motobikedriverlicenseapp.data.repository.WrongAnswerRepository
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.base.BaseViewModel
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.generateEmptyQuestionStateList
import com.nguyennhatminh614.motobikedriverlicenseapp.utils.interfaces.IResponseListener

class WrongAnswerViewModel(
    private val wrongAnswerRepository: WrongAnswerRepository,
) : BaseViewModel() {

    private val _listAllQuestion = MutableLiveData<MutableList<Questions>>()

    private val _listWrongAnswerQuestion = MutableLiveData<MutableList<Questions>>()
    val listWrongAnswerQuestion: LiveData<MutableList<Questions>>
        get() = _listWrongAnswerQuestion

    private val _listWrongAnswer = MutableLiveData<MutableList<WrongAnswerObject>>()
    val listWrongAnswer: LiveData<MutableList<WrongAnswerObject>>
        get() = _listWrongAnswer

    private val _listQuestionState = MutableLiveData<MutableList<QuestionOptions>>()
    val listQuestionOptions: LiveData<MutableList<QuestionOptions>>
        get() = _listQuestionState

    init {
        fetchData()
    }

    private fun fetchData() {
        launchTask {
            val data = wrongAnswerRepository.getAllWrongAnswerQuestion()
            if (data.isNotEmpty()) {
                _listWrongAnswer.postValue(data)
                wrongAnswerRepository.getAllListQuestion(
                    object : IResponseListener<MutableList<Questions>> {
                        override fun onSuccess(data: MutableList<Questions>) {
                            val wrongAnswerList = mutableListOf<Questions>()
                            _listAllQuestion.postValue(data)
                            _listWrongAnswer.value?.forEach { wrongAnswerQuestion ->
                                data.forEach {
                                    if (wrongAnswerQuestion.questionsID == it.id) {
                                        wrongAnswerList.add(it)
                                        return@forEach
                                    }
                                }
                            }

                            _listWrongAnswerQuestion.postValue(wrongAnswerList)
                            _listQuestionState.postValue(generateEmptyQuestionStateList(
                                wrongAnswerList.size))
                            hideLoading()
                        }

                        override fun onError(exception: Exception?) {
                            this@WrongAnswerViewModel.exception.postValue(exception)
                            hideLoading()
                        }
                    }
                )
            } else {
                val exception = Exception(EMPTY_DATA_EXCEPTION)
                this@WrongAnswerViewModel.exception.postValue(exception)
                hideLoading()
            }

            hideLoading()
        }
    }

    fun updateNewDataFromDatabase(listener: IResponseListener<Boolean>) {
        launchTask {
            val data = wrongAnswerRepository.getAllWrongAnswerQuestion()
            _listWrongAnswer.postValue(data)

            val currentListQuestion = _listAllQuestion.value ?: mutableListOf()
            val newList = mutableListOf<Questions>()

            currentListQuestion.forEach { question ->
                data.forEach {
                    if(it.questionsID == question.id) {
                        newList.add(question)
                        return@forEach
                    }
                }
            }

            _listWrongAnswerQuestion.postValue(newList)
            _listQuestionState.postValue(generateEmptyQuestionStateList(newList.size))
            hideLoading()

            listener.onSuccess(true)
        }
    }

    fun updateDataQuestionPos(questionsPosition: Int, item: QuestionOptions) {
        val list = _listQuestionState.value as? MutableList
        list?.set(questionsPosition, item)
        _listQuestionState.value = list ?: mutableListOf()
    }

    fun getQuestionOptionSelectedByQuestionPosition(questionsPosition: Int): QuestionOptions? {
        return _listQuestionState.value?.get(questionsPosition)
    }

    companion object {
        private const val EMPTY_DATA_EXCEPTION = "Không có dữ liệu!!"
    }
}
