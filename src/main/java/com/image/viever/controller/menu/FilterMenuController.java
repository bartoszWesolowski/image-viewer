package com.image.viever.controller.menu;

import com.image.viever.ImageWrapper;
import com.image.viever.events.EventManager;
import com.image.viever.events.impl.ImageMdifiedEvent;
import com.image.viever.filters.DarkerFilter;
import com.image.viever.filters.EdgeFilter;
import com.image.viever.filters.Filter;
import com.image.viever.filters.FishEyeFilter;
import com.image.viever.filters.GrayScaleFilter;
import com.image.viever.filters.InvertFilter;
import com.image.viever.filters.LighterFilter;
import com.image.viever.filters.PixelizeFilter;
import com.image.viever.filters.SmoothFilter;
import com.image.viever.filters.SolarizeFilter;
import com.image.viever.filters.ThresholdFilter;
import com.image.viever.model.ViewedImagesModel;
import com.image.viever.utils.ImageModifier;
import com.image.viever.view.menu.FilterMenu;
import com.image.viever.view.menu.ImageMenu;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class FilterMenuController {

    private static final List<Filter> FILTERS = Arrays.asList(
            new DarkerFilter("Darker"),
            new LighterFilter("Lighter"),
            new ThresholdFilter("Threshold"),
            new InvertFilter("Invert"),
            new SolarizeFilter("Solarize"),
            new SmoothFilter("Smooth"),
            new PixelizeFilter("Pixelize"),
            new GrayScaleFilter("Grayscale"),
            new EdgeFilter("Edge Detection"),
            new FishEyeFilter("Fish Eye")
    );

    private final ImageModifier imageModifier = new ImageModifier();

    private FilterMenu filterMenu;

    private ViewedImagesModel viewedImagesModel;

    public FilterMenuController(final FilterMenu filterMenu, final ViewedImagesModel viewedImagesModel) {
        this.filterMenu = filterMenu;
        this.viewedImagesModel = viewedImagesModel;
    }

    public void init() {
        FILTERS.forEach(filter -> createFilterItem(filter));
    }

    private void createFilterItem(final Filter filter) {
        JMenuItem filterItem = new JMenuItem(filter.getName());
        filterItem.addActionListener(e -> {
            ImageWrapper modified = imageModifier.applyFilter(viewedImagesModel.getCurrentBaseImageVersion(), filter);
            EventManager.getInstance().fireEvent(new ImageMdifiedEvent(modified));
        });
        filterMenu.add(filterItem);
    }
}
