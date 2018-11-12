package com.image.viever.view;

import com.image.viever.utils.SwingHelper;

import javax.swing.*;
import java.awt.*;

public class ProgressBarFrame extends JFrame{

    private JProgressBar progressBar = new JProgressBar();

    private JTextArea messageBox = new JTextArea();

    private int numberOfOperationsToPerform = 0;

    private double numberOfPerformedOperations = 0;

    public ProgressBarFrame(String title, int numberOfOperationsToPerform) {
        super(title);
        this.numberOfOperationsToPerform = numberOfOperationsToPerform;

        Container content = getContentPane();
        progressBar.setStringPainted(true);
        content.add(progressBar, BorderLayout.NORTH);
        content.add(new JScrollPane(messageBox), BorderLayout.CENTER);
        setSize(300, 200);

        SwingHelper.centralizeOnScreen(this);
    }

    public void open() {
        setVisible(true);
    }

    public ProgressBarFrame addMessage(String message) {
        messageBox.append(message + "\n");
        return this;
    }

    public ProgressBarFrame operationCalculated() {
        numberOfPerformedOperations++;
        int progress = (int) (numberOfPerformedOperations * 100 / numberOfOperationsToPerform);
        progressBar.setValue(progress);
        return this;
    }
}
