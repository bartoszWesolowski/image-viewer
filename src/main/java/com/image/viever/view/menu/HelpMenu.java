package com.image.viever.view.menu;

import javax.swing.*;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class HelpMenu extends JMenu {

    private JMenuItem help = new JMenuItem("Help");

    private JMenuItem generalInfo = new JMenuItem("About this app");

    public HelpMenu() {
        super("Help");
        add(help);
        add(generalInfo);
    }
}
