package ru.ncedu.Polyvyan.Archiver;

/**
 * Created by Dmitriy on 27.08.2016.
 */
import java.io.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by dpolyvyan on 26.08.2016.
 */
public class Archiver {

    BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
    private File path = new File(System.getProperty("user.dir"));
    byte[] buffer = new byte[1024];
    private char operation;
    private List<String> files_list = new LinkedList<>();

    /**
     * Method runs class methods in order and choose main operation depending on operation number.
     */
    public void run() throws Exception {
        while (true) {
            try {
                Archiver archiver = new Archiver();
                switch (archiver.operationChoice()) {
                    case '1':
                        zip_archive_creator();
                        break;
                    case '2':
                        zip_extract();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Method sets operation number from user input and returns it
     *@return char operation
     */
    public Character operationChoice() throws Exception {

        System.out.println("Выберите действие (введите 1 или 2):" + "\n" + "1.Архивировать файлы" + "\n" + "2.Разархивировать");
        String sIn = d.readLine();
        if (sIn.length() == 1 && (sIn.charAt(0) == '1' || sIn.charAt(0) == '2')) {
            char operation = sIn.charAt(0);
            switch (operation) {
                case '1':
                    return this.operation = '1';
                case '2':
                    return this.operation = '2';
            }
        } else throw new Exception("Неверно введено действие" + "\n");
        return null;
    }

    /**
     * Method checks if given string is zip filename
     * @param test
     *@return true or false
     */
    public boolean isZip(String test) {
        if (test.matches("([^\\s]+(\\.(?i)(zip))$)")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method checks if given string is legit filename
     * @param test
     *@return true or false
     */
    public boolean isFileName(String test) {
        if (test.matches("^[^*&%\\s]+$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method adds given files to file list if they are proper
     * @param files
     */
    public void setFiles_list(String[] files) {
        for (String file : files) {
            File test_is_file = new File(file);
            if (isFileName(file) && test_is_file.isFile() && !isZip(file)) {
                this.files_list.add(file);
            }
        }
    }

    /**
     * Method creates zip archive in the working directory
     */
    public void zip_archive_creator() throws IOException {
        System.out.println("Введите имя архива:");
        String archive_name = d.readLine();
        if (isFileName(archive_name)) {
            if (archive_name.endsWith(".zip") || archive_name.endsWith(".ZIP")) {
            } else {
                archive_name = archive_name + ".zip";
            }
            File archive = new File(this.path, archive_name);
            FileOutputStream fos = new FileOutputStream(archive);
            ZipOutputStream zos = new ZipOutputStream(fos);
            String[] files = this.path.list();
            setFiles_list(files);
            int len;
            for (String filename : this.files_list) {
                ZipEntry ze = new ZipEntry(filename);
                zos.putNextEntry(ze);
                FileInputStream in = new FileInputStream(filename);
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                in.close();
                zos.closeEntry();
            }
            zos.close();
            System.out.println("\nАрхив успешно создан в рабочей директории \n" + System.getProperty("user.dir") + "\n");
        } else throw new IOException("Неверно введено имя архива" + "\n");
    }

    /**
     * Method extracts zip archive in the working directory
     */
    public void zip_extract() throws IOException {
        System.out.println("Введите имя архива для распаковки:");
        String archive_name = d.readLine();
        if (isFileName(archive_name)) {
            if (archive_name.endsWith(".zip") || archive_name.endsWith(".ZIP")) {
            } else {
                archive_name = archive_name + ".zip";
            }
            ZipFile zipFile = new ZipFile(archive_name);
            Enumeration<?> enu = zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enu.nextElement();
                File file = new File(zipEntry.getName());
                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(file);
                int length;
                while ((length = is.read(buffer)) >= 0) {
                    fos.write(buffer, 0, length);
                }
                is.close();
                fos.close();
            }
            zipFile.close();
            System.out.println("\nАрхив успешно разархивирован в рабочую директорию:\n" + System.getProperty("user.dir") + "\n");
        } else throw new IOException("Неверно введено имя архива или такого архива не сузествует" + "\n");
    }
}