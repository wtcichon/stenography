package net.trollheim.stenography.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileSelector extends JPanel implements ActionListener {
	/**
	 * 
	 */
	
	protected void log(String message){}
	
	private static final long serialVersionUID = 1L;
	JButton button;
	JLabel label;
	private JFileChooser fc;
	private File file;

	public File getFile() {
		return file;
	}

	public FileSelector(String buttonLabel)
	{
		super(new BorderLayout());
		setSize(400, 30);
		setBackground(Color.LIGHT_GRAY);
		label = new JLabel("Not selected");
		
		button = new JButton(buttonLabel);
 
		add(label,BorderLayout.CENTER);
		add(button,BorderLayout.EAST);
		button.addActionListener(this);
		fc = new JFileChooser();
		label.setPreferredSize(new Dimension(300, 20));
		button.setPreferredSize(new Dimension(100, 20));
 
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int returnVal = fc.showOpenDialog(FileSelector.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			label.setText(file.getName());
			log("Selected file : " + file.getName() + ".\n");
		} else {
			// log.append("Open command cancelled by user." + newline);
		}

	}

}
