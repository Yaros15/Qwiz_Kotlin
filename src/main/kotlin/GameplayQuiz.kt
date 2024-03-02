class GameplayQuiz {

    val textForForm: TextForForm = TextForForm()
    val readle: Task = Task()
    var currentPuzzle: Puzzle = Puzzle()

    fun selectRandomCollectionItem (){
        var rand: Int = (Math.random() * readle.riddle.size).toInt()
        currentPuzzle = readle.riddle.get(rand)
    }

    fun getNamePazzleQuestion(): String{
        return currentPuzzle.question
    }

    fun checkUsersResponse (currentAnswer: String): Int {

        if(currentPuzzle.answer == currentAnswer){
            return 1
        }else{
            return 0
        }

    }

}