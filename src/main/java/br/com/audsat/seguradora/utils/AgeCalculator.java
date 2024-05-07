package br.com.audsat.seguradora.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeCalculator {

    public static int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public static boolean isBetween18And25(LocalDate birthDate) {
        int age = calculateAge(birthDate);
        return age >= 18 && age <= 25;
    }
}
