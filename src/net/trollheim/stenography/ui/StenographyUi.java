package net.trollheim.stenography.ui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class StenographyUi extends JTabbedPane {

	public StenographyUi() {
		super();
		addTab("Encode", null, new EncodePanel(), "Encode");
		addTab("Decode", null, new DecodePanel(), "Decode");
		setMnemonicAt(0, KeyEvent.VK_1);
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Stenography v0.2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new StenographyUi());

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}
}