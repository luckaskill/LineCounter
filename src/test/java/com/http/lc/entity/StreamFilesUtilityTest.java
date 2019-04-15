package com.http.lc.entity;

import com.http.lc.service.FileRunner;
import com.http.lc.service.StreamFilesUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class StreamFilesUtilityTest {
    private static StreamFilesUtility streamFilesUtility;
    private static FileRunner runnerProjects;
    private static FileRunner runnerWebLib;

    @BeforeAll
    private static void setUp() {
        streamFilesUtility = new StreamFilesUtility();
        runnerProjects = new FileRunner("C:\\Projects");
        runnerWebLib = new FileRunner("C:\\Projects\\weblib");
    }

    @Test
    void printStatistic() {
        runnerProjects.allFilesInit();
        ArrayList<File> files = StreamFilesUtility.clearSameFiles(runnerProjects.findSpecificFiles("java"));
        streamFilesUtility.printStatistic(files, "import", "package");
    }

    @Test
    void charsRead() throws IOException {
        String str = "www\n" +
                "ddd";
        File file = new File("C:\\Projects\\restlib\\src\\main\\java\\com\\http\\webservice\\Application.java");
        System.out.println(Files.lines(Paths.get(file.getCanonicalPath()))
                .flatMapToInt(String::chars)
                .count());
    }
}