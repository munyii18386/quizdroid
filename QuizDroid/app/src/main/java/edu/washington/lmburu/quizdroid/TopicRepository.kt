package edu.washington.lmburu.quizdroid

interface TopicRepository {
    companion object{
        var quizManager = QuizApp.newInstance()
    }


    fun setSubject(selectedSubject: String){
        quizManager.setSubject(selectedSubject)
    }

    fun getSubject(): String{
        return quizManager.sub
    }

    //get list of questions
    fun getQuizOptions() = quizManager.quizList

    //get desire topic summary
    fun topicOverview(subject: String): String{
        return quizManager.topicOverview(subject)
    }

    fun setIndex(index: Int){
        quizManager.setIndex(index)
    }
    fun getIndex(): Int{
        return quizManager.getIndex()
    }

    fun setTotalTally(num:Int){
        quizManager.setTotalTally(num)
    }

    fun getTotalTally():Int{
        return quizManager.getTotalTally()
    }

    fun getUserCheckedAnswer():String{
        return quizManager.getUserCheckedAnswer()
    }

    fun setUserCheckedAnswer(ans: String){
        quizManager.setUserCheckedAnswer(ans)
    }

    //get quiz list size
    fun quizSize(subject: String): Int {
        return quizManager.quizSize(subject)
    }

    //get list of questions per subject
    fun getQuestion(subject: String, index: Int): String {
        return quizManager.getQuestion(subject, index)
    }
    // get list of answers per question
    fun getAnswer(subject: String, index: Int): List<String> {
      return quizManager.getAnswer(subject, index)
    }

    //get the correct answer for corresponding question
    fun correctAnswer(subject: String, index: Int): String {
        return quizManager.correctAnswer(subject, index)
    }


}