package com.giousa.daily.ftl;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtils {

    public static void writeFile(String content,String fileN) {
        // 在文件夹目录下新建文件
        String fileName = "/Users/zhangmengmeng/Desktop/FTL_File/"+fileN;
        File file = new File(fileName);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    log.info("文件不存在，开始创建文件");
                }
                fos = new FileOutputStream(file);
            } else {
                //清空文件
                clearFile(file);
                //追加内容
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");

            osw.write(content);

            log.info("文件:{} ; 写入完成！",fileName);
        } catch (Exception e) {
            log.info("写入文件发生异常", e);
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.info("关闭流异常", e);
            }
        }

    }

    private static void clearFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            log.info("文件清理成功！");
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("文件清理失败！");
        }

    }
}
