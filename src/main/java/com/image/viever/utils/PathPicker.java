package com.image.viever.utils;

import com.image.viever.model.UserSettingsManager;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Optional;

public class PathPicker {

    private static JFileChooser FILE_CHOOSER = new JFileChooser(getFileChooserPath());

    private static JFileChooser DIRECTORY_CHOOSER = new JFileChooser(getFileChooserPath());

    static {
        DIRECTORY_CHOOSER.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }
    private static String getFileChooserPath() {
        String defaultImagesPath = UserSettingsManager.getInstance()
                .getSettings()
                .getDefaultImagesPath();
        return StringUtils.defaultString(defaultImagesPath, System.getProperty("user.dir"));
    }

    public Optional<File> getPath(Component parent) {
        int returnValue = FILE_CHOOSER.showOpenDialog(parent);
        return Optional.of(returnValue)
                .filter(value -> JFileChooser.APPROVE_OPTION == value)
                .map(value -> FILE_CHOOSER.getSelectedFile());

    }
    public Optional<File> getDirectory(Component parent) {
        int returnValue = DIRECTORY_CHOOSER.showOpenDialog(parent);
        return Optional.of(returnValue)
                .filter(value -> JFileChooser.APPROVE_OPTION == value)
                .map(value -> DIRECTORY_CHOOSER.getSelectedFile());

    }
}
