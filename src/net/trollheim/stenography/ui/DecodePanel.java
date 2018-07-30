package net.trollheim.stenography.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.trollheim.stenography.core.SimpleStenography;
import net.trollheim.stenography.core.Util;

public class DecodePanel extends JPanel implements ActionListener {
	static private final String newline = "\n";
	JButton openButton, saveButton;
	JTextArea log;

	private FileSelector container;
	private FileSelector output;
	SimpleStenography stenography = new SimpleStenography();

	private class InnnerFileSelector extends FileSelector{

		@Override
		protected void log(String message) {
			if (log!=null)
			{
				log.append(message);
			}
		}

		public InnnerFileSelector(String buttonLabel) {
			super(buttonLabel);
		}}
	
	
	public DecodePanel() {
		super(new BorderLayout());

	 	log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
 
		JPanel buttonPanel = new JPanel(); 

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		container = new InnnerFileSelector("Container");
		output = new InnnerFileSelector("Output");

		buttonPanel.add(container);
		buttonPanel.add(output);
		Button start = new Button("Start");
		start.addActionListener(this);
		buttonPanel.add(start);
		add(buttonPanel, BorderLayout.PAGE_START);

		add(logScrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		Util util = new Util();
		try {
			BufferedImage containerImg = util.load(container.getFile());
			util.save(stenography.decode(containerImg), output.getFile());
			log.append("Success!!!");
		} catch (Exception ex) {
			log.append("Operation failed : `" + ex.getMessage() + "`."
					+ newline);
		}
	}
}
