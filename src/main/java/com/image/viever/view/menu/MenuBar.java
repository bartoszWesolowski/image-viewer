package com.image.viever.view.menu;


import javax.swing.*;

/**
 * Created by Bartosz Wesolowski on 02.11.2018.
 */
public class MenuBar extends JMenuBar {

    private FileMenu fileMenu = new FileMenu();

    private EditMenu editMenu = new EditMenu();

    private ImageMenu imageMenu = new ImageMenu();

    private FilterMenu filterMenu = new FilterMenu();

    private HelpMenu helpMenu = new HelpMenu();

    public MenuBar() {
        super();
        add(fileMenu);
        add(editMenu);
        add(imageMenu);
        add(filterMenu);
        add(helpMenu);
    }

    public FileMenu getFileMenu() {
        return fileMenu;
    }

    public EditMenu getEditMenu() {
        return editMenu;
    }

    public ImageMenu getImageMenu() {
        return imageMenu;
    }

    public FilterMenu getFilterMenu() {
        return filterMenu;
    }

    @Override
    public HelpMenu getHelpMenu() {
        return helpMenu;
    }
}
