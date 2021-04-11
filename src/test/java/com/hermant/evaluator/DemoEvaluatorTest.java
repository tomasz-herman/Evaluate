package com.hermant.evaluator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DemoEvaluatorTest {

    private static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of("2 + 2", "4"),
                Arguments.of("2 - 2", "0"),
                Arguments.of("2 * 2", "4"),
                Arguments.of("2 / 2", "1"),
                Arguments.of("2 / 5", "0.4"),
                Arguments.of("2", "2"),
                Arguments.of("-2", "-2"),
                Arguments.of("--2", "2"),
                Arguments.of("+2", "2"),
                Arguments.of("2 + 2 * 2", "6"),
                Arguments.of("(2 + 2) * 2", "8"),
                Arguments.of("2 * 2 + 2", "6"),
                Arguments.of("2 * 2 + 2)", null),
                Arguments.of("2 * (2 + 2", null),
                Arguments.of("2 * 2 + 2 -", null),
                Arguments.of("* 2 * 2 + 2", null),
                Arguments.of("2 ** 2 + 2", null),
                Arguments.of("- 2 - - 2 + - + 2", "-2")
                );
    }

    @ParameterizedTest
    @MethodSource("parameters")
    public void test(String equation, String expected) {
        // given:
        DemoEvaluator evaluator = new DemoEvaluator();
        // when:
        BigDecimal value = evaluator.evaluate(equation);
        // then:
        if(expected != null)
            assertThat(value).isEqualTo(new BigDecimal(expected));
        else
            assertThat(value).isNull();
    }

}