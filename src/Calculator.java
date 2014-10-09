import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on October/10/14.
 */
public class Calculator {

    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton buttondot = new JButton(".");
    JButton buttoneq = new JButton("=");
    JButton buttonplus = new JButton("+");
    JButton buttonminus = new JButton("-");
    JButton buttondivide = new JButton("/");
    JButton buttonmultiply = new JButton("X");
    JLabel display = new JLabel("0");

    static int num1 = 0;
    static StringBuffer num2 = new StringBuffer();
    static char operator = 's';
    static char reset = 'f';// flag used if, after displaying a result, the user clicks a number, a new calculation starts instead of appending the new number to the result. Everytime '=' is clicked, flag is set to 't', to reset

    private void buildGUI(){

        JFrame frame = new JFrame("Calculator");
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container p = frame.getContentPane();
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        p.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridy = 1;
        p.add(button1, c);
        p.add(button2, c);
        p.add(button3, c);
        p.add(buttondivide, c);

        c.gridy = 2;
        p.add(button4, c);
        p.add(button5, c);
        p.add(button6, c);
        p.add(buttonmultiply, c);

        c.gridy = 3;
        p.add(button7, c);
        p.add(button8, c);
        p.add(button9, c);
        p.add(buttonminus, c);

        c.gridy = 4;
        p.add(buttondot, c);
        p.add(button0, c);
        p.add(buttoneq, c);
        p.add(buttonplus, c);

        c.fill = GridBagConstraints.BOTH;
        c.ipady = 30;      //make this component tall
        c.ipadx = 30;
        c.gridwidth = 4;
        c.insets = new Insets(10,5,5,5);
        c.gridy = 0;
        display.setBorder(BorderFactory.createLineBorder(Color.black));
        p.add(display, c);

        for(Component comp: p.getComponents()){
            if (comp instanceof JButton) {
                comp.setFont(new Font("Arial", Font.BOLD, 30));
                ((JButton) comp).setFocusPainted(false);
                ((JButton) comp).addActionListener(digitClicked((JButton) comp));
            }
        }

        display.setFont(new Font("Arial", Font.BOLD, 30));

        frame.pack();
        frame.setVisible(true);
    }

    private void printToScreen(int val){

    }

    private ActionListener digitClicked(final JButton button){

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processNumber(button);
            }
        };
    }

    private void processNumber(JButton button){

        int result = 0;
        String num = button.getText();
        if (myUtil.isNumeric(num)) {
            if (reset == 't'){
                num1 = 0;
                num2.setLength(0);
                operator = 's';
                reset = 'f';
            }

            num2.append(num);
            display.setText(num2.toString());
        }else if (num != "="){
            num1 = Integer.parseInt(num2.toString());
            System.out.println("num1 : "+ num1);
            num2.setLength(0);
            operator = num.charAt(0);
            display.setText(String.valueOf(operator));
            reset = 'f';
        }else{
            switch(operator){
                case '+':
                    result = num1 + Integer.parseInt(num2.toString());
                    break;
                case '-':
                    result = num1 - Integer.parseInt(num2.toString());
                    break;
                case '/':
                    result = num1 / Integer.parseInt(num2.toString());
                    break;
                case 'X':
                    result = num1 * Integer.parseInt(num2.toString());
                    break;
            }
            num2 = new StringBuffer(String.valueOf(result));
            reset = 't';
            display.setText(String.valueOf(result));
        }
    }

    Calculator(){
        buildGUI();
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
