package per.wilson.cloud.utils;

import java.util.function.Consumer;

/**
 * @author: Wilson
 * @date: 2018/10/22
 * @since:
 */
public class IfUtils {

    private IfUtils() {
    }

    public static IfUtils getInstance() {
        return new IfUtils();
    }

    public <T> IfUtils instanceIf(boolean condition, T t, Consumer<T> consumer) {
        if (condition) {
            consumer.accept(t);
        }
        return this;
    }

    public static <T> T withIf(boolean condition, T t, Consumer<T> consumer) {
        if (condition) {
            consumer.accept(t);
        }
        return t;
    }

    public static <T> T withElse(boolean condition, T t, Consumer<T> ifConsumer,
                                 Consumer<T> elseConsumer) {
        if (condition) {
            ifConsumer.accept(t);
        } else {
            elseConsumer.accept(t);
        }
        return t;
    }

    public static <T> T withElseIf(T t, boolean ifCondition, Consumer<T> ifConsumer,
                                   boolean elseIfCondition, Consumer<T> elseIfConsumer, Consumer<T> elseConsumer) {
        if (ifCondition) {
            ifConsumer.accept(t);
        } else {
            withElse(elseIfCondition, t, elseIfConsumer, elseConsumer);
        }
        return t;
    }
}
