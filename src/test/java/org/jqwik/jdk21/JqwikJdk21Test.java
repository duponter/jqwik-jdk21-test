package org.jqwik.jdk21;

import net.jqwik.api.*;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.StringLength;

import static org.assertj.core.api.Assertions.assertThat;

class JqwikJdk21Test {
    @Group
    class InGroup {

        @Property
        void runsWithAnnotations(@ForAll @NumericChars @StringLength(9) String input) {
            assertThat(input).hasSize(9).containsOnlyDigits();
        }


        @Property
        void runsWithProvideFromOuterClass(@ForAll("numericStrings") String input) {
            assertThat(input).hasSize(9).containsOnlyDigits();
        }
    }

    @Property
    void runsWithProvideFromSameClass(@ForAll("numericStrings") String input) {
        assertThat(input).hasSize(9).containsOnlyDigits();
    }

    @Provide
    static Arbitrary<String> numericStrings() {
        return Arbitraries.strings().numeric().ofLength(9);
    }
}
