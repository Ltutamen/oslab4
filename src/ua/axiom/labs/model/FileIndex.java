package ua.axiom.labs.model;

import java.sql.Timestamp;

/**
 * Generic data about file
 */
public class FileIndex implements Comparable<FileIndex> {
    private final int id;
    private String fname;
    private final int shift = 0;
    private Timestamp created;
    private final int size;
    private Timestamp lastModified;

    public FileIndex(int id, String fname, int size) {
        this.id = id;
        this.fname = fname;
        this.size = size;
    }

    public int getId() {
        return id;
    }


    public String getFname() {
        return fname;
    }

    public int getShift() {
        return shift;
    }

    public int getSize() {
        return size;
    }

    @Override
    public int compareTo(FileIndex o) {
        return this.fname.compareTo(o.getFname());
    }

    @Override
    public String toString() {
        return "fi:" + id;
    }
}
