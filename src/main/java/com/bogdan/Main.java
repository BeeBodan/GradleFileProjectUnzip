package com.bogdan;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.core.ZipFile;
import org.apache.commons.io.FileUtils;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        //Copy src.zip in other directory
        File file = new File("C:\\Program Files\\Java\\jdk-11\\lib\\src.zip");
        File destinationDir = new File("test-directory");
        try {
            FileUtils.copyFileToDirectory(file, destinationDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Unzipping src.zip
        String source = "test-directory/src.zip";
        String destination = "test-directory/unzip/";
        try {
            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(destination);
        } catch (ZipException e) {
            e.printStackTrace();
        }

        //Found string in all files
        final File folder = new File("test-directory/unzip");
        listFilesForFolder(folder);
    }



    public static void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                try {
                    String absolutePath = fileEntry.getAbsolutePath();
                    searching(absolutePath, "@FunctionalInterface");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void searching(String name, String word) throws IOException {
        File file = new File(name);
        String[] words;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;

        while((string = bufferedReader.readLine()) != null) {
            words = string.split(" ");
            for (String oneWord : words) {
                if (oneWord.equals(word)) {
                    System.out.println(file.getName());
                }
            }
        }
        fileReader.close();
    }
}
