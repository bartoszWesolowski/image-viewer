package com.image.viever.controller;

import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.menu.MenuBar;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class MenuController {

    private MenuBar menuBar;

    private ViewedImagesModel viewedImagesModel;

    public MenuController(final MenuBar menuBar, final ViewedImagesModel viewedImagesModel) {
        this.menuBar = menuBar;
        this.viewedImagesModel = viewedImagesModel;
    }

    public void init() {
        setActionListeners();
    }

    private void setActionListeners() {
        FileMenuController fileMenuController = new FileMenuController(menuBar.getFileMenu(), viewedImagesModel);
        fileMenuController.init();

    }
}
