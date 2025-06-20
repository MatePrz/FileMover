package pl.filemover.ui.listeners;

import javax.swing.JComboBox;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class MaskSelectPopupListener implements PopupMenuListener {
	private final JComboBox<String> comboBox;

    public MaskSelectPopupListener(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }
    
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		comboBox.getEditor().setItem("");
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {}

}
