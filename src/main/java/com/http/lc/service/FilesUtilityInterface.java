package com.http.lc.service;

import java.io.File;
import java.util.ArrayList;

public interface FilesUtilityInterface {
    void printStatistic(ArrayList<File> files, String... unreadLines);

    static String readFileName(File path) {
        return path.toString().substring(path.toString().lastIndexOf("\\") + 1);
    }
}
