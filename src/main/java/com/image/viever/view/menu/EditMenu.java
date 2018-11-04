package com.image.viever.view.menu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditMenu extends JMenu {

    private JMenuItem undoMenuItem = new MenuItemWithShortcut("Undo", KeyEvent.VK_Z);

    private JMenuItem redoMenuItem = new MenuItemWithShortcut("Redo", KeyEvent.VK_Y);

    public EditMenu() {
        super("Edit");
        add(undoMenuItem);
        add(redoMenuItem);
    }
}
