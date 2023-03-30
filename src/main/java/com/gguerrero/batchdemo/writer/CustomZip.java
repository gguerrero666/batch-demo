package com.gguerrero.batchdemo.writer;

import com.gguerrero.batchdemo.constants.Constants;
import org.springframework.batch.item.ItemWriter;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CustomZip  implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {

        for (String data : list) {

            zipFile(Constants.path + data + ".txt");
            System.out.println("CustomZip    : Writing zip    : " + data);
        }
        System.out.println("CustomZip    : Writing zip    : completed");
    }

    public void zipFile(String name) throws Exception {

        String sourceFile = name;
        FileOutputStream fos = new FileOutputStream(name.replace(".txt", ".zip"));
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }

        zipOut.close();
        fis.close();
        fos.close();
    }
}
