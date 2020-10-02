import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FileViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btn;
	private JCheckBox chk;
	private JTextArea text;
	private JScrollPane scroller;
	private JSlider slider;
	Font font;

	public FileViewer() {
		btn = new JButton("Click");
		btn.setBounds(10, 30, 100, 70);
		chk = new JCheckBox("Choose One");
		chk.setBounds(10, 400, 100, 60);
		text = new JTextArea();

		slider = new JSlider();
		slider.setMinimum(10);
		slider.setMaximum(60);
		slider.setValue(25);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBounds(150, 40, 500, 50);

		InnerListeners listen = new InnerListeners();

		slider.addChangeListener(listen);

		font = new Font("Calibri", Font.ITALIC, 25);
		text.setFont(font);

		scroller = new JScrollPane(text);
		btn.addActionListener(listen);

		chk.addChangeListener(listen);

		scroller.setBounds(120, 100, 850, 900);
		add(btn);
		add(slider);
		add(chk);

		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroller);

		// scroller.add(text);
		// scroller.add(text);
		// add(text);
		setLayout(null);

		setTitle("TestDisplay");
		setVisible(true);
		// pack();
		setSize(1000, 1000);
	}

	private class InnerListeners implements ActionListener, ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == slider) {
				font = new Font("Calibri", Font.ITALIC, slider.getValue());
				text.setFont(font);
				text.setText(text.getText());

			} else if (e.getSource() == chk) {
				if (chk.isSelected()) {
					text.setText(text.getText().toUpperCase());
				}

				else {
					text.setText(text.getText().toLowerCase());
				}
			}

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JFileChooser filechooser = new JFileChooser();
			filechooser.showOpenDialog(null);
			File file = filechooser.getSelectedFile();
			if (file != null) {
				StringBuffer sb = new StringBuffer();
				try (Scanner sc = new Scanner(file)) {
					while (sc.hasNextLine()) {
						sb.append(sc.nextLine() + "\n");
					}

					text.setText(sb.toString());

				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				}
			} else {
				text.setText("No file choosed");
			}

		}

	}

	public static void main(String[] args) {
		new FileViewer();
	}
}
