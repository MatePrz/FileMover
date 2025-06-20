package pl.filemover.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pl.filemover.services.CopyThread;
import pl.filemover.ui.FileSelectorWindow;
import pl.filemover.utils.Messages;

public class CopyActionListener implements ActionListener{
	private final FileSelectorWindow parent;

    public CopyActionListener(FileSelectorWindow parent) {
        this.parent = parent;
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton copyButton = parent.getCopyButton();
		copyButton.setEnabled(false);

        String srcPath = parent.getStartPathField().getText();
        String destPath = parent.getEndPathField().getText();
        String mask = parent.getSelectedMask();
        
        if (srcPath.isEmpty() || destPath.isEmpty()) {
            JOptionPane.showMessageDialog(parent, Messages.PATHS_EMPTY_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        File src = new File(srcPath);
        File dest = new File(destPath);
        
        if (srcPath.equalsIgnoreCase(destPath)) {
            JOptionPane.showMessageDialog(parent, Messages.PATHS_SAME_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        if (!src.exists() || !src.isDirectory()) {
            JOptionPane.showMessageDialog(parent, Messages.SRC_PATH_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        if (dest.getName().contains(".")) {
            JOptionPane.showMessageDialog(parent, Messages.DEST_PATH_ERROR, Messages.ERROR_LABEL, JOptionPane.ERROR_MESSAGE);
            copyButton.setEnabled(true);
            return;
        }
        
        new CopyThread(src, dest, mask, copyButton).start();
	}

}
