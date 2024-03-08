import javax.swing.*

class FormQuiz : JFrame(){
/*
    init{

        runFormQuiz()

    }
*/
    fun runFormQuiz(){

        title = "Harry Potter QUIZ"

        val textForForm: TextForForm = TextForForm()
        val gameplayQuiz: GameplayQuiz = GameplayQuiz()

        val label: JLabel = JLabel(textForForm.descriptionGame)
        val textField: JTextField = JTextField("", 10)
        val buttonAnswerQuiz: JButton = JButton(textForForm.answerProperty)
        val buttonNextQuiz: JButton = JButton(textForForm.nextProperty)
        val buttonNewGameQuiz: JButton = JButton(textForForm.newGameProperty)

        var correctly: Int = 0
        var total: Int = 0
        var result: Boolean = false

        textField.isEditable = false
        buttonAnswerQuiz.isVisible = false
        buttonNextQuiz.isVisible = false

        createLayout(label, textField, buttonAnswerQuiz, buttonNextQuiz, buttonNewGameQuiz)

        buttonAnswerQuiz.addActionListener {

            result = gameplayQuiz.checkUsersResponse(textField.text)

            if (result) {
                correctly++
                textField.text = textForForm.correctly
            } else {
                textField.text = textForForm.wrong
            }
            textField.isEditable = false
            total++
            buttonAnswerQuiz.isVisible = false
            buttonNextQuiz.isVisible = true

        }

        buttonNextQuiz.addActionListener {

            if(total<10){

                gameplayQuiz.selectRandomCollectionItem()
                label.text = gameplayQuiz.getNamePazzleQuestion()
                textField.text = ""
                textField.isEditable = true
                buttonAnswerQuiz.isVisible = true
                buttonNextQuiz.isVisible = false

            }else{

                buttonAnswerQuiz.isVisible = false
                buttonNextQuiz.isVisible = false
                buttonNewGameQuiz.isVisible = true
                var resultsOfGame: String = String.format(textForForm.resultTemplateGame,correctly, total)
                textField.text = resultsOfGame
                label.text = textForForm.descriptionGame

            }

        }

        buttonNewGameQuiz.addActionListener {

            buttonNewGameQuiz.isVisible = false
            gameplayQuiz.selectRandomCollectionItem()
            label.text = gameplayQuiz.getNamePazzleQuestion()
            buttonAnswerQuiz.isVisible = true
            textField.text = ""
            textField.isEditable = true
            correctly = 0
            total = 0

        }

        defaultCloseOperation = EXIT_ON_CLOSE
        setBounds(100, 100, 500, 500)
        isVisible = true
    }

    private fun createLayout(vararg arg: JComponent) {
        val groupLayout = GroupLayout(contentPane)
        contentPane.layout = groupLayout

        groupLayout.autoCreateContainerGaps = true

        groupLayout.setHorizontalGroup(
            groupLayout.createSequentialGroup()
                .addGroup(
                    groupLayout.createParallelGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4])
                )
        )

        groupLayout.setVerticalGroup(
            groupLayout.createSequentialGroup()
                .addComponent(arg[0])
                .addComponent(arg[1])
                .addComponent(arg[2])
                .addComponent(arg[3])
                .addComponent(arg[4])
        )

        pack()
    }

}