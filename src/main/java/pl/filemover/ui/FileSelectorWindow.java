/**
 * Package containing class that handles the user interface window for selecting and copying files
 */
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

/**
 * 
 * @author Jakub Ceranowicz
 * @author Maksymilian Grzelecki
 * @author Mateusz Przybysz
 * @version 23.06.2025
 * A graphical user interface window for selecting files and directories to copy - 
 * JFrame implementation that handles file selection and copying operations
 */
@SuppressWarnings("serial")
public class FileSelectorWindow extends JFrame{
	/**
	 * Array of supported file masks for filtering files during copy operations
	 */
	private final String[] masks = Arrays.stream(FileMask.values()).map(FileMask::getPattern).toArray(String[]::new);
	/**
	 * Currently selected file mask pattern for filtering files
	 */
	private String selectedMask = FileMask.ALL.getPattern();
	
	// UI Components
	/**
	 * main panel
	 */
	private JPanel mainPanel;
	/**
	 * top panel
	 */
	private JPanel topPanel;
	/**
	 * bottom panel
	 */
	private JPanel bottomPanel;
	/**
	 * panel with the source path
	 */
	private JPanel startPathPanel;
	/**
	 * panel with the destination path
	 */
	private JPanel endPathPanel;
	/**
	 * panel with the file mask
	 */
	private JPanel maskPanel;
	
	/**
	 * text field for the source path
	 */
	private JTextField startPathField;
	/**
	 * text field for the destination path
	 */
	private JTextField endPathField;
	
	/**
	 * button with editable field for selecting file mask to copy
	 */
	private JComboBox<String> maskComoBox;
	
	/**
	 * button for selecting source path
	 */
	private JButton startPathSelectButton;
	/**
	 * button for selecting destination path
	 */
	private JButton endPathSelectButton;
	/**
	 * button for starting the process
	 */
	private JButton copyButton;

	/**
	 * Constructs a new FileSelectorWindow instance with predefined settings
	 */
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
    
    /**
     * Returns the text field containing the source path for copying files
     * @return source path
     */
    public JTextField getStartPathField() {
    	return this.startPathField;
    }
    
    /**
     * Returns the text field containing the destination path for copying files
     * @return destination path
     */
    public JTextField getEndPathField() {
    	return this.endPathField;
    }
    
    /**
     * Returns the copy operation trigger button
     * @return button that triggers the operation
     */
    public JButton getCopyButton() {
    	return this.copyButton;
    }
    
    /**
     * Returns the currently selected file mask pattern
     * @return selected file mask
     */
    public String getSelectedMask() {
    	return this.selectedMask;
    }
    
    /**
     * Sets the selected file mask pattern
     * @param mask selected file mask
     */
    public void setSelectedMask(String mask) {
        this.selectedMask = mask;
    }
}
