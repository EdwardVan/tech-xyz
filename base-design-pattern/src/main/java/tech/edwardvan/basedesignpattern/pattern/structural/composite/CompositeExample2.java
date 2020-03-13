package tech.edwardvan.basedesignpattern.pattern.structural.composite;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * 组合模式
 *
 * @author EdwardVan
 */
@Slf4j
public class CompositeExample2 {

    public static void main(String[] args) {

        AbstractFile file1, file2, file3, file4, file5, folder1, folder2, folder3, folder4;

        folder1 = new Folder("Van的资料");
        folder2 = new Folder("图像文件");
        folder3 = new Folder("文本文件");
        folder4 = new Folder("视频文件");

        file1 = new ImageFile("小龙女.jpg");
        file2 = new ImageFile("张无忌.gif");
        file3 = new TextFile("九阴真经.txt");
        file4 = new TextFile("葵花宝典.doc");
        file5 = new VideoFile("笑傲江湖.mp4");

        folder2.add(file1);
        folder2.add(file2);
        folder3.add(file3);
        folder3.add(file4);
        folder4.add(file5);
        folder1.add(folder2);
        folder1.add(folder3);
        folder1.add(folder4);

        folder1.killVirus();
    }

    /**
     * 抽象文件类:抽象构件
     */
    abstract static class AbstractFile {
        public abstract void add(AbstractFile file);

        public abstract void remove(AbstractFile file);

        public abstract AbstractFile getChild(int i);

        public abstract void killVirus();
    }

    /**
     * 叶子构件:图像文件类
     */
    static class ImageFile extends AbstractFile {
        private String name;

        public ImageFile(String name) {
            this.name = name;
        }

        @Override
        public void add(AbstractFile file) {
            log.info("对不起，不支持该方法！");
        }

        @Override
        public void remove(AbstractFile file) {
            log.info("对不起，不支持该方法！");
        }

        @Override
        public AbstractFile getChild(int i) {
            log.info("对不起，不支持该方法！");
            return null;
        }

        @Override
        public void killVirus() {
            log.info("对图像文件<{}>进行杀毒", name);
        }
    }

    /**
     * 叶子构件:文本文件类
     */
    static class TextFile extends AbstractFile {
        private String name;

        public TextFile(String name) {
            this.name = name;
        }

        @Override
        public void add(AbstractFile file) {
            log.info("对不起，不支持该方法！");
        }

        @Override
        public void remove(AbstractFile file) {
            log.info("对不起，不支持该方法！");
        }

        @Override
        public AbstractFile getChild(int i) {
            log.info("对不起，不支持该方法！");
            return null;
        }

        @Override
        public void killVirus() {
            log.info("对文本文件<{}>进行杀毒", name);
        }
    }

    /**
     * 叶子构件:视频文件类
     */
    static class VideoFile extends AbstractFile {
        private String name;

        public VideoFile(String name) {
            this.name = name;
        }

        @Override
        public void add(AbstractFile file) {
            log.info("对不起，不支持该方法！");
        }

        @Override
        public void remove(AbstractFile file) {
            log.info("对不起，不支持该方法！");
        }

        @Override
        public AbstractFile getChild(int i) {
            log.info("对不起，不支持该方法！");
            return null;
        }

        @Override
        public void killVirus() {
            log.info("对视频文件'" + name + "'进行杀毒");
        }
    }

    /**
     * 容器构件:文件夹类
     */
    static class Folder extends AbstractFile {
        /**
         * 定义集合fileList，用于存储AbstractFile类型的成员
         */
        private ArrayList<AbstractFile> fileList = new ArrayList<>();

        private String name;

        public Folder(String name) {
            this.name = name;
        }

        @Override
        public void add(AbstractFile file) {
            fileList.add(file);
        }

        @Override
        public void remove(AbstractFile file) {
            fileList.remove(file);
        }

        @Override
        public AbstractFile getChild(int i) {
            return fileList.get(i);
        }

        @Override
        public void killVirus() {
            log.info("对文件夹<{}>进行杀毒", name);
            for (AbstractFile abstractFile : fileList) {
                abstractFile.killVirus();
            }
        }
    }
}
