package test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

final public class Calculator{ //set final
	/*Initlize the parameters
	*seeting the panel sample like a normal calculator
	*store some variable to deal with the processor
	*/
	private JFrame cal;
	private JPanel panelText, panelButton;
	private JTextField textRes, textHis;
	private JButton clr;
	private String history, curNum;
	private String[] signal = new String[2];
	private Double[] num = new Double[3];
	private int numIndex, signalIndex;
	private boolean start, pointStatus, signalStatus;
	private Calculator() { // make a constructor, cause there is main func in its body, it can be private 
		paraInit();
		setText();
		setPanel();
		setFrame();
	}
	private void paraInit() { // the init func should be reused
		start = false;
		pointStatus = false;
		signalStatus = false;
		signalIndex = 0;
		numIndex = 0;
		curNum = "0";
		history = "";
	}
	private void setFrame() { //set Frame sample as it like to be
		cal = new JFrame("Simple Calculator");
		cal.setLayout(new BorderLayout());
		cal.setLocation(300,200);
		cal.setSize(400,350);
		cal.add(panelText, BorderLayout.NORTH);
		cal.add(panelButton, BorderLayout.CENTER);
		cal.add(clr, BorderLayout.SOUTH);
		cal.setVisible(true);
		cal.setResizable(false);
		cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void setPanel() {//deal with the panel
		panelText = new JPanel();
		panelText.setLayout(new GridLayout(2, 1, 2,2));
		panelText.add(textHis);
		panelText.add(textRes);
		panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(4, 4, 5, 5));
		addButton();
	}
	private void addButton() { // add Button to the panel
		ActionListener insert = new Insert();
		ActionListener command = new Command();
		ActionListener sum = new Sum();
		ActionListener clear = new Clear();
		String[] buttonName= {"7","8","9","+","4","5","6","-","1","2","3","*",".","0","=","/"};
		for(int i = 0; i < buttonName.length; ++i) {
			JButton button = new JButton(buttonName[i]);
			if(buttonName[i].equals("+") || buttonName[i].equals("/") || buttonName[i].equals("*") || buttonName[i].equals("-")) {
				button.addActionListener(command);
			}else if(!buttonName[i].equals("=")){
				button.addActionListener(insert);
			}else {
				button.addActionListener(sum);
			}
			panelButton.add(button);
		}
		clr = new JButton("Clear");
		clr.addActionListener(clear);
	}
	private class Clear implements ActionListener{ // add Listener
		public void actionPerformed(ActionEvent event) {
			paraInit();
			textHis.setText(history);
			textRes.setText(curNum);
		}
	}
	private class Sum implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(signalStatus) {
				history = history.substring(0, history.length() - 1) + "=";
			}else {
				num[numIndex] = Double.parseDouble(curNum); 
				numIndex++;
				history += curNum + "=";
			}
			for(int i = numIndex - 2; i >= 0; --i) {
				num[i] = calculator(signal[i], num[i], num[i + 1]); 
			}
			textHis.setText(history);
			textRes.setText(num[0].toString());
			paraInit();
		}
	}
	private class Command implements ActionListener{ //add ActionListener to specifify button
		public void actionPerformed(ActionEvent event) {
			String cmd = event.getActionCommand();
			if(!start) {
				textHis.setText(history);
				textRes.setText(curNum);
				return;
			}
			if(signalStatus) {
				signal[signalIndex - 1] = cmd;
				history = history.substring(0, history.length() - 1) + cmd;
			}else {
				num[numIndex] = Double.parseDouble(curNum); 
				numIndex++;
				if(numIndex >= 2) {
					if(signalIndex == 2) {
						num[1] = calculator(signal[1], num[1], num[2]);
						numIndex = 2;
						signalIndex = 1;
					}else {
						if(signal[0].equals("*") || signal[0].equals("/")) {
							signalIndex = 0;
							num[0] = calculator(signal[0], num[0], num[1]);
							numIndex = 1;
						}
					}
				}
				signal[signalIndex] = cmd;
				signalIndex++;
				history += curNum + cmd;
				signalStatus = true;
			}
			textHis.setText(history);
		}
	}
	private class Insert implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String cmd = event.getActionCommand();
			if(pointStatus && cmd.equals(".")) {
				return;
			}
			if(!start || signalStatus) {
				if(!cmd.equals(".")) {
					start = true;
					signalStatus = false;
					curNum = cmd;
				}
			}else {
				if(cmd.equals(".") && !pointStatus) {
					pointStatus = true;
					curNum += cmd;
				}else {
					if(curNum.equals("0")) {
						curNum = cmd;
					}else {
						curNum += cmd;
					}
				}
			}
			textRes.setText(curNum);
		}
	}
	private Double calculator(String signal, Double value1, Double value2) {
		Double res = value1;
		if(signal.equals("+")){
			res += value2;
		}
		else if(signal.equals("-")){
			res -= value2;
		}	
		else if(signal.equals("*")){
			res *= value2;
		}
		else{
			res /= value2;
		}
		return res;
	}
	private void setText() {
		textRes = new JTextField();
		textRes.setHorizontalAlignment(JTextField.RIGHT);
		textRes.setEditable(false);
		textRes.setText(curNum);
		textRes.setFont(new Font("黑体", Font.PLAIN, 20));
		
		textHis = new JTextField();
		textHis.setHorizontalAlignment(JTextField.RIGHT);
		textHis.setEditable(false);
		textHis.setText(history);
		textHis.setFont(new Font("宋体", Font.PLAIN, 10));
	}
	public static void main(String[] args) {
		new Calculator();
	}
}
