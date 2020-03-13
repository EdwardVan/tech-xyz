package tech.edwardvan.basedesignpattern.pattern.structural.flyweight;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Random;

/**
 * 享元模式
 * <p>
 * 优点:
 * 享元模式的优点在于它可以极大减少内存中对象的数量,使得相同对象或相似对象在内存中只保存一份.
 * 享元模式的外部状态相对独立,而且不会影响其内部状态,从而使得享元对象可以在不同的环境中被共享.
 * <p>
 * 缺点:
 * 享元模式使得系统更加复杂,需要分离出内部状态和外部状态,这使得程序的逻辑复杂化.
 * 为了使对象可以共享,享元模式需要将享元对象的状态外部化,而读取外部状态使得运行时间变长.
 * <p>
 * 举例:
 * {@link java.lang.Integer#valueOf(int)}
 * 数据库连接池
 *
 * @author EdwardVan
 */
@Slf4j
public class FlyweightExample {

    public static void main(String[] args) {
        for (int i = 0; i < 20; ++i) {
            Circle circle =
                    (Circle) CircleFactory.getCircle(getRandomColor());
            circle.setX(getRandom());
            circle.setY(getRandom());
            circle.setRadius(getRandom());
            circle.draw();
        }
    }

    /**
     * 随机获取颜色
     */
    private static String getRandomColor() {
        final String colors[] = {"Red", "Green", "Blue", "White", "Black"};
        return colors[new Random().nextInt(colors.length)];
    }

    /**
     * 随机获取坐标
     */
    private static int getRandom() {
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
            log.info("Circle: [Color :{}, x : {}, y :{}, radius :{}", color, x, y, radius);
        }
    }

    /**
     * 制圆工厂
     */
    public static class CircleFactory {

        private static final HashMap<String, Circle> circleMap = new HashMap<>();

        public static Shape getCircle(String color) {
            Circle circle = circleMap.get(color);
            if (circle == null) {
                circle = new Circle(color);
                circleMap.put(color, circle);
                log.info("Creating circle of color : {}", color);
            }
            return circle;
        }
    }
}
