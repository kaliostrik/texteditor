package ru.medvedev.main;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Editor extends JFrame {
    final About aboutwin = new About();
    JTextArea editorArea = new JTextArea();
    File selectedfile = null;

    public Editor() {
        super("Text Editor");
        initCompoments();
        initMenuBar();
    }

    private void initCompoments() {
        this.setBounds(200, 200, 500, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(editorArea);
        add(scroll, BorderLayout.CENTER);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu mfile = new JMenu("File");
        JMenu medit = new JMenu("Edit");
        JMenu mhelp = new JMenu("Help");

        menuBar.add(mfile);
        menuBar.add(medit);
        menuBar.add(mhelp);

        JMenuItem editCut = new JMenuItem("Cut");
        medit.add(editCut);
        editCut.addActionListener(e -> {
            editorArea.cut();
        });

        JMenuItem editCopy = new JMenuItem("Copy");
        medit.add(editCopy);
        editCopy.addActionListener(e -> {
            editorArea.copy();
        });

        JMenuItem editPaste = new JMenuItem("Paste");
        medit.add(editPaste);
        editPaste.addActionListener(e -> {
            editorArea.paste();
        });

        JMenuItem fileNew = new JMenuItem("New file");
        mfile.add(fileNew);
        fileNew.addActionListener(e -> {
            selectedfile = null;
            editorArea.setText("");
        });

        JMenuItem fileOpen = new JMenuItem("Open");
        mfile.add(fileOpen);
        fileOpen.addActionListener(e -> {
            try {
                openFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        JMenuItem fileSaveAs = new JMenuItem("Save as");
        mfile.add(fileSaveAs);
        fileSaveAs.addActionListener(e -> {
            try {
                saveFile();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        JMenuItem fileExit = new JMenuItem("Exit");
        mfile.add(fileExit);
        fileExit.addActionListener(e -> {
            System.exit(0);
        });

        JMenuItem helpAbout = new JMenuItem("About");
        mhelp.add(helpAbout);
        helpAbout.addActionListener(e -> {
            aboutwin.setVisible(true);
        });
    }

    public void openFile() throws IOException {
        File filename = null;
        JFileChooser openfile = new JFileChooser(System.getProperty("user.home"));
        if (openfile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedfile = filename;
            filename = openfile.getSelectedFile();
            StringBuilder result = new StringBuilder();
            FileReader filereader = new FileReader(openfile.getSelectedFile());
            BufferedReader reader = new BufferedReader(filereader);
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
            editorArea.setText(result.toString());
        }
        System.out.println(filename.toString());
    }

    public void saveFile() throws FileNotFoundException {
        if (selectedfile != null) {
            PrintWriter writer = new PrintWriter(selectedfile);
            writer.write(editorArea.getText());
            writer.close();
        } else {
            JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
            if (dialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                PrintWriter writer = new PrintWriter(dialog.getSelectedFile());
                selectedfile = dialog.getSelectedFile();
                writer.write(editorArea.getText());
                writer.close();
            }
        }
    }
}







