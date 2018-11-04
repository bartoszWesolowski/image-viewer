package com.image.viever.view.menu;

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

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterMenu extends JMenu {


    public final List<Filter> filterList = Arrays.asList(
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

    public FilterMenu() {
        super("Filter");
    }
}
