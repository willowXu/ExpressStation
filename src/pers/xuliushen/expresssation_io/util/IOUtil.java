package pers.xuliushen.expresssation_io.util;

import java.io.*;

/**
 * 文件读写的工具类
 * @author xuliushen
 * @Time 2021/7/20 16:07
 */
public class IOUtil {
    /**
     * 从文件中读取对象（反序列化）
     * @param fileName 文件名
     * @return 读取的对象
     * @throws IOException,ClassNotFoundException
     */
    public static Object readFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }

    /**
     * 写入数据到指定文件
     * @param obj 待写入的对象
     * @param fileName 写入的文件
     * @throws IOException
     */
    public static void writeFile(Object obj, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);
    }

}
