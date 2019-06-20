package tech.edwardvan.basedesignpattern.pattern.behavioral.interpreter;

/**
 * 解释器模式
 * <p>
 * 优点:
 * 可扩展性比较好,灵活.
 * 增加了新的解释表达式的方式.
 * 易于实现简单文法.
 * 缺点:
 * 可利用场景比较少.
 * 对于复杂的文法比较难维护.
 * 解释器模式会引起类膨胀.
 * 举例:
 * {@link org.springframework.expression.ExpressionParser}
 *
 * @author EdwardVan
 */
public class InterpreterExample {

    public static void main(String[] args) {
        IExpression expression =
                new Minus(
                        new Plus(
                                new Plus(
                                        new Plus(
                                                new Value(1),
                                                new Value(2)),
                                        new Value(3)),
                                new Value(4)),
                        new Value(5)
                );
        System.out.println(expression.interpreter());
    }

    /**
     * 抽象表达式
     */
    public interface IExpression {
        int interpreter();
    }

    /**
     * 终结符表达式角色-值
     */
    public static class Value implements IExpression {

        private int value;

        public Value(int value) {
            this.value = value;
        }

        @Override
        public int interpreter() {
            return value;
        }
    }

    /**
     * 非终结符表达式角色-加法
     */
    public static class Plus implements IExpression {

        private IExpression left;
        private IExpression right;

        public Plus(IExpression left, IExpression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpreter() {
            return left.interpreter() + right.interpreter();
        }
    }

    /**
     * 非终结符表达式角色-减法
     */
    public static class Minus implements IExpression {

        private IExpression left;
        private IExpression right;

        public Minus(IExpression left, IExpression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int interpreter() {
            return left.interpreter() - right.interpreter();
        }
    }
}
