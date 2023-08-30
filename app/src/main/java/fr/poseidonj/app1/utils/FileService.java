package fr.poseidonj.app1.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileService {
    public static void writeInternal(String path, String content) throws IOException {
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
        stream.write(content.getBytes(StandardCharsets.UTF_8));
        stream.close();
    }

    public static String readInternal(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        reader.close();
        return builder.toString();
    }

    public static boolean writeExternal(Context context, String filename, String content) throws Exception {
        if (isExternalWritable()) {
            File file = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            //file=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

            assert file != null;
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file.getAbsoluteFile() + "/" + filename));
            stream.write(content.getBytes(StandardCharsets.UTF_8));
            stream.close();
            return true;
        } else {
            throw new Exception("External not mounted");
        }
    }

    public static String readInternal(Context context, String filename) throws Exception {
        if (isExternalReadable()) {
            File file = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

            assert file != null;
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file.getAbsoluteFile() + "/" + filename));

            int car;
            StringBuilder builder = new StringBuilder();

            byte[] buffer = new byte[2048];
            while ((car = reader.read(buffer)) != -1) {
                builder.append(new String(buffer, 0, car));
            }
            reader.close();
            return builder.toString();
        } else {
            throw new Exception("External not mounted");
        }


    }

    public static boolean isExternalWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static boolean isExternalReadable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY) ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}
