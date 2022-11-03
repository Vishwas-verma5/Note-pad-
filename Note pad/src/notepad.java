
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import static javax.swing.text.html.HTML.Attribute.N;
import javax.swing.undo.UndoManager;

class code implements ActionListener {

    JFrame fm;
    JMenu file, edit, help, formate,subfont;
    JMenuBar menubar;
    JTextArea textarea;
     JScrollPane pane;
    JMenuItem nw, open, save, saveas, print, exit, cut, copy, paste, selectall, backgroundcolor, wraping,undo,redo,
            font_style,font_size;
    JList fontsize;
    String font_sizearray[]={"11","12","13","14","15","16","17","18","19"};
    public void appcode() {
        fm = new JFrame("Notepad");
        fm.setVisible(true);
        fm.setBackground(Color.WHITE);
        fm.setBounds(200, 200, 900, 700);
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // text area here
        textarea = new JTextArea();
       pane = new JScrollPane(textarea);
        pane.setBorder(BorderFactory.createEmptyBorder());
        fm.add(pane);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        menubar = new JMenuBar();

        // file menu bar code here
        file = new JMenu("File");
        nw = new JMenuItem("New ");
        open = new JMenuItem("Open");

        save = new JMenuItem("Save");
        saveas = new JMenuItem("Save As");
        print = new JMenuItem("Print");
        exit = new JMenuItem("Exit");
        // adding in file menu bar
        file.add(nw);
        file.add(open);
        file.addSeparator();
        file.add(save);
        file.add(saveas);
        file.addSeparator();
        file.add(print);
        file.add(exit);
        menubar.add(file);
        fm.setJMenuBar(menubar);

        nw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
       // saveas.setAccelerator(keyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.SHIFT_DOWN_MASK));
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));

        // Edit menu bar starting from here
        edit = new JMenu("Edit");
        cut = new JMenuItem("Cut ");
        copy = new JMenuItem("Copy ");

        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        undo=new JMenuItem("Undo");
        redo=new JMenuItem("Redo");
        formate = new JMenu("Formating");
        subfont = new JMenu("Font");
        font_style=new JMenuItem("Font Style");
        font_size=new JMenuItem("Font Size");
        wraping = new JMenuItem("Text Wraping");
        backgroundcolor = new JMenuItem("Background Color");
        

        // adding in edit menu bar
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.addSeparator();
        edit.add(undo);
        edit.add(redo);
        menubar.add(edit);

        formate.add(subfont);
        subfont.add(font_size);
        subfont.add(font_style);
        formate.add(wraping);
        formate.add(backgroundcolor);
        menubar.add(formate);

        
           
          
            
        fm.setJMenuBar(menubar);
        // shortcut keys listener
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));
         redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,KeyEvent.CTRL_DOWN_MASK));
        //formate menu bar 

        nw.addActionListener(this);
        save.addActionListener(this);
        saveas.addActionListener(this);
        open.addActionListener(this);
        print.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);

//        formate action listener
        wraping.addActionListener(this);
      font_size.addActionListener(this);
      font_style.addActionListener(this);
        backgroundcolor.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nw) 
        {
            this.appcode();
        } 
        else if (e.getSource() == open) 
        {
            JFileChooser filechooser = new JFileChooser("open");
            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("All Files", "txt");
            filechooser.setAcceptAllFileFilterUsed(true);
            filechooser.addChoosableFileFilter(textfilter);
            int action = filechooser.showOpenDialog(null);
            if (action != filechooser.APPROVE_OPTION) {
                return;
            } else {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filechooser.getSelectedFile()));
                    textarea.read(reader, null);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } 
        else if (e.getSource() == save) {
            JFileChooser filechooser = new JFileChooser();
//            FileNameExtensionFilter textfilter = new FileNameExtensionFilter("All Files", "java");
            filechooser.setAcceptAllFileFilterUsed(true);
            // filechooser.addChoosableFileFilter(textfilter);
            int action = filechooser.showSaveDialog(null);
            if (action != filechooser.APPROVE_OPTION) {
                return;
            } else {
                String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
//                if (!filename.contains(".java")) {
//                    filename = filename + ".java";
//                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    textarea.write(writer);
                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }

            }
           

        } 
        else if(e.getSource()==saveas)
        {
                 JFileChooser filechooser = new JFileChooser();
                 filechooser.setAcceptAllFileFilterUsed(true);
                 filechooser.setDialogTitle("Save As");
               int action=  filechooser.showDialog(fm,"Save As");
               
            if (action != filechooser.APPROVE_OPTION) {
                return;
            } else {
                String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
           
//                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    textarea.write(writer);
                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }

            }
       
        }
        else if (e.getSource() == print) {
            try {
                textarea.print();
            } catch (PrinterException ex) {
                Logger.getLogger(code.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == selectall) {
            textarea.selectAll();
        } else if (e.getSource() == cut) {
            textarea.cut();
        } else if (e.getSource() == copy) {
            textarea.copy();
        } else if (e.getSource() == paste) {
            textarea.paste();
        } else if (e.getSource() == wraping) {
            textarea.setLineWrap(true);
            textarea.setWrapStyleWord(true);
        } else if (e.getSource() == backgroundcolor) {

            Color col = JColorChooser.showDialog(textarea, "choose color", Color.RED);
            textarea.setBackground(col);
        } 
       else if (e.getSource() == font_size) {
       
         Font font = new Font("font", Font.PLAIN, 17);
          textarea.setFont(font);

       }
       
        
    }
}

class notepad {

    public static void main(String args[]) throws Exception {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception uiex) {
            uiex.printStackTrace();
        }
        code c = new code();
        c.appcode();

    }

}
