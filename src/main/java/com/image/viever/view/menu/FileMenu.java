package com.image.viever.view.menu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class FileMenu extends JMenu {

    private JMenuItem openMenuItem = new MenuItemWithShortcut("Open", KeyEvent.VK_O);

    private JMenuItem openExampleMenuItem = new MenuItemWithShortcut("Open Example", KeyEvent.VK_1);

    private JMenuItem closeMenuItem = new MenuItemWithShortcut("Close", KeyEvent.VK_W);

    private JMenuItem saveAsMenuItem = new MenuItemWithShortcut("Save as...", KeyEvent.VK_S);

    private JMenuItem quitMenuItem = new MenuItemWithShortcut("Quit", KeyEvent.VK_Q);

    public FileMenu() {
        super("File");
        add(openMenuItem);
        add(openExampleMenuItem);
        add(closeMenuItem);
        add(saveAsMenuItem);
        addSeparator();
        add(quitMenuItem);
    }
}
