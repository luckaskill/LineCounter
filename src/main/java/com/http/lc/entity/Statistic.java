package com.http.lc.entity;

import com.http.lc.service.FilesUtilityInterface;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class Statistic {
    private String fileName;
    private int linesCount;
    private long charsCount;

    public Statistic(File file, int linesCount, long charsCount) {
        this.fileName = FilesUtilityInterface.readFileName(file);
        this.linesCount = linesCount;
        this.charsCount = charsCount;
    }

    @Override
    public String toString() {
        return "File name: " + fileName + "\n" +
                "Lines count: " + linesCount + "\n" +
                "Characters count: " + charsCount + "\n\n";
    }
}
