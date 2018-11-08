package com.image.viever.controller.actionpanel;

import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.ImageViewerFrame;
import com.image.viever.view.actionpanel.ActionPanel;

public class ActionPanelController {

    private ActionPanel actionPanel;

    private ViewedImagesModel viewedImages;

    public ActionPanelController(final ActionPanel actionPanel, final ViewedImagesModel viewedImages) {
        this.actionPanel = actionPanel;
        this.viewedImages = viewedImages;
    }

    public void init() {
        ZoomController zoomController = new ZoomController(actionPanel.getZoomPanel());
        zoomController.init();

        SwitchImagePanelController switchImagePanelController = new SwitchImagePanelController(actionPanel.getSwitchImagePanel(), viewedImages);
        switchImagePanelController.init();

        GalleryPanelController galleryPanelController = new GalleryPanelController(actionPanel.getGalleryPanel(), viewedImages);
        galleryPanelController.init();
    }
}
