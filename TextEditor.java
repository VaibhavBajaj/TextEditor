import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

// Made a text editor class.
public class TextEditor extends JFrame implements ActionListener {

	//Class owns a text area
	JTextArea area;

	//On running make new object using this class
	public static void main(String[] args) {

		new TextEditor();

	}

	//Constructor of text editor.
	public TextEditor() {

		//Commanding JFrame constructor to run.
		super("Text Editor");
		setSize(700,700);

		//Initialize it
		initialize();

		//Make visible
		setVisible(true);

	}

	//Create the text area and menu bar.
	private void initialize() {

		area = new JTextArea(30, 20);
		add(area);

		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);


		//JMenu fileMenu = new JMenu("File");
		String[] fileMenuItem = {"New", "Open", "Save","Save As", "Print"};
		JMenu fileMenu = createMenu("File", fileMenuItem);
		bar.add(fileMenu);

		String[] editMenuItem = { "Cut", "Copy", "Paste", "Paste", "Undo", "Redo", "Delete" };
		JMenu editMenu = createMenu("Edit", editMenuItem);
		bar.add(editMenu);

		String[] viewMenuItem = {"Actual Size"};
		JMenu viewMenu = createMenu("View" , viewMenuItem);
		bar.add(viewMenu);

		//Make a filter menu.
		String[] filterMenuItem = {"Remove Profanity", "Remove Trolling"};
		JMenu filterMenu = createMenu("Filter" , filterMenuItem);
		bar.add(filterMenu);


	}

	public JMenu createMenu(String name, String[] items) {
		JMenu menu = new JMenu(name);
		for (String item : items) {
			menu.add(createMenuItem(item));
		}
		return menu;
	}

	public JMenuItem createMenuItem(String MenuItem) {
		JMenuItem item = new JMenuItem(MenuItem);
		item.setActionCommand(MenuItem);
		item.addActionListener(this);
		return item;
	}

	private void openFile() {

		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {

			System.out.println("Haha! You think you can get a file from me.");
			File f = chooser.getSelectedFile();
			try {
				String content = "";
				Scanner s = new Scanner(f);
				while (s.hasNextLine()) {
					//Append another line of input
					content = content  + "\n" + s.nextLine();
				}
				//Tell text area what text is.
				area.setText(content);
			}
			catch (FileNotFoundException e) {
				System.out.println("Not a file!");
				//e.printStackTrace();
			}
		}
	}

	private void saveFile() {

		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {

			System.out.println("Hehe! You think I'll let you save any stuff.");
			File f = chooser.getSelectedFile();

			try {
				// if file doesn't exists, then create it
				if (!f.exists()) {
					f.createNewFile();
				}
				FileWriter fw = new FileWriter(f.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(area.getText());
				bw.close();
			}
			catch (IOException e) {
				System.out.println("Not a file!");
				//e.printStackTrace();
			}
		}
	}

	public void saveAsFile() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {

			System.out.println("Hoo hoo! You think I'll let you save any stuff.");
			File f = chooser.getSelectedFile();

			try {
				// if file doesn't exists, then create it
				if (!f.exists()) {
					f.createNewFile();
				}
				FileWriter fw = new FileWriter(f.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(area.getText());
				bw.close();
			}
			catch (IOException e) {
				System.out.println("Not a file!");
				//e.printStackTrace();
			}
		}
	}

	public void newFile() {

		System.out.println("Hehe! You think I'll let you make a new file!");

	}

	public void printFile() {

		System.out.println("Hehe! You think I'll let you print anything... I'm an evil Text Editor.");
		System.out.println("Muahahahahahahaha!");

	}

	public void removeTrolling() {
		String wb = "\\b";
		String content = area.getText();
		// \\b = word boundary
		// + = one or more times
		// * = zero or more times
		area.setText(content);
		content = content.replaceAll("(?i)" + wb + "troll" + wb, "No no no.");
		content = content.replaceAll("(?i)" + wb + "tro(lo)*" + wb, "No no no.");
		content = content.replaceAll("(?i)" + wb + "haha(ha)+" + wb, "Haha");
		content = content.replaceAll("(?i)" + wb + "ha+ha+" + wb, "Haha");
		content = content.replaceAll("(?i)" + wb + "hm+" + wb, "Hmm");
		area.setText(content);
	}

	public void removeProfanity() {

		String content = area.getText();
		// \\b = word boundary
		String[] badWords = {"darn", "heck", "shoot", "gadha", "budhu", "uloo", "gadhi", "paagal", "gadhe" };
		for(String words : badWords) {
			content = content.replaceAll("(?i)\\b" + words + "\\b", "Hoo Hoo Ha Ha");	
		}
		area.setText(content);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		System.out.println(command);

		if(command.equals("Open")) {
			openFile();
		}
		if(command.equals("Save")) {
			saveFile();
		}
		if(command.equals("New")) {
			newFile();
		}
		if(command.equals("Save As")) {
			saveAsFile();
		}
		if(command.equals("Print")) {
			printFile();
		}
		if(command.equals("Remove Profanity")) {
			removeProfanity();
		}
		if(command.equals("Remove Trolling")) {
			removeTrolling();
		}
	}

}


/*
  value/policy iteration
  expectiminimax
 Q learning
  ActionListener
 regular expressions
 */
