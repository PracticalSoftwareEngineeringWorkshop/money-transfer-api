package com.psew.moneytransferapi;

import com.psew.moneytransferapi.utils.AgeCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class AgeCalculatorTest {

    @Test
    public void testCalculateAge_Success() {
        // setup
        LocalDate birthDate = LocalDate.of(1961, 5, 17);
        // exercise
        int actual = AgeCalculator.calculateAge(birthDate, LocalDate.of(2016, 7, 12));
        // assert
        Assert.assertEquals(55, actual);
    }
}
