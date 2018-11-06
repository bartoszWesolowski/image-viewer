package com.image.viever.controller.menu;

import com.image.viever.ImageWrapper;
import com.image.viever.events.EventManager;
import com.image.viever.events.impl.ImageMdifiedEvent;
import com.image.viever.filters.Filter;
import com.image.viever.filters.FlipVerticallyFilter;
import com.image.viever.filters.MirrorFilter;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.utils.ImageModifier;
import com.image.viever.view.menu.ImageMenu;

public class ImageMenuController {

    private static final Filter MIRROR_FILTER = new MirrorFilter("Flip horizontally");

    public static final Filter FLIP_VERTICALLY = new FlipVerticallyFilter("Flip vertically");

    private final ImageModifier imageModifier = new ImageModifier();

    private ImageMenu imageMenu;

    private ViewedImagesModel viewedImagesModel;

    public ImageMenuController(final ImageMenu imageMenu, final ViewedImagesModel viewedImagesModel) {
        this.imageMenu = imageMenu;
        this.viewedImagesModel = viewedImagesModel;
    }

    public void init() {

        imageMenu.getRotateBy90Degrees().addActionListener(e -> rotate(90));

        imageMenu.getRotateBy180Degrees().addActionListener(e -> rotate(180));

        imageMenu.getFlipHorizontallyMenuItem().addActionListener(e -> {
            applyFilter(MIRROR_FILTER);
        });

        imageMenu.getFlipVerticallyMenuItem().addActionListener(e -> {
            applyFilter(FLIP_VERTICALLY);
        });
    }

    private void rotate(int angle) {
        if (viewedImagesModel.hasCurrentImage()) {
            ImageWrapper rotated = imageModifier.rotate(viewedImagesModel.getCurrentlyDisplayedImageVersion(), angle);
            EventManager.getInstance().fireEvent(new ImageMdifiedEvent(rotated));
        }
    }

    private void applyFilter(Filter filter) {
        if (viewedImagesModel.hasCurrentImage()) {
            ImageWrapper flipped = imageModifier.applyFilter(viewedImagesModel.getCurrentlyDisplayedImageVersion(), filter);
            EventManager.getInstance().fireEvent(new ImageMdifiedEvent(flipped));
        }
    }
}
