package com.image.viever.model;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class UserSettingsManager {

    private static final UserSettingsManager INSTANCE = new UserSettingsManager();

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSettingsManager.class);

    private static final String SETTINGS_FILE_NAME = "settings.json";

    private static final String USER_SETTINGS_DIRECTORY = Joiner.on(File.separator)
            .join(System.getProperty("user.home"), "image-viever");

    private static final String USER_SETTINGS_FILE_PATH = Joiner.on(File.separator)
            .join(USER_SETTINGS_DIRECTORY, SETTINGS_FILE_NAME);


    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static final UserSettingsManager getInstance() {
        return INSTANCE;
    }

    private UserSettings userSettings;

    public UserSettings getSettings() {
        if (userSettings != null) {
            return userSettings;
        }
        UserSettings userSettings = new UserSettings();
        try {
            userSettings = getOrCreateEmpty();
        } catch (IOException e) {
            LOGGER.error("Failed to read/create settings", e);
        }
        this.userSettings = userSettings;
        return userSettings;
    }

    public Optional<ImageGallery> getGallery(String id) {
        return getSettings()
                .getImageGalleries()
                .stream()
                .filter(imageGallery -> StringUtils.equals(id, imageGallery.getId()))
                .findFirst();
    }

    public void addFileToGallery(String groupId, String file) throws UserSettingsModificationException {
        UserSettings us = getSettings();
        us.getImageGalleries().stream()
                .filter(g -> StringUtils.equals(g.getId(), groupId))
                .findFirst()
                .ifPresent(g -> g.addPath(file));
        save(us);
    }

    public void createGallery(String label) throws UserSettingsModificationException {
        ImageGallery imageGallery = new ImageGallery(label);
        UserSettings settings = getSettings();
        settings.getImageGalleries().add(imageGallery);
        this.save(settings);
    }
    private UserSettings getOrCreateEmpty() throws IOException {
        UserSettings userSettings = new UserSettings();
        File settingsFile = new File(USER_SETTINGS_FILE_PATH);
        if (settingsFile.exists()) {
            userSettings = readUserSettings(settingsFile);
        } else {
            saveSettings(userSettings);
        }
        return userSettings;
    }

    private UserSettings readUserSettings(final File settingsFile) throws IOException {
        String settings = IOUtils.toString(settingsFile.toURI(), StandardCharsets.UTF_8);
        return GSON.fromJson(settings, UserSettings.class);
    }

    private void save(UserSettings userSettings) throws UserSettingsModificationException {

        try {
            saveSettings(userSettings);
        } catch (IOException e) {
            LOGGER.error("Failed to save settings", e);
            throw new UserSettingsModificationException("Failed to modify user settings");
        }
    }

    private void saveSettings(UserSettings userSettings) throws IOException {
        new File(USER_SETTINGS_DIRECTORY).mkdirs();
        File settingsFile = new File(USER_SETTINGS_FILE_PATH);
        settingsFile.createNewFile();
        try (Writer writer = new FileWriter(settingsFile)) {
            GSON.toJson(userSettings, writer);
        }
    }
}
