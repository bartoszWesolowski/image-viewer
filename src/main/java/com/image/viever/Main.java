package com.image.viever;

import com.image.viever.controller.MainController;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.ImageViewerFrame;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class Main {

    public static void main(String[] args) {
        ImageViewerFrame mainFrame = new ImageViewerFrame();
        ViewedImagesModel viewedImages = new ViewedImagesModel();

        MainController mainController = new MainController(mainFrame, viewedImages);
        mainController.openApp();
    }
}
