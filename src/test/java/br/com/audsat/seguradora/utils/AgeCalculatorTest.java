package br.com.audsat.seguradora.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgeCalculatorTest {

    @Test
    public void testCalculateAge() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        int expectedAge = 34; // Idade calculada manualmente

        int actualAge = AgeCalculator.calculateAge(birthDate);

        assertEquals(expectedAge, actualAge);
    }

    @Test
    public void testIsBetween18And25() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        boolean expected = false; // A pessoa tem mais de 25 anos

        boolean actual = AgeCalculator.isBetween18And25(birthDate);

        assertEquals(expected, actual);
    }

    @Test
    public void testIsBetween18And25When25() {
        LocalDate birthDate = LocalDate.of(1999, 1, 1);

        boolean expected = true; // A pessoa tem exatamente 25 anos

        boolean actual = AgeCalculator.isBetween18And25(birthDate);

        assertEquals(expected, actual);
    }

    @Test
    public void testIsBetween18And25When18() {
        LocalDate birthDate = LocalDate.of(2006, 1, 1);

        boolean expected = true; // A pessoa tem exatamente 18 anos

        boolean actual = AgeCalculator.isBetween18And25(birthDate);

        assertEquals(expected, actual);
    }
}
