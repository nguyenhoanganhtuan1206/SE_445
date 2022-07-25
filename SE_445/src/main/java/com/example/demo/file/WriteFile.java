package com.example.demo.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteFile {
    /* Write file */
    public void writeFile(String filePath , List<Object> listObjects , boolean append) {
        File file = new File(filePath);
        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file , append));
            for (Object o : listObjects) {
                bufferedWriter.write(o.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
