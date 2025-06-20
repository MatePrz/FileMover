package pl.filemover.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import pl.filemover.ui.FileSelectorWindow;
import pl.filemover.utils.Messages;

public class FolderChooserListener implements ActionListener {
	private final FileSelectorWindow parent;
    private final boolean isStart;

    public FolderChooserListener(FileSelectorWindow parent, boolean isStart) {
        this.parent = parent;
        this.isStart = isStart;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle(isStart ? Messages.SRC_TITLE : Messages.DEST_TITLE);
        int returnVal = chooser.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String selectedPath = chooser.getSelectedFile().getAbsolutePath();
            if (isStart) {
                parent.getStartPathField().setText(selectedPath);
            } else {
                parent.getEndPathField().setText(selectedPath);
            }
        }
	}

}
