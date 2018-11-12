package com.image.viever.controller.menu;

import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.menu.GalleriesMenu;

public class GalleriesMenuController {

    private GalleriesMenu galleriesMenu;

    public GalleriesMenuController(final GalleriesMenu galleriesMenu) {
        this.galleriesMenu = galleriesMenu;
    }

    public void init() {
        galleriesMenu.getCopyGallery().addActionListener(e -> {
            CopyGalleryFrameController copyGalleryFrameController = new CopyGalleryFrameController();
            copyGalleryFrameController.open();
        });
    }

}
