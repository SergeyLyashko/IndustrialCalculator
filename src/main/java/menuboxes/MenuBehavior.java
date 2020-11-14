package menuboxes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBehavior implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        MenuSelectable source = (MenuSelectable) event.getSource();
        String currentMenu = source.getCurrentMenu();
        source.actionMenu(currentMenu);
    }
}
