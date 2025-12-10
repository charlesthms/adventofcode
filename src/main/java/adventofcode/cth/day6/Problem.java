package adventofcode.cth.day6;


import lombok.Data;

import java.util.List;
import java.util.function.BinaryOperator;

@Data
public class Problem<T> {

    private final BinaryOperator<T> operator;
    private final List<T> elements;

    public Problem(final BinaryOperator<T> newOperator, final List<T> newElements) {
        operator = newOperator;
        elements = newElements;
    }

    public T solve() {
        T result = elements.getFirst();
        for (int i = 1; i < elements.size(); i++) {
            result = operator.apply(result, elements.get(i));
        }
        return result;
    }
}
