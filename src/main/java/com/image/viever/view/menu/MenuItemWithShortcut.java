package com.image.viever.view.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bartosz Wesolowski on 02.11.2018.
 */
public class MenuItemWithShortcut extends JMenuItem {

    private static final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    public MenuItemWithShortcut(final String text, int keyEventId) {
        super(text);
        setAccelerator(KeyStroke.getKeyStroke(keyEventId, SHORTCUT_MASK));
    }
}
