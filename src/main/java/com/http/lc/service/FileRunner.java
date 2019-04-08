package com.http.lc.service;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileRunner {
    @Getter
    private ArrayList<File> allFiles = new ArrayList<>();
    @Getter
    @Setter
    private File startFolder;

    public FileRunner(File startFolder) {
        this.startFolder = startFolder;
    }

    public FileRunner(String startFolderPath) {
        startFolder = new File(startFolderPath);
    }

    public void allFilesInit() {
        allFiles.clear();
        allFilesInit(startFolder);
    }

    private void allFilesInit(File folder) {
        File[] files = folder.listFiles();
        try {
            for (File file : Objects.requireNonNull(files)) {
                if (file.isDirectory()) {
                    allFilesInit(file);
                    continue;
                }
                allFiles.add(file);
            }
        } catch (NullPointerException ignored) {
        }
    }

    public ArrayList<File> findSpecificFiles(String extension) {
        return (ArrayList<File>) allFiles.stream().filter(file -> {
            try {
                String fileExtension = file.toString().substring(file.toString().lastIndexOf("."));
                return fileExtension.equals("." + extension);
            } catch (IndexOutOfBoundsException e){
                return false;
            }
        }).collect(Collectors.toList());
    }
}