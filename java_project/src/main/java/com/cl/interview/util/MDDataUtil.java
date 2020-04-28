package com.cl.interview.util;

import com.cl.interview.common.MDData;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MDDataUtil {
    private static final String[] titles = new String[]{"#", "##", "###", "####"};


    public static void outFile(String filePath, MDData data) {
        File file = new File(filePath);
        try {
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file, true);
            outFile(out, data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void outFile(OutputStream out, MDData data) throws IOException {
        if (data == null) {
            return;
        }
        if (!StringUtils.isEmpty(data.getTitle())) {
            outTitle(out, data.getTitle(), data.getLevel());
        }
        if (!StringUtils.isEmpty(data.getSequence())) {
            outSequence(out, data.getSequence());
        }
        if (data.getBlock() != null) {
            for (int i = 0; i < data.getBlock().size(); i++) {
                outblock(out, data.getBlock().get(i));
            }
        }
        if (data.getList() != null) {
            for (int i = 0; i < data.getList().size(); i++) {
                outFile(out, data.getList().get(i));
            }
        }
    }

    public static void outblock(OutputStream out, String block) throws IOException {
        out.write("```".getBytes());
        separator(out);
        out.write(block.getBytes());
        separator(out);
        out.write("```".getBytes());
        separator(out);
    }

    public static void outSequence(OutputStream out, String sequence) throws IOException {
        out.write("- ".getBytes());
        out.write(sequence.getBytes());
        separator(out);
    }

    public static void outTitle(OutputStream out, String title, Integer level) throws IOException {
        out.write(titles[level].getBytes());
        out.write(title.getBytes());
        separator(out);
    }

    public static void separator(OutputStream out) throws IOException {
        String huanhang = System.getProperty("line.separator");
        out.write(huanhang.getBytes());
    }


}
