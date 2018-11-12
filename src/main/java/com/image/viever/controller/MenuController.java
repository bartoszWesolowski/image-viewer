package com.image.viever.controller;

import com.image.viever.controller.actionpanel.GalleryPanelController;
import com.image.viever.controller.menu.CopyGalleryFrameController;
import com.image.viever.controller.menu.FileMenuController;
import com.image.viever.controller.menu.FilterMenuController;
import com.image.viever.controller.menu.GalleriesMenuController;
import com.image.viever.controller.menu.ImageMenuController;
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

        ImageMenuController imageMenuController = new ImageMenuController(menuBar.getImageMenu(), viewedImagesModel);
        imageMenuController.init();

        FilterMenuController filterMenuController = new FilterMenuController(menuBar.getFilterMenu(), viewedImagesModel);
        filterMenuController.init();

        GalleriesMenuController galleriesMenuController = new GalleriesMenuController(menuBar.getGalleriesMenu());
        galleriesMenuController.init();
    }
}
