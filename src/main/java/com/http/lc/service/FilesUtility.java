package com.http.lc.service;

import com.http.lc.entity.Statistic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class FilesUtility implements FilesUtilityInterface {

    public ArrayList<File> clearSameFiles(ArrayList<File> fileList) {
        ArrayList<File> nonRepeatingList = new ArrayList<>();
        int b = 1;
        boolean isRepeat;

        for (File file : fileList) {
            isRepeat = false;
            for (int i = b; i < fileList.size(); i++) {
                if (FilesUtilityInterface.readFileName(file)
                        .equals(FilesUtilityInterface.readFileName(fileList.get(i)))) {
                    isRepeat = true;
                    break;
                }
            }
            b++;
            if (!isRepeat) {
                nonRepeatingList.add(file);
            }
        }
        return nonRepeatingList;
    }


    public ArrayList<String> convertToStringList(ArrayList<File> files) {
        ArrayList<String> filesNames = new ArrayList<>();
        for (File file : files) {
            filesNames.add(FilesUtilityInterface.readFileName(file));
        }
        return filesNames;
    }

    public int countUpLines(ArrayList<File> files, String... unreadLines) throws IOException {
        int linesCount = 0;

        for (File file : files) {
            linesCount = countUpLinesAtFile(file, unreadLines);
        }
        return linesCount;
    }


    @Override
    public void printStatistic(ArrayList<File> files, String... unreadLines) {
        int linesCount;
        long charsCount;
        long allCharsCount = 0;
        int allLinesCount = 0;
        List<Statistic> statistics = new ArrayList<>();

        for (File file : files) {
            try {
                charsCount = countUpCharsAtFile(file, unreadLines);
                linesCount = countUpLinesAtFile(file, unreadLines);

                statistics.add(new Statistic(FilesUtilityInterface.readFileName(file), linesCount, charsCount));
                allLinesCount += linesCount;
                allCharsCount += charsCount;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        statistics.sort(Comparator.comparing(Statistic::getLinesCount));
//        Collections.reverse(statistics);
        System.out.println(statistics);

        System.out.println("All lines Count: " + allLinesCount);
        System.out.println("All characters count: " + allCharsCount);
    }

    private int countUpLinesAtFile(File file, String... unreadableLines) throws IOException {
        return (int) Files.lines(Paths.get(file.getCanonicalPath()))
                .filter(n -> isReadableLine(n, unreadableLines))
                .count();
    }

    private long countUpCharsAtFile(File file, String... unreadableLines) throws IOException {
        return Files.lines(Paths.get(file.getCanonicalPath()))
                .filter(n -> isReadableLine(n, unreadableLines))
                .flatMapToInt(line ->
                        line.trim().chars())
                .count();
    }

    private boolean isReadableLine(String line, String... unreadLines) {
        if (line.isEmpty()) {
            return false;
        }
        for (String unreadLine : unreadLines) {
            if (line.contains(unreadLine)) {
                return false;
            }
        }
        return true;
    }
}
