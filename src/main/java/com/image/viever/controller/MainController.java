package com.image.viever.controller;

import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.ImageViewerFrame;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class MainController {

    private ImageViewerFrame mainFrame;

    private ViewedImagesModel viewedImages;

    public MainController(final ImageViewerFrame mainFrame, final ViewedImagesModel viewedImages) {
        this.mainFrame = mainFrame;
        this.viewedImages = viewedImages;
    }

    public void openApp(){
        this.initControllers();
        this.mainFrame.setVisible(true);
    }

    private void initControllers() {
        ZoomController zoomController = new ZoomController(mainFrame);
        zoomController.init();


    }
}
