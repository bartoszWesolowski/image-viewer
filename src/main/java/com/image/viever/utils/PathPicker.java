package com.image.viever.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Optional;

public class PathPicker {

    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

    public Optional<File> getPath(Component parent) {
        int returnValue = fileChooser.showOpenDialog(parent);
        return Optional.of(returnValue)
                .filter(value -> JFileChooser.APPROVE_OPTION == value)
                .map(value -> fileChooser.getSelectedFile());

    }
}
