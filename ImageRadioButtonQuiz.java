import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ImageRadioButtonQuiz extends JFrame {
    private int currentIndex = 0;


    private final String[] imagePaths = {


        "silver-tabby-cat-sitting-on-green-background-free-photo.jpg",
        "dog-animal_DOTORLBDD7.jpg",
        "1_YKK8Yyg2T0jSwypriUyUvw.jpg",
        "main_1500.jpg",
        "94e7276aa7b286b70ee5279295617484.jpg"



    };

    private final int[] correctAnswers = {1, 0, 2, 3, 1};



    private final String[][] labels = {
        {"Owl", "Cat", "Owl","Golden Eagle","Monkey"},
        {"Dog","Monkey","Owl", "Golden Eagle","Cat"},
        {"Cat","Monkey", "Golden Eagle", "Owl","Dog"},
        {"Monkey", "Cat", "Golden Eagle", "Owl","Dog"},
        {"Monkey", "Cat", "Owl","Golden Eagle", "Dog"},
    };

    private JLabel imageLabel;
    private JRadioButton[] radioButtons;
    private JLabel selectionMessageLabel;

    public ImageRadioButtonQuiz(){
        setTitle("Image Radio Button Quiz");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());



        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(5, 1));

        radioButtons = new JRadioButton[5];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < radioButtons.length; i++){
            radioButtons[i] = new JRadioButton();
            group.add(radioButtons[i]);
            radioPanel.add(radioButtons[i]);
            radioButtons[i].addActionListener(new RadioButtonListener() );
        }

        add(radioPanel, BorderLayout.WEST);

       
        selectionMessageLabel = new JLabel("You selected : ");
        selectionMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(selectionMessageLabel, BorderLayout.SOUTH);

        loadNextImage();

        setVisible(true);
    }

    private void loadNextImage() {
        if (currentIndex < imagePaths.length) {
            for (JRadioButton radioButton: radioButtons){
                radioButton.setSelected(false);
            }

            for (int i =0; i <  radioButtons.length; i++) {
                radioButtons[i].setText(labels[currentIndex][i]);
            }

            ImageIcon imageIcon = new ImageIcon(imagePaths[currentIndex]);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(216, 216, Image.SCALE_SMOOTH); // 3 inches = 72 * 3 pixels
            imageIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(imageIcon);
            selectionMessageLabel.setText("You selected: ");  
        } else {
            JOptionPane.showMessageDialog(this, "Quiz completed!");
            System.exit(0);
        }
    }

    private class RadioButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton source = (JRadioButton) e.getSource();
            int selectedIndex = -1;
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i] == source) {
                    selectedIndex = i;
                    break;
                }
            }

            if (selectedIndex != -1){
                selectionMessageLabel.setText("You selected: " + radioButtons[selectedIndex].getText());
            }

            if (selectedIndex ==correctAnswers[currentIndex]) {
                currentIndex++;
                loadNextImage();
            }
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new ImageRadioButtonQuiz() ) ;
    }
}
