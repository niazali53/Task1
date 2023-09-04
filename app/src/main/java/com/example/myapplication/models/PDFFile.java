package com.example.myapplication.models;

public class PDFFile {

    private String name;
    private String path;

    public PDFFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

}
