package com.cl.interview.util;

import java.io.*;

/**
 * Created by cl on 2017/11/28.
 * 对象序列化
 * 将对象序列化之后存到某文件
 * 反序列化则将该文件中的数据生成对象
 */
public class SerializableFile {
    /**
     * 序列化
     * 通过文件
     *
     * @param object
     * @param path
     */
    public static void doSerializable(Object object, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(object);
        out.close();
    }

    /**
     * 反序列化
     * 通过文件
     *
     * @param path
     * @return
     */
    public static Object upSerializable(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        Object object = inputStream.readObject();
        return object;
    }

    /**
     * 序列化
     * 通过字节数组
     *
     * @param object
     */
    public static ByteArrayOutputStream doSerializable(Object object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeObject(object);
        out.close();
        return outputStream;
    }

    /**
     * 反序列化
     * 通过文件
     *
     * @param bytes 可通过 ByteArrayOutputStream.toByteArray()获取
     * @return
     */
    public static Object upSerializable(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Object object = inputStream.readObject();
        return object;
    }

}


