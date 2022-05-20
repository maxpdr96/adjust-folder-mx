package com.hidarisoft.adjustfoldermx;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;


public class AdjustFolderMxApplication {
    private static final String DEFAULT_PATH = "F:\\Download";
    private static final String DEFAULT_PATH_DOWNLOAD = DEFAULT_PATH + "\\Opera";

    private static final String DEFAULT_PATH_AUDIO = DEFAULT_PATH_DOWNLOAD + "\\Audio\\";
    private static final String DEFAULT_PATH_VIDEO = DEFAULT_PATH_DOWNLOAD + "\\Video\\";
    private static final String DEFAULT_PATH_TEXTS = DEFAULT_PATH_DOWNLOAD + "\\Texts\\";
    private static final String DEFAULT_PATH_IMAGE = DEFAULT_PATH_DOWNLOAD + "\\Image\\";
    private static final String DEFAULT_PATH_COMPACT = DEFAULT_PATH_DOWNLOAD + "\\Compact\\";
    private static final String DEFAULT_PATH_EXEC = DEFAULT_PATH_DOWNLOAD + "\\Exec\\";

    public static void main(String[] args) {
        File files = new File(DEFAULT_PATH);

        Arrays.stream(Objects.requireNonNull(files.listFiles())).filter(AdjustFolderMxApplication::isFileDefault).forEach(file -> {

            String fileName = file.toString();

            if (file.isFile()) {
                String extension = "";
                int index = fileName.lastIndexOf('.');
                if (index > 0) {
                    extension = fileName.substring(index + 1);
                }
                moveFiles(extension, file);
            }

        });
    }

    private static Boolean isFileDefault(File file) {
        return !(file.getName().equals("Audio") || file.getName().equals("Image") || file.getName().equals("Texts") || file.getName().equals("Video") || file.getName().equals("Compact") || file.getName().equals("Exec"));
    }

    private static void moveFiles(String extension, File file) {
        System.out.println("File being moved: " + file.getName());
        if (extension.equals("pdf") || extension.equals("epub") || extension.equals("azw3") || extension.equals("mobi") || extension.equals("tif")) {
            file.renameTo(new File(DEFAULT_PATH_TEXTS + file.getName()));
        }

        if (extension.equals("mp4") || extension.equals("mkv") || extension.equals("avi")) {
            file.renameTo(new File(DEFAULT_PATH_VIDEO + file.getName()));
        }

        if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
            file.renameTo(new File(DEFAULT_PATH_IMAGE + file.getName()));
        }

        if (extension.equals("ogg") || extension.equals("mp3")) {
            file.renameTo(new File(DEFAULT_PATH_AUDIO + file.getName()));
        }

        if (extension.equals("rar") || extension.equals("zip") || extension.equals("torrent")) {
            file.renameTo(new File(DEFAULT_PATH_COMPACT + file.getName()));
        }

        if (extension.equals("exe")) {
            file.renameTo(new File(DEFAULT_PATH_EXEC + file.getName()));
        }

    }

}
