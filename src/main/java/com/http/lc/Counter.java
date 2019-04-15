package com.http.lc;

import com.http.lc.service.FileRunner;
import com.http.lc.service.StreamFilesUtility;
import com.http.lc.service.PathGuide;

import java.io.File;
import java.util.ArrayList;

public class Counter {
    private static ArrayList<File> allDotJavaFiles;
    private static ArrayList<File> allDotJSFiles;

    public static void main(String[] args) {
        PathGuide guide = new PathGuide();
        FileRunner filer = new FileRunner(guide.pave());
        StreamFilesUtility utility = new StreamFilesUtility();
        //noinspection InfiniteLoopStatement
        while (true) {
            long time = System.currentTimeMillis();
            filer.allFilesInit();
            allDotJavaFiles = StreamFilesUtility.clearSameFiles(filer.findSpecificFiles("java"));
//            allDotJSFiles = utility.clearSameFiles(filer.findSpecificFiles("js"));
            utility.printStatistic(allDotJavaFiles, "import", "package");
            System.out.println("Counting time: " + (System.currentTimeMillis() - time) + "mls");
//            utility.printStatistic(allDotJSFiles);
            filer.setStartFolder(guide.paveContinue());
        }
    }
}