import javax.swing.*;
import java.awt.*;

public class FormQuiz extends JFrame{

    private JLabel label;
    private JTextField textField;
    private JButton buttonAnswer, buttonNext, buttonNewGame;
    private TextForForm textForForm = new TextForForm();
    private GameplayQuiz gameplayQuiz = new GameplayQuiz();

    //private String question, answer;
    private int resu, correctly, total;

    public FormQuiz() {
        super("Harry_Potter");
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void runFormQuiz(){

        label = new JLabel(textForForm.getDescriptionGame());
        textField = new JTextField("", 10);
        buttonAnswer = new JButton(textForForm.getAnswerProperty());
        buttonNext = new JButton(textForForm.getNextProperty());
        buttonNewGame = new JButton(textForForm.getNewGameProperty());
        buttonAnswer.setVisible(false);
        buttonNext.setVisible(false);
        setLayout(new GridLayout(5, 1, 2, 2));

        add(label);
        add(textField);
        add(buttonAnswer);
        add(buttonNext);
        add(buttonNewGame);

        buttonAnswer.addActionListener(e -> {

            resu = gameplayQuiz.checkUsersResponse(textField.getText());

            if (resu == 1) {
                correctly++;
                label.setText(textForForm.getCorrectly());
            } else {
                label.setText(textForForm.getWrong());
            }
            total++;
            buttonAnswer.setVisible(false);
            buttonNext.setVisible(true);
        });

        buttonNext.addActionListener(e -> {
            if (total < 10) {
                gameplayQuiz.selectRandomCollectionItem();
                label.setText(gameplayQuiz.getNamePazzleQuestion());
                textField.setText(null);
                buttonAnswer.setVisible(true);
                buttonNext.setVisible(false);
            } else {
                buttonAnswer.setVisible(false);
                buttonNext.setVisible(false);
                buttonNewGame.setVisible(true);
                String result = String.format(textForForm.getResultTemplateGame(),correctly, total);
                textField.setText(result);
                label.setText(textForForm.getDescriptionGame());
            }
        });

        buttonNewGame.addActionListener(e -> {
            //riddle = new Task();
            buttonNewGame.setVisible(false);
            gameplayQuiz.selectRandomCollectionItem();
            //riddle.removeRiddle(rand);
            label.setText(gameplayQuiz.getNamePazzleQuestion());
            buttonAnswer.setVisible(true);
            textField.setText(null);
            correctly = 0;
            total = 0;
        });

        this.setVisible(true);

    }

}
