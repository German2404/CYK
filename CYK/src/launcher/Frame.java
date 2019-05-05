package launcher;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import model.CYKCore;
import model.Grammar;

public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4402844063484353617L;
	
	
	private JTextArea grammar;
	private JTextField string;
	private JButton check;
	
	
	public Frame() {
		this.setLayout(new BorderLayout());

		
		grammar=new JTextArea();
		grammar.setBorder(new TitledBorder("Grammar:"));
		grammar.setToolTipText("Write your grammar here...");
		
		string=new JTextField();
		string.setBorder(new TitledBorder("String:"));
		string.setToolTipText("Write the string to check here...");
		check=new JButton("Run CYK");
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					String grammarString=grammar.getText();
					JTextField f=new JTextField();
					f.setEditable(false);
					Grammar g=Grammar.grammarParser(grammarString);
					String toCheck=string.getText();
					boolean belongs= CYKCore.CYK(g, toCheck);
					if(belongs) {
						f.setText("The string "+"'"+toCheck+"'"+" belongs to the language of the specified grammar.");
					}else {
						f.setText("The string "+"'"+toCheck+"'"+" does not belong to the language of the specified grammar.");
					}
					JOptionPane.showMessageDialog(null, f);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		this.add(grammar,BorderLayout.CENTER);
		JPanel p=new JPanel(new BorderLayout());
		p.add(string,BorderLayout.CENTER);
		p.add(check,BorderLayout.SOUTH);
//		TextPrompt tpgrammar=new TextPrompt("Write your grammar here...",grammar);
//		tpgrammar.changeAlpha(0.75f);
//		tpgrammar.changeStyle(Font.ITALIC);
//		
//		TextPrompt tpstring=new TextPrompt("Write the string to check here...",string);
//		tpstring.changeAlpha(0.75f);
//		tpstring.changeStyle(Font.ITALIC);
		this.add(p,BorderLayout.SOUTH);
		JLabel l=new JLabel("CYK for CNF grammars.");
		l.setHorizontalAlignment(JLabel.CENTER);
		this.add(l,BorderLayout.NORTH);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("CYK for CNF grammars");
	}
	
	
	public static void main(String[] args) {
		new Frame();
	}

}
