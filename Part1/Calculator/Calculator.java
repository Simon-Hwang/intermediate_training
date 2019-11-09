package GridWorld.Calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator{
	private JFrame cal;
	private JPanel panel_text, panel_button;
	private JTextField text_res, text_his;
	private JButton clr;
	private String history, cur_num;
	private String[] signal = new String[2];
	private Double[] num = new Double[3];
	private int num_index = 0, signal_index = 0;
	private boolean start, point_status, signal_status;
	public Calculator() {
		para_init();
		setText();
		setPanel();
		setFrame();
	}
	private void para_init() {
		start = point_status = signal_status = false;
		signal_index = num_index = 0;
		cur_num = "0";
		history = "";
	}
	private void setFrame() {
		cal = new JFrame("Simple Calculator");
		cal.setLayout(new BorderLayout());
		cal.setLocation(300,200);
		cal.setSize(400,350);
		cal.add(panel_text, BorderLayout.NORTH);
		cal.add(panel_button, BorderLayout.CENTER);
		cal.add(clr, BorderLayout.SOUTH);
		cal.setVisible(true);
		cal.setResizable(false);
		cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void setPanel() {
		panel_text = new JPanel();
		panel_text.setLayout(new GridLayout(2, 1, 2,2));
		panel_text.add(text_his);
		panel_text.add(text_res);
		panel_button = new JPanel();
		panel_button.setLayout(new GridLayout(4, 4, 5, 5));
		addButton();
	}
	private void addButton() {
		ActionListener insert = new Insert();
		ActionListener command = new Command();
		ActionListener sum = new Sum();
		ActionListener clear = new Clear();
		String[] button_name= {"7","8","9","+","4","5","6","-","1","2","3","*",".","0","=","/"};
		for(int i = 0; i < button_name.length; ++i) {
			JButton button = new JButton(button_name[i]);
			if(button_name[i].equals("+") || button_name[i].equals("/") || button_name[i].equals("*") || button_name[i].equals("-")) {
				button.addActionListener(command);
			}else if(!button_name[i].equals("=")){
				button.addActionListener(insert);
			}else {
				button.addActionListener(sum);
			}
			panel_button.add(button);
		}
		clr = new JButton("Clear");
		clr.addActionListener(clear);
	}
	private class Clear implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			para_init();
			text_his.setText(history);
			text_res.setText(cur_num);
		}
	}
	private class Sum implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(signal_status) {
				history = history.substring(0, history.length() - 1) + "=";
			}else {
				num[num_index] = Double.parseDouble(cur_num); 
				num_index++;
				history += cur_num + "=";
			}
			for(int i = num_index - 2; i >= 0; --i) {
				num[i] = calculator(signal[i], num[i], num[i + 1]); 
			}
			text_his.setText(history);
			text_res.setText(num[0].toString());
			para_init();
		}
	}
	private class Command implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String cmd = event.getActionCommand();
			if(!start) {
				text_his.setText(history);
				text_res.setText(cur_num);
				return;
			}
			if(signal_status) {
				signal[signal_index - 1] = cmd;
				history = history.substring(0, history.length() - 1) + cmd;
			}else {
				num[num_index] = Double.parseDouble(cur_num); 
				num_index++;
				if(num_index >= 2) {
					if(signal_index == 2) {
						num[1] = calculator(signal[1], num[1], num[2]);
						num_index = 2;
						signal_index = 1;
					}else {
						if(signal[0].equals("*") || signal[0].equals("/")) {
							signal_index = 0;
							num[0] = calculator(signal[0], num[0], num[1]);
							num_index = 1;
						}
					}
				}
				signal[signal_index] = cmd;
				signal_index++;
				history += cur_num + cmd;
				signal_status = true;
			}
			text_his.setText(history);
		}
	}
	private class Insert implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String cmd = event.getActionCommand();
			if(point_status && cmd.equals(".")) {
				return;
			}
			if(!start || signal_status) {
				if(!cmd.equals(".")) {
					start = true;
					signal_status = false;
					cur_num = cmd;
				}
			}else {
				if(cmd.equals(".") && !point_status) {
					point_status = true;
					cur_num += cmd;
				}else {
					if(cur_num.equals("0")) {
						cur_num = cmd;
					}else {
						cur_num += cmd;
					}
				}
			}
			text_res.setText(cur_num);
		}
	}
	private Double calculator(String signal, Double value1, Double value2) {
		Double res = value1;
		if(signal.equals("+"))
			res += value2;
		else if(signal.equals("-"))
			res -= value2;
		else if(signal.equals("*"))
			res *= value2;
		else
			res /= value2;
		return res;
	}
	private void setText() {
		text_res = new JTextField();
		text_res.setHorizontalAlignment(JTextField.RIGHT);
		text_res.setEditable(false);
		text_res.setText(cur_num);
		text_res.setFont(new Font("黑体", Font.PLAIN, 20));
		
		text_his = new JTextField();
		text_his.setHorizontalAlignment(JTextField.RIGHT);
		text_his.setEditable(false);
		text_his.setText(history);
		text_his.setFont(new Font("宋体", Font.PLAIN, 10));
	}
	public static void main(String[] args) {
		Calculator cal = new Calculator();
	}
}