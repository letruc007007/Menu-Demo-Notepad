/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo;

/**
 *
 * @author ADMIN
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Jnotepad extends JFrame {

    private JMenuBar mBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp, mZoom;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit;
    private JMenuItem itemUndo, itemCut, itemCopy, itemPaste, itemDelete, itemSearch, itemFind, itemReplace, itemGoto, itemSelect, itemTime;
    private JMenuItem itemFont, itemZoomOn, itemZoomIn, itemSend, itemAbout,itemView;
    private JCheckBoxMenuItem itemWord, itemBar;
    private int size = 14;

    private JTextArea txtEditor;
    private File currentFile = null; // Biến lưu tên file hiện tại

    public Jnotepad(String title) {
        super(title);
        createMenu();
        txtEditor = new JTextArea();
        JScrollPane scrollEditor = new JScrollPane(txtEditor);
        add(scrollEditor);
        txtEditor.setFont(new Font("Arial", Font.PLAIN, size));
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        processEvent();
    }

    private void createMenu() {

        mBar = new JMenuBar();

        mFile = new JMenu("File");
        mEdit = new JMenu("Edit");
        mFormat = new JMenu("Format");
        mView = new JMenu("View");
        mHelp = new JMenu("Help");

        mBar.add(mFile);
        mBar.add(mEdit);
        mBar.add(mFormat);
        mBar.add(mView);
        mBar.add(mHelp);

        itemNew = new JMenuItem("New");
        itemOpen = new JMenuItem("Open...");
        itemSave = new JMenuItem("Save");
        itemSaveAs = new JMenuItem("Save As...");
        itemPageSetup = new JMenuItem("Page Setup...");
        itemPrint = new JMenuItem("Print...");
        itemExit = new JMenuItem("Exit");

        mFile.add(itemNew);
        mFile.add(itemOpen);
        mFile.add(itemSave);
        mFile.add(itemSaveAs);
        mFile.addSeparator();
        mFile.add(itemPageSetup);
        mFile.add(itemPrint);
        mFile.addSeparator();
        mFile.add(itemExit);

        itemUndo = new JMenuItem("Undo");
        itemCut = new JMenuItem("Cut");
        itemCopy = new JMenuItem("Copy");
        itemPaste = new JMenuItem("Paste");
        itemDelete = new JMenuItem("Delete");
        itemSearch = new JMenuItem("Search");
        itemFind = new JMenuItem("Find");
        itemReplace = new JMenuItem("Replace");
        itemGoto = new JMenuItem("Goto");
        itemSelect = new JMenuItem("Select");
        itemTime = new JMenuItem("Time");

        mEdit.add(itemUndo);
        mEdit.addSeparator();
        mEdit.add(itemCut);
        mEdit.add(itemCopy);
        mEdit.add(itemPaste);
        mEdit.add(itemDelete);
        mEdit.addSeparator();
        mEdit.add(itemSearch);
        mEdit.add(itemFind);
        mEdit.add(itemReplace);
        mEdit.add(itemGoto);
        mEdit.addSeparator();
        mEdit.add(itemSelect);
        mEdit.add(itemTime);

        itemWord = new JCheckBoxMenuItem("Word Wrap");
        itemFont = new JMenuItem("Font");

        mFormat.add(itemWord);
        mFormat.add(itemFont);

        mZoom = new JMenu("Zoom");
        mZoom.add(itemZoomIn = new JMenuItem("Zoom In"));
        mZoom.add(itemZoomOn = new JMenuItem("Zoom Out"));
        itemBar = new JCheckBoxMenuItem("Status Bar");

        mView.add(mZoom);
        mView.add(itemBar);

        itemAbout = new JMenuItem("About Notepad");
        itemSend = new JMenuItem("Send Feedback");
        itemView = new JMenuItem("View Help");
        
        mHelp.add(itemView);
        mHelp.add(itemSend);
        mHelp.addSeparator();
        mHelp.add(itemAbout);

        setJMenuBar(mBar);

        // Phím tắt
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));

        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        itemSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        itemSelect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itemTime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.CTRL_DOWN_MASK));

        // Xử lý sự kiện
        itemExit.addActionListener(e -> System.exit(0));

        itemAbout.addActionListener(e -> 
            JOptionPane.showMessageDialog(Jnotepad.this, "About Notepad", "Information", JOptionPane.INFORMATION_MESSAGE)
        );
    }

    private void processEvent() {
        // Xử lý Zoom In
        itemZoomIn.addActionListener(e -> {
            size += 4; 
            txtEditor.setFont(new Font("Arial", Font.PLAIN, size));
        });

        // Xử lý Zoom Out
        itemZoomOn.addActionListener(e -> {
            size -= 4; 
            txtEditor.setFont(new Font("Arial", Font.PLAIN, size));
        });
        
        itemWord.addItemListener(e -> {
            txtEditor.setLineWrap(itemWord.isSelected());
        });

        // Xử lý mở file
        itemOpen.addActionListener(e -> {
            try {
                openFile(); // Gọi hàm mở file
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Xử lý lưu file
        itemSave.addActionListener(e -> {
            try {
                saveFile(); // Gọi hàm lưu file
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Xử lý Save As
        itemSaveAs.addActionListener(e -> {
            try {
                saveFileAs(); // Gọi hàm lưu file dưới dạng khác
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Xử lý sao chép
        itemCopy.addActionListener(e -> txtEditor.copy());

        // Xử lý dán
        itemPaste.addActionListener(e -> txtEditor.paste());

        // Thay đổi phông chữ
        itemFont.addActionListener(e -> {
            String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
            String selectedFont = (String) JOptionPane.showInputDialog(
                Jnotepad.this, 
                "Choose a font:", 
                "Font", 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                fonts, 
                txtEditor.getFont().getFamily()
            );

            if (selectedFont != null) {
                txtEditor.setFont(new Font(selectedFont, Font.PLAIN, txtEditor.getFont().getSize()));
            }
        });
    }

    private void openFile() throws IOException {
        JFileChooser dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = dlgFile.getSelectedFile(); // Lưu tên file hiện tại
            try (FileInputStream fis = new FileInputStream(currentFile)) {
                byte[] b = new byte[fis.available()];
                fis.read(b);
                txtEditor.setText(new String(b));
            }
        }
    }

    private void saveFile() throws IOException {
        if (currentFile != null) {
            try (FileOutputStream fos = new FileOutputStream(currentFile)) {
                fos.write(txtEditor.getText().getBytes());
            }
        } else {
            saveFileAs(); // Nếu chưa có tên file, gọi hàm lưu dưới dạng
        }
    }
    private void saveFileAs() throws IOException {
    JFileChooser dlgFile = new JFileChooser();
    if (dlgFile.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
        File selectedFile = dlgFile.getSelectedFile();
        currentFile = selectedFile; // Lưu tên file hiện tại
        try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
            fos.write(txtEditor.getText().getBytes());
            }
        }
    }
   
    
    public static void main(String[] args) {
        Jnotepad notepad = new Jnotepad("Demo Notepad");
        notepad.setVisible(true);
    }
}

    