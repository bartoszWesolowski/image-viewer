package com.image.viever.utils;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;

public final class MessagesPresenter {

    private Component parent;

    public MessagesPresenter(final Component parent) {
        this.parent = parent;
    }

    public void showInfoDialog(String title, String message) {
        showMessage(title, message, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showWarningDialog(String title, String message) {
        showMessage(title, message, JOptionPane.WARNING_MESSAGE);
    }

    public void showErrorDialog(String title, String message) {
        showMessage(title, message, JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(parent,
                message,
                title,
                messageType);
    }
}
