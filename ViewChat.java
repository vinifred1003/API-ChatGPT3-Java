package br.com.ChatGPT3Project;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SpringLayout;
import javax.swing.GroupLayout.Alignment;

public class ViewChat extends JFrame {

	private JPanel contentPane;
	private JTextPane textPane1;
	private JEditorPane LocalText;
	private String userInputText;
	private String chatResponse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewChat frame = new ViewChat();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewChat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new SpringLayout());
		LocalText = new JEditorPane();

		LocalText.setText("Write here...");

		JButton searchByTextButton = new JButton("Text Search");
		searchByTextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				userInputText = LocalText.getText();

				if (!userInputText.isEmpty()) {
					String textToSearch = userInputText;
					API t = new API();
					try {
						chatResponse = t.chat(textToSearch);
						textPane1.setText(chatResponse);
					} catch (Exception ex) {
						 showErrorDialog("An error occurred during the search: " + ex.getMessage());
					}
				}
			}
		});

		textPane1 = new JTextPane();

		JButton SaveButton = new JButton("Save");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chatAnswer = chatResponse;
				ChatAnswer CA = new ChatAnswer();
				CA.setChatAnswer(chatAnswer);
				ChatAnswerDAO answer2 = new ChatAnswerDAO();
				answer2.inserir(CA);

				JOptionPane.showMessageDialog(null, "reply sent");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(LocalText, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(textPane1, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(searchByTextButton, GroupLayout.PREFERRED_SIZE, 173,
										GroupLayout.PREFERRED_SIZE)
								.addGap(215)
								.addComponent(SaveButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textPane1, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addComponent(LocalText, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
						.addGap(18).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(searchByTextButton).addComponent(SaveButton))
						.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	private void showErrorDialog(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Erro", JOptionPane.ERROR_MESSAGE);
	}

}
