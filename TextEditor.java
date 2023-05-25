import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener {

	JTextArea area;
	JScrollPane pane;
	JMenuBar mb;
	JMenu m1,m2,m3;
	JMenuItem newfile,open,savee,print,exitt;
	JMenuItem op1,op2,op3,op4;
	JMenuItem about;

	public TextEditor()
	{
		area = new JTextArea();
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setFont(new Font("Comic Sans MS",Font.PLAIN,25));
		
		pane = new JScrollPane(area);
		pane.setPreferredSize(new Dimension(400,300));
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		mb = new JMenuBar();
		setJMenuBar(mb);
		mb.setBorderPainted(true);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		mb.setBorder(border);
		
		m1 = new JMenu("File");
		m2 = new JMenu("Edit");
		m3 = new JMenu("About us");
		
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);

		
		newfile = new JMenuItem("New");
		open = new JMenuItem("Open");
		savee = new JMenuItem("Save");
		print = new JMenuItem("Print");
		exitt = new JMenuItem("Exit");
		
		op1 = new JMenuItem("Select all");
		op2 = new JMenuItem("Cut");
		op3 = new JMenuItem("Copy");
		op4 = new JMenuItem("Paste");
		
		
		about = new JMenuItem("about");
		
		
		
		m1.add(newfile);
		m1.add(open);
		m1.add(savee);
		m1.add(print);
		m1.add(exitt);
		
		m2.add(op1);
		m2.add(op2);
		m2.add(op3);
		m2.add(op4);
		
		m3.add(about);
		
		
		newfile.addActionListener(this);
		open.addActionListener(this);
		savee.addActionListener(this);
		print.addActionListener(this);
		exitt.addActionListener(this);
		
		
		op1.addActionListener(this);
		op2.addActionListener(this);
		op3.addActionListener(this);
		op4.addActionListener(this);
		about.addActionListener(this);
		
       
        add(pane);
		setTitle("My NotePad");
		setVisible(true);
		setSize(500,500);
        setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new TextEditor();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource()== newfile)
		{
			area.setText("");
		}

		if(e.getSource()==open) 
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
			fileChooser.setFileFilter(filter);
			
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
			 File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			 Scanner fileIn = null;
			 
			 try 
			 {
			  fileIn = new Scanner(file);
			  if(file.isFile()) {
			   while(fileIn.hasNextLine()) {
				String line = fileIn.nextLine()+"\n";
				area.append(line);
			   }
			  }
			 }
			catch (FileNotFoundException e1) 
			{
			  System.out.println("Something Wrong...");
			}
			}
		   }

		   if(e.getSource()==savee) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			
			int response = fileChooser.showSaveDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
			 File file;
			 PrintWriter fileOut = null;
			 
			 file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			 try {
			  fileOut = new PrintWriter(file);
			  fileOut.println(area.getText());
			 } 
			 catch (FileNotFoundException e1) {
				System.out.println("Something Went Wrong...");
			 }
			}
		   }
		
		
		if(e.getSource()==print)
		{
			try {
				area.print();}
			catch(Exception except) {
				System.out.println("OOPS! something went wrong");}
		}
		
		if(e.getSource()==exitt)
		{
			System.exit(0);
		}
		
		if(e.getSource()==op1)
		{
			area.selectAll();
		}
		
		if(e.getSource()==op2)
		{
			area.cut();
		}
		
		if(e.getSource()==op3)
		{
			area.copy();
		}
		
		if(e.getSource()==op4)
		{
			area.paste();
		}
		
        if(e.getSource()==about)
		{
            JOptionPane.showMessageDialog(this,"Developed by BALAJI A");

		}

		
	
		
	}

	}