package com.psew.moneytransferapi;

import com.psew.moneytransferapi.utils.AgeCalculator;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgeCalculatorTest {

    @Test
    public void shouldReturnErrorIfDateOfBirthIsNull() {

        LocalDate dateOfBirth = null;
        LocalDate currentDate = LocalDate.of(2023, 3, 23);

        Integer result = AgeCalculator.calculateAge(dateOfBirth, currentDate);

        assertEquals(0, result);
    }

    @Test
    public void shouldReturnErrorIfCurrentDateIsNull() {

        LocalDate dateOfBirth = LocalDate.of(2000, 3, 23);
        LocalDate currentDate = null;

        Integer result = AgeCalculator.calculateAge(dateOfBirth, currentDate);

        assertEquals(0, result);
    }

    @Test
    public void shouldReturnTheCorrectDifferenceBetweenCurrentDateAndDateOfBirth() {

        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
        LocalDate currentDate = LocalDate.of(2023, 3, 23);

        Integer result = AgeCalculator.calculateAge(dateOfBirth, currentDate);

        assertEquals(33, result);
    }
}
