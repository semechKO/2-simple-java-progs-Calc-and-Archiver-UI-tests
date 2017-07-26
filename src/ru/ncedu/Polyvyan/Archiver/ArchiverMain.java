package ru.ncedu.Polyvyan.Archiver;

/**
 * Created by Dmitriy on 27.08.2016.
 */
public class ArchiverMain extends Archiver {
    public static void main(String[] args) {
        Archiver archiver = new Archiver();
        try {
            archiver.run();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}