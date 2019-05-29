package tech.edwardvan.basedesignpattern.pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Random;

/**
 * 享元模式
 * 优点:
 * 享元模式的优点在于它可以极大减少内存中对象的数量,使得相同对象或相似对象在内存中只保存一份.
 * 享元模式的外部状态相对独立,而且不会影响其内部状态,从而使得享元对象可以在不同的环境中被共享.
 * 缺点:
 * 享元模式使得系统更加复杂,需要分离出内部状态和外部状态,这使得程序的逻辑复杂化.
 * 为了使对象可以共享,享元模式需要将享元对象的状态外部化,而读取外部状态使得运行时间变长.
 * 举例:
 * {@link java.lang.Integer#valueOf(int)}
 * 连接池
 *
 * @author EdwardVan
 */
public class FlyweightExample {

    private static final String colors[] = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {
        for (int i = 0; i < 20; ++i) {
            Circle circle =
                    (Circle) CircleFactory.getCircle(getRandomColor());
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.setRadius(100);
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[new Random().nextInt(colors.length)];
    }

    private static int getRandomX() {
        return new Random().nextInt(100);
    }

    private static int getRandomY() {
        return new Random().nextInt(100);
    }


    /**
     * 形状接口
     */
    public interface Shape {
        void draw();
    }

    /**
     * 圆
     */
    public static class Circle implements Shape {
        /**
         * 内部状态
         */
        private String color;
        /**
         * 外部状态
         */
        private int x;
        private int y;
        private int radius;

        public Circle(String color) {
            this.color = color;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Circle: Draw() [Color : " + color
                    + ", x : " + x + ", y :" + y + ", radius :" + radius);
        }
    }

    /**
     * 圆工厂
     */
    public static class CircleFactory {

        private static final HashMap<String, Shape> circleMap = new HashMap<>();

        public static Shape getCircle(String color) {
            Circle circle = (Circle) circleMap.get(color);
            if (circle == null) {
                circle = new Circle(color);
                circleMap.put(color, circle);
                System.out.println("Creating circle of color : " + color);
            }
            return circle;
        }
    }
}
