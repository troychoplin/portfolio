package solution;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * Creates a GUI Calculator.
 * 
 * @author Troy Choplin
 * @version 2020-02-06
 * 
 */

public class Calculator
{

    private JFrame calcFrame;

    /**
     * Main method- initializes calculator GUI.
     * @param args
     */

    public static void main(String[] args)
    {
        Calculator calc = new Calculator();

    }

    /**
     * No-args constructor.
     */

    public Calculator()
    {
        JFrame calcFrame = new JFrame();
        this.calcFrame = calcFrame;
        this.calcFrame.setLocation(100, 100);
        this.calcFrame.setSize(400, 400);
        this.calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.calcFrame.setTitle("Calculator");

        initializeComponents(calcFrame);
        this.calcFrame.pack();
        this.calcFrame.setVisible(true);
    }

    /**
     * Returns main frame.
     * @return calcFrame
     */
    public JFrame getFrame()
    {
        return this.calcFrame;
    }


    /**
     * Initializes all GUI Components.
     * @param calcFrame
     */
    public void initializeComponents(JFrame calcFrame)
    {

        if (calcFrame != null)
        {
            JPanel buttonPan = new JPanel();
            JButton addButton = new JButton("ADD");
            JButton subButton = new JButton("SUB");
            JButton multButton = new JButton("MULT");
            JButton divButton = new JButton("DIV");
            addButton.setName("addButton");
            subButton.setName("subButton");
            multButton.setName("multButton");
            divButton.setName("divButton");
            buttonPan.add(addButton);
            buttonPan.add(subButton);
            buttonPan.add(multButton);
            buttonPan.add(divButton);
            this.calcFrame.add(buttonPan, BorderLayout.PAGE_END);
            JPanel resultPanel = new JPanel();
            final JLabel resultLabel = new JLabel("Result = ");
            resultLabel.setName("resultLabel");
            resultPanel.add(resultLabel);
            this.calcFrame.add(resultPanel, BorderLayout.WEST);
            JPanel textPanel = new JPanel();
            final JTextField text1 = new JTextField(10);
            final JTextField text2 = new JTextField(10);
            text1.setName("leftOperand");
            text2.setName("rightOperand");
            textPanel.add(text1);
            textPanel.add(text2);
            this.calcFrame.add(textPanel, BorderLayout.PAGE_START);
            divButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    double ans = 0;
                    double num1 = 0;
                    double num2 = 0;
                    String str1 = text1.getText();
                    String str2 = text2.getText();
                    try 
                    {
                        num1 = Double.parseDouble(str1);
                        num2 = Double.parseDouble(str2);
                    }
                    catch (Exception NumberFormatException)
                    {
                        resultLabel.setText("Result = Error");
                    }
                    if (num2 != 0)
                    {
                        ans = num1 / num2;
                        resultLabel.setText("Result = " + ans);
                    }
                    else 
                    {
                        resultLabel.setText("Result = Error");  
                    }
                }
            }

                );

            addButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    double ans = 0;
                    double num1 = 0;
                    double num2 = 0;
                    boolean err = false;
                    String str1 = text1.getText();
                    String str2 = text2.getText();
                    try 
                    {

                        num1 = Double.parseDouble(str1);
                        num2 =  Double.parseDouble(str2);
                    }
                    catch (Exception NumberFormatException)
                    {
                        err = true;
                        resultLabel.setText("Result = Error");
                    }
                    if (!err)
                    {
                        ans = num1 + num2;
                        resultLabel.setText("Result = " + ans);
                    }
                }

            }
                );

            subButton.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {
                    double ans = 0;
                    double num1 = 0;
                    double num2 = 0;
                    boolean err = false;
                    String str1 = text1.getText();
                    String str2 = text2.getText();
                    try 
                    {

                        num1 = Double.parseDouble(str1);
                        num2 =  Double.parseDouble(str2);
                        ans = num1 - num2;
                        resultLabel.setText("Result = " + ans);

                    }
                    catch (Exception NumberFormatException)
                    {
                        err = true;
                        resultLabel.setText("Result = Error");
                    }
                    if (!err) 
                    {
                        ans = num1 - num2;
                        resultLabel.setText("Result = " + ans);
                    }
                }

            }
                );

            multButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    double ans = 0;
                    double num1 = 0;
                    double num2 = 0;
                    boolean err = false;
                    String str1 = text1.getText();
                    String str2 = text2.getText();
                    try 
                    {

                        num1 = Double.parseDouble(str1);
                        num2 = Double.parseDouble(str2);
                    }
                    catch (Exception NumberFormatException) 
                    {
                        err = true;
                        resultLabel.setText("Result = Error");
                    }
                    if (!err)
                    {
                        ans = num1 * num2;
                        resultLabel.setText("Result = " + ans);
                    }
                }

            }
                );
        }

    }







}
