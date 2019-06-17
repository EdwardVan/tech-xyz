package tech.edwardvan.basedesignpattern.pattern.behavioral.memento;

import java.util.Stack;

/**
 * 备忘录模式
 *
 * @author EdwardVan
 */
public class MementoExample2 {

    public static void main(String[] args) {
        Edit edit = new Edit();
        edit.write("hello").ctrlS();
        edit.write("world!").ctrlS();
        edit.write("java").ctrlS();
        edit.ctrlZ();
        System.out.println(edit);
        edit.ctrlZ();
        System.out.println(edit);
        edit.ctrlZ();
        System.out.println(edit);
    }

    /**
     * 文本编辑器
     */
    public static class Edit {

        private Stack<Memento> mementoStack = new Stack<>();

        private String text;

        public Edit write(String text) {
            this.text = text;
            return this;
        }

        /**
         * 保存
         */
        public void ctrlS() {
            mementoStack.push(new Memento(text));
        }

        /**
         * 撤销
         */
        public void ctrlZ() {
            text = mementoStack.pop().getText();
        }

        @Override
        public String toString() {
            return "Edit{" +
                    "text='" + text + '\'' +
                    '}';
        }

        private static class Memento {
            private String text;

            public Memento(String text) {
                this.text = text;
            }

            public String getText() {
                return text;
            }
        }
    }
}
