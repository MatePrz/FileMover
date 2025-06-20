package pl.filemover.ui;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

import pl.filemover.ui.listeners.CopyActionListener;
import pl.filemover.ui.listeners.FolderChooserListener;
import pl.filemover.ui.listeners.MaskSelectActionListener;
import pl.filemover.ui.listeners.MaskSelectPopupListener;
import pl.filemover.utils.FileMask;
import pl.filemover.utils.Messages;

@SuppressWarnings("serial")
public class FileSelectorWindow extends JFrame{
	private final String[] masks = Arrays.stream(FileMask.values()).map(FileMask::getPattern).toArray(String[]::new);
	private String selectedMask = FileMask.ALL.getPattern();
	
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel startPathPanel;
	private JPanel endPathPanel;
	private JPanel maskPanel;
	
	private JTextField startPathField;
	private JTextField endPathField;
	
	private JComboBox<String> maskComoBox;
	
	private JButton startPathSelectButton;
	private JButton endPathSelectButton;
	private JButton copyButton;

    public FileSelectorWindow() {
        super(Messages.APP_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setMinimumSize(new Dimension(1000, 200));
        setResizable(false);
        setLocationRelativeTo(null);
        
        //MAIN PANEL
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 0, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        setContentPane(mainPanel);
        
        //TOP PANEL
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 20, 0));
        
        //LEWA POLOWA TOP PANEL
        startPathPanel = new JPanel(new BorderLayout(5, 5));
        startPathPanel.add(new JLabel(Messages.SOURCE_LABEL), BorderLayout.NORTH);
        startPathField = new JTextField(System.getProperty("user.dir"));
        startPathSelectButton = new JButton(Messages.BUTTON_SELECT);
        startPathPanel.add(startPathField, BorderLayout.CENTER);
        startPathPanel.add(startPathSelectButton, BorderLayout.EAST);
        
        //PRAWA POLOWA TOP PANEL
        endPathPanel = new JPanel(new BorderLayout(5, 5));
        endPathPanel.add(new JLabel(Messages.DEST_LABEL), BorderLayout.NORTH);
        endPathField = new JTextField(System.getProperty("user.dir"));
        endPathSelectButton = new JButton(Messages.BUTTON_SELECT);
        endPathPanel.add(endPathField, BorderLayout.CENTER);
        endPathPanel.add(endPathSelectButton, BorderLayout.EAST);
        
        topPanel.add(startPathPanel);
        topPanel.add(endPathPanel);
        
        //BOTTOM PANEL
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 2, 20, 0));
        
        //BOTTOM PANEL LEWA POLOWA
        maskPanel = new JPanel(new BorderLayout(5, 5));
        maskComoBox = new JComboBox<String>(masks);
        maskComoBox.setEditable(true);
        maskComoBox.setSelectedItem(FileMask.ALL.getPattern());
        maskPanel.add(new JLabel(Messages.MASK_LABEL), BorderLayout.NORTH);
        maskPanel.add(maskComoBox, BorderLayout.CENTER);
        
        //COPY BUTTON
        copyButton = new JButton(Messages.COPY_BUTTON);
        
        bottomPanel.add(maskPanel);
        bottomPanel.add(copyButton);
        
        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);
        setVisible(true);
        
        maskComoBox.addActionListener(new MaskSelectActionListener(this, maskComoBox));
        maskComoBox.addPopupMenuListener(new MaskSelectPopupListener(maskComoBox));
        
        startPathSelectButton.addActionListener(new FolderChooserListener(this, true));
        endPathSelectButton.addActionListener(new FolderChooserListener(this, false));
        copyButton.addActionListener(new CopyActionListener(this));
    }
    
    public JTextField getStartPathField() {
    	return this.startPathField;
    }
    
    public JTextField getEndPathField() {
    	return this.endPathField;
    }
    
    public JButton getCopyButton() {
    	return this.copyButton;
    }
    
    public String getSelectedMask() {
    	return this.selectedMask;
    }
    
    public void setSelectedMask(String mask) {
        this.selectedMask = mask;
    }
}
