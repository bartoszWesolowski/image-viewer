package com.image.viever;

import com.image.viever.filters.DarkerFilter;
import com.image.viever.filters.EdgeFilter;
import com.image.viever.filters.Filter;
import com.image.viever.filters.FishEyeFilter;
import com.image.viever.filters.FlipVerticallyFilter;
import com.image.viever.filters.GrayScaleFilter;
import com.image.viever.filters.InvertFilter;
import com.image.viever.filters.LighterFilter;
import com.image.viever.filters.MirrorFilter;
import com.image.viever.filters.PixelizeFilter;
import com.image.viever.filters.SmoothFilter;
import com.image.viever.filters.SolarizeFilter;
import com.image.viever.filters.ThresholdFilter;
import com.image.viever.view.ImagePanel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

/**
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * <p>
 * To start the application, create an object of this class.
 *
 * @author Michael Kölling and David J. Barnes.
 * @version 3.1
 */
public class ImageViewer {
    // static fields:
    private static final String VERSION = "Version 3.1";
    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    private static final String DEGREE = "\u00b0";

    // fields:
    private JFrame frame;
    private ImagePanel imagePanel;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    private ImageWrapper currentImage;
    private JScrollPane scrollPanel;
    private JFrame scFrame;
    private JSpinner spinnerW;
    private JSpinner spinnerH;
    private JLabel sizeLabel1;
    private JLabel sizeLabel2;
    private JButton sizeOK;
    private JButton sizeCancel;
    private JCheckBox sizeRatio;
    private HistoryManager historyMan;
    private JFrame rFrame;
    private JSlider rotateSlider;
    private JLabel rotateDegrees;
    private JButton rotateOK;

    boolean click;
    boolean changedH;
    boolean changedW;
    double zoom = 100;

    private List<Filter> filters;


    /**
     * Create an ImageViewer and display its GUI on screen.
     */
    public ImageViewer() {
        currentImage = null;
        historyMan = new HistoryManager();
        filters = createFilters();
        makeFrame();
        zoom = 100;
        //deactivateButtons();
    }


    // ---- implementation of menu functions ----

    /**
     * Open function: open a file chooser to select a new image file,
     * and then display the chosen image.
     */
    private void openFile() {
        int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;  // cancelled
        }
        File selectedFile = fileChooser.getSelectedFile();
        currentImage = ImageFileManager.loadImage(selectedFile);

        if (currentImage == null) {   // image file was not a valid image
            JOptionPane.showMessageDialog(frame,
                    "The file was not in a recognized image file format.",
                    "Image Load Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        historyMan.eraseAll();
        historyMan.add(currentImage);
        imagePanel.modifyDisplayedImage(currentImage);
        imagePanel.saveOriginal();
        showFilename(selectedFile.getPath());
        showStatus("File loaded.");
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
        scrollPanel.getViewport().revalidate();
        //activateButtons();
        resetSlider();
    }

    /**
     * Opens an example picture (monkey.jpg in project directory)
     */
    private void openExample() {
        currentImage = ImageFileManager.loadImage(new File("monkey.jpg"));
        historyMan.eraseAll();
        historyMan.add(currentImage);
        imagePanel.modifyDisplayedImage(currentImage);
        imagePanel.saveOriginal();
        showStatus("File loaded.");
        showFilename("Monkey bussiness!");
        frame.pack();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
        scrollPanel.getViewport().revalidate();
        resetSlider();
        frame.repaint();
    }

    /**
     * Close function: close the current image.
     */
    private void close() {
        currentImage = null;
        imagePanel.clearImage();
        showFilename(null);
        //deactivateButtons();
        resetSlider();
        scrollPanel.getViewport().revalidate();
        historyMan.eraseAll();
    }

    /**
     * Save As function: save the current image to a file.
     */
    private void saveAs() {
        if (currentImage != null) {
            int returnVal = fileChooser.showSaveDialog(frame);

            if (returnVal != JFileChooser.APPROVE_OPTION) {
                return;  // cancelled
            }
            File selectedFile = fileChooser.getSelectedFile();
            ImageFileManager.saveImage(currentImage, selectedFile);

            showFilename(selectedFile.getPath());
        }
    }


    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }

    /**
     * Apply a given filter to the current image.
     *
     * @param filter The filter object to be applied.
     */
    private void applyFilter(Filter filter) {
        if (currentImage != null) {
            ImageWrapper filtered = ImageWrapper.clone(currentImage);
            filter.apply(filtered);
            showStatus("Applied: " + filter.getName());
            currentImage = filtered;
            imagePanel.modifyDisplayedImage(currentImage);
            imagePanel.saveOriginal();
            scrollPanel.getViewport().revalidate();
            historyMan.add(currentImage);
            frame.repaint();
        } else {
            showStatus("No image loaded.");
        }
    }

    /**
     * Repaints main frame
     */
    private void refreshFrame() {
        frame.repaint();
    }

    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout() {
        JOptionPane.showMessageDialog(frame,
                "ImageViewer\n" + VERSION,
                "About ImageViewer",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Resize function
     */
    private void resize(int newWidth, int newHeight) {
        BufferedImage resized = new BufferedImage(newWidth, newHeight, currentImage.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(currentImage, 0, 0, newWidth, newHeight, 0, 0, currentImage.getWidth(),
                currentImage.getHeight(), null);
        g.dispose();
        ImageWrapper img = new ImageWrapper(resized, currentImage.getOriginalFile());
        currentImage = img;
        imagePanel.modifyDisplayedImage(currentImage);
        imagePanel.saveOriginal();
        scrollPanel.getViewport().revalidate();
        historyMan.add(currentImage);
        showStatus("Image resized");
    }

    /**
     * Rotate function
     */
    private void rotate(int angle) {
        if (currentImage != null) {
            double sin = Math.abs(Math.sin(Math.toRadians(angle)));
            double cos = Math.abs(Math.cos(Math.toRadians(angle)));

            int w = currentImage.getWidth();
            int h = currentImage.getHeight();

            int neww = (int) Math.floor(w * cos + h * sin);
            int newh = (int) Math.floor(h * cos + w * sin);

            BufferedImage rotated = new BufferedImage(neww, newh, currentImage.getType());
            Graphics2D g = rotated.createGraphics();

            g.translate((neww - w) / 2, (newh - h) / 2);
            g.rotate(Math.toRadians(angle), w / 2, h / 2);
            g.drawImage(currentImage, null, 0, 0);
            g.dispose();

            currentImage = new ImageWrapper(rotated, currentImage.getOriginalFile());
            imagePanel.modifyDisplayedImage(currentImage);
            imagePanel.saveOriginal();
            scrollPanel.getViewport().revalidate();
            historyMan.add(currentImage);
            showStatus("Rotated by " + angle + DEGREE);
        } else
            showStatus("No image loaded!");
    }

    private void resetSlider() {
        //click = true;
        //zoomSlider.setValue(100);
        //zoom = 100;
        //zoomValue.setText("" + zoom + "%");
    }
    // ---- support methods ----

    /**
     * Show the file name of the current image in the fills display label.
     * 'null' may be used as a parameter if no file is currently loaded.
     *
     * @param filename The file name to be displayed, or null for 'no file'.
     */
    private void showFilename(String filename) {
        if (filename == null) {
            filenameLabel.setText("No file displayed.");
        } else {
            filenameLabel.setText("File: " + filename);
        }
    }


    /**
     * Show a message in the status bar at the bottom of the screen.
     *
     * @param text The status message.
     */
    private void showStatus(String text) {
        statusLabel.setText(text);
    }


    /**
     * Create a list with all the known filters.
     *
     * @return The list of filters.
     */
    private List<Filter> createFilters() {
        List<Filter> filterList = new ArrayList<Filter>();
        filterList.add(new DarkerFilter("Darker"));
        filterList.add(new LighterFilter("Lighter"));
        filterList.add(new ThresholdFilter("Threshold"));
        filterList.add(new InvertFilter("Invert"));
        filterList.add(new SolarizeFilter("Solarize"));
        filterList.add(new SmoothFilter("Smooth"));
        filterList.add(new PixelizeFilter("Pixelize"));
        filterList.add(new GrayScaleFilter("Grayscale"));
        filterList.add(new EdgeFilter("Edge Detection"));
        filterList.add(new FishEyeFilter("Fish Eye"));
        return filterList;
    }

    // ---- Swing stuff to build the frame and all its components and menus ----

    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame() {

        frame = new JFrame("ImageViewer");
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));

        makeMenuBar(frame);

        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));

        // Create the image pane with scroll panes in the center
        imagePanel = new ImagePanel();
        scrollPanel = new JScrollPane(imagePanel);
        scrollPanel.setViewportView(imagePanel);
        scrollPanel.setBorder(new EtchedBorder());
        contentPane.add(scrollPanel, BorderLayout.CENTER);

        // Create two labels at top and bottom for the file name and status messages
        filenameLabel = new JLabel();
        contentPane.add(filenameLabel, BorderLayout.NORTH);

        statusLabel = new JLabel(VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        //Create a panel with two buttons, a slider and a label for zooming on right
        //ZoomPanel zoomPanel = new ZoomPanel();
        ///ZoomController zoomController = new ZoomController(zoomPanel, new ImageScrollPanel(imagePanel));
        ///zoomController.init();

       // contentPane.add(zoomPanel, BorderLayout.EAST);


        // building is done - arrange the components      
        showFilename(null);
        frame.pack();

        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Create the main frame's menu bar.
     *
     * @param frame The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame) {
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem item;

        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);

        item = new JMenuItem("Open...");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        menu.add(item);
        item = new JMenuItem("Open Example");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openExample();
            }
        });
        menu.add(item);

        item = new JMenuItem("Close");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("Save As...");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAs();
            }
        });
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });
        menu.add(item);

        //create edit menu
        menu = new JMenu("Edit");
        menubar.add(menu);

        item = new JMenuItem("Undo");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                historyMan.undo();
                ImageWrapper img = historyMan.getCurrentVersion();
                currentImage = img;
                imagePanel.modifyDisplayedImage(img);
                imagePanel.saveOriginal();
                refreshFrame();
                scrollPanel.getViewport().revalidate();

            }
        });
        menu.add(item);

        item = new JMenuItem("Redo");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, SHORTCUT_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                historyMan.redo();
                ImageWrapper img = historyMan.getCurrentVersion();
                currentImage = img;
                imagePanel.modifyDisplayedImage(img);
                imagePanel.saveOriginal();
                refreshFrame();
                scrollPanel.getViewport().revalidate();
            }
        });
        menu.add(item);


        //create the image edit menu
        menu = new JMenu("Image");
        menubar.add(menu);

        item = new JMenuItem("Image size");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeSizeChangeFrame();
            }
        });
        menu.add(item);

        item = new JMenuItem("Rotate by 90 CW");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rotate(90);
            }
        });
        menu.add(item);

        item = new JMenuItem("Rotate by 180");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rotate(180);
            }
        });
        menu.add(item);

        item = new JMenuItem("Rotate...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeRotateFrame();
            }
        });
        menu.add(item);

        item = new JMenuItem("Flip horizontally");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyFilter(new MirrorFilter("Flip horizontally"));
            }
        });
        menu.add(item);

        item = new JMenuItem("Flip vertically");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                applyFilter(new FlipVerticallyFilter("Flip vertically"));
            }
        });
        menu.add(item);


        // create the Filter menu
        menu = new JMenu("Filter");
        menubar.add(menu);

        for (final Filter filter : filters) {
            item = new JMenuItem(filter.getName());
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    applyFilter(filter);
                }
            });
            menu.add(item);
        }

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);

        item = new JMenuItem("About ImageViewer...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAbout();
            }
        });
        menu.add(item);
    }

    private void makeSizeChangeFrame() {
        if (currentImage != null) {
            scFrame = new JFrame("Change image size");
            JPanel contentPane = (JPanel) scFrame.getContentPane();
            contentPane.setLayout(new GridLayout(0, 3, 10, 5));

            //Creates a spinner with text forms and labels for changing picture size
            sizeLabel1 = new JLabel("Width:");
            contentPane.add(sizeLabel1);

            int height = currentImage.getHeight();
            int width = currentImage.getWidth();

            spinnerW = new JSpinner(new SpinnerNumberModel(width, 1, 4000, 1));
            spinnerW.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent arg0) {
                    if (changedH) {
                        changedH = false;
                        return;
                    }
                    if (sizeRatio.isSelected()) {
                        click = true;
                        double ratio = new Double(currentImage.getWidth()) /
                                new Double(currentImage.getHeight());
                        double newHeight = ((Integer) spinnerW.getValue()) / ratio;
                        int newVal = new Double(newHeight).intValue();
                        spinnerH.setValue(newVal);
                        changedW = true;
                    }

                }
            });
            contentPane.add(spinnerW);

            sizeOK = new JButton("OK");
            sizeOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int newW = (Integer) spinnerW.getValue();
                    int newH = (Integer) spinnerH.getValue();
                    resize(newW, newH);
                    scFrame.dispose();
                }
            });
            contentPane.add(sizeOK);

            sizeLabel2 = new JLabel("Height:");
            contentPane.add(sizeLabel2);

            spinnerH = new JSpinner(new SpinnerNumberModel(height, 1, 4000, 1));
            spinnerH.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (changedW) {
                        changedW = false;
                        return;
                    }
                    if (sizeRatio.isSelected()) {
                        click = true;
                        double ratio = new Double(currentImage.getWidth())
                                / new Double(currentImage.getHeight());
                        double newWidth = ((Integer) spinnerH.getValue()) * ratio;
                        int newVal = new Double(newWidth).intValue();
                        spinnerW.setValue(newVal);
                        changedH = true;
                    }
                }
            });
            contentPane.add(spinnerH);

            sizeCancel = new JButton("Cancel");
            sizeCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scFrame.dispose();
                }
            });
            contentPane.add(sizeCancel);

            JPanel empty = new JPanel();
            contentPane.add(empty);

            sizeRatio = new JCheckBox("Constrain proportion", true);
            contentPane.add(sizeRatio);


            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            scFrame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
            scFrame.pack();
            scFrame.setVisible(true);
        } else
            showStatus("No image loaded");

    }

    private void makeRotateFrame() {
        if (currentImage != null) {
            rFrame = new JFrame("Rotate by degrees");
            JPanel contentPane = (JPanel) rFrame.getContentPane();
            contentPane.setLayout(new GridLayout(0, 3, 10, 5));

            rotateSlider = new JSlider(0, 360, 0);
            rotateSlider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    rotateDegrees.setText("" + rotateSlider.getValue() + DEGREE);
                    ;
                }
            });
            contentPane.add(rotateSlider);

            rotateDegrees = new JLabel("" + rotateSlider.getValue() + DEGREE);
            contentPane.add(rotateDegrees);

            rotateOK = new JButton("Apply");
            rotateOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int degrees = rotateSlider.getValue();
                    rotate(degrees);
                    rFrame.dispose();
                }
            });
            contentPane.add(rotateOK);

            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            rFrame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
            rFrame.pack();
            rFrame.setVisible(true);

        } else
            showStatus("No image loaded");
    }
}
