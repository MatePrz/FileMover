package pl.filemover.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import pl.filemover.ui.FileSelectorWindow;

public class MaskSelectActionListener implements ActionListener {
	private final FileSelectorWindow parent;
    private final JComboBox<String> comboBox;
    
    public MaskSelectActionListener(FileSelectorWindow parent, JComboBox<String> comboBox) {
        this.parent = parent;
        this.comboBox = comboBox;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		Object item = comboBox.getSelectedItem();
        if (item != null) {
        	parent.setSelectedMask(item.toString().toLowerCase().trim());
        }
	}

}
