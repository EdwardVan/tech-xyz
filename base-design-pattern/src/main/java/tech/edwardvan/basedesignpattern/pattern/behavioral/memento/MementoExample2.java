package tech.edwardvan.basedesignpattern.pattern.behavioral.memento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * 备忘录模式
 *
 * @author EdwardVan
 */
@Slf4j
public class MementoExample2 {

    public static void main(String[] args) {
        Edit edit = new Edit();
        edit.write("hello").ctrlS();
        edit.write("world!").ctrlS();
        edit.write("java").ctrlS();
        edit.ctrlZ();
        log.info(edit.toString());
        edit.ctrlZ();
        log.info(edit.toString());
        edit.ctrlZ();
        log.info(edit.toString());
    }

    /**
     * 文本编辑器
     */
    @ToString
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

        @AllArgsConstructor
        @Getter
        private static class Memento {
            private String text;
        }
    }
}
