package tech.edwardvan.basedesignpattern.pattern.structural.composite;

import java.util.ArrayList;

/**
 * 组合模式
 * <p>
 * 举例:
 * {@link org.apache.ibatis.scripting.xmltags.SqlNode}
 *
 * @author EdwardVan
 */
public class CompositeExample {
    public static void main(String[] args) {
        Folder folder1, folder2, folder3;
        folder1 = new Folder("Van的资料");
        folder2 = new Folder("图像文件");
        folder3 = new Folder("文本文件");

        ImageFile image1, image2;
        image1 = new ImageFile("小龙女.jpg");
        image2 = new ImageFile("张无忌.gif");

        TextFile text1, text2;
        text1 = new TextFile("九阴真经.txt");
        text2 = new TextFile("葵花宝典.doc");

        folder2.addImageFile(image1);
        folder2.addImageFile(image2);
        folder3.addTextFile(text1);
        folder3.addTextFile(text2);
        folder1.addFolder(folder2);
        folder1.addFolder(folder3);

        folder1.killVirus();
    }

    /**
     * 图像文件类
     */
    static class ImageFile {
        private String name;

        public ImageFile(String name) {
            this.name = name;
        }

        public void killVirus() {
            System.out.println("对图像文件'" + name + "'进行杀毒");
        }
    }

    /**
     * 文本文件类
     */
    static class TextFile {
        private String name;

        public TextFile(String name) {
            this.name = name;
        }

        public void killVirus() {
            System.out.println("对文本文件'" + name + "'进行杀毒");
        }
    }

    /**
     * 文件夹类
     */
    static class Folder {
        private String name;
        /**
         * 定义集合folderList，用于存储Folder类型的成员
         */
        private ArrayList<Folder> folderList = new ArrayList<>();
        /**
         * 定义集合imageList，用于存储ImageFile类型的成员
         */
        private ArrayList<ImageFile> imageList = new ArrayList<>();
        /**
         * 定义集合textList，用于存储TextFile类型的成员
         */
        private ArrayList<TextFile> textList = new ArrayList<>();

        public Folder(String name) {
            this.name = name;
        }

        /**
         * 增加新的Folder类型的成员
         */
        public void addFolder(Folder f) {
            folderList.add(f);
        }

        /**
         * 增加新的ImageFile类型的成员
         */
        public void addImageFile(ImageFile image) {
            imageList.add(image);
        }

        /**
         * 增加新的TextFile类型的成员
         */
        public void addTextFile(TextFile text) {
            textList.add(text);
        }

        //需提供三个不同的方法removeFolder() & removeImageFile() & removeTextFile()来删除成员,代码省略

        //需提供三个不同的方法getChildFolder(int i) & getChildImageFile(int i) & getChildTextFile(int i)来获取成员,代码省略

        /**
         * 杀毒
         */
        public void killVirus() {
            System.out.println("对文件夹'" + name + "'进行杀毒");

            //如果是Folder类型的成员，递归调用Folder的killVirus()方法
            for (Folder folder : folderList) {
                folder.killVirus();
            }

            //如果是ImageFile类型的成员，调用ImageFile的killVirus()方法
            for (ImageFile imageFile : imageList) {
                imageFile.killVirus();
            }
            //如果是TextFile类型的成员，调用TextFile的killVirus()方法
            for (TextFile textFile : textList) {
                textFile.killVirus();
            }
        }
    }

}
