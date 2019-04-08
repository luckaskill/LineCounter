package com.http.lc.service;


import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PathGuide implements PaveInterface{
    @Getter
    private String path = "";
    private File direction = null;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public File pave() {
        localDiscChoose();
        printFolderContent(path);
        return run();
    }

    public File paveContinue() {
        if (direction == null) {
            return pave();
        } else {
            direction = null;
        }
        printFolderContent(path);
        return run();
    }

    private File run() {
        while (true) {
            implInput(getInput());
            if (direction != null) {
                return direction;
            }
            path += "\\";
            printFolderContent(path);
        }
    }

    private String getInput() {
        return scanner.nextLine();
    }

    private void implInput(String input) {
        if (input.equals("-do")) {
            direction = new File(path);
            return;
        }
        if (input.equals("-re")) {
            this.path = "";
            localDiscChoose();
            return;
        }
        if (input.equals("../")) {
            folderBack();
            return;
        }
        openIfExists(input);
    }

    private void openIfExists(String path) {
        String newPath = this.path + path;
        File file = new File(newPath);
        if (!(file.exists())) {
            return;
        }
        try {
            this.path = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFolderContent(String path) {
        File[] files = new File(path).listFiles();
        if (files == null) {
            System.out.println("Empty folder");
            return;
        }
        for (File file : files) {
            System.out.println(file);
        }
    }

    private void folderBack() {
        path = path.substring(0, path.lastIndexOf("\\"));
        path = path.substring(0, path.lastIndexOf("\\"));
    }


    private void localDiscChoose() {
        String discName;
        File[] roots = File.listRoots();
        System.out.println(Arrays.toString(roots));
        do {
            discName = scanner.nextLine();
            for (File file : roots) {
                if (file.toString().equals(discName.toUpperCase())) {
                    path += file.toString();
                    return;
                }
                if (file.toString().equals(discName.toUpperCase() + ":\\")) {
                    path += file.toString();
                    return;
                }
            }
            System.out.println("Wrong disc name");
        } while (true);
    }
}
