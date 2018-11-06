package com.image.viever.controller.menu;

import com.image.viever.model.ViewedImagesModel;
import com.image.viever.view.menu.EditMenu;

/**
 * Created by Bartosz Wesolowski on 04.11.2018.
 */
public class EditMenuController {

    private EditMenu editMenu;

    private ViewedImagesModel viewedImagesModel;

    public EditMenuController(final EditMenu editMenu, final ViewedImagesModel viewedImagesModel) {
        this.editMenu = editMenu;
        this.viewedImagesModel = viewedImagesModel;
    }
}
