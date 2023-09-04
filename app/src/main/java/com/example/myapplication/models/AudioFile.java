package com.example.myapplication.models;

public class AudioFile {

    private String name; // Name of the audio file
    private String path; // File path of the audio file
    private long duration; // Duration of the audio file in milliseconds

    public AudioFile(String name, String path) {
        this.name = name;
        this.path = path;
        this.duration = duration;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public long getDuration() {
        return duration;
    }

    // Setter methods (if needed)
    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
