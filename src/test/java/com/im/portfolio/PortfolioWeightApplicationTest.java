package com.im.portfolio;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class PortfolioWeightApplicationTest {

    @Test
    public void testCallMainMethod() {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
            System.setIn(in);
           PortfolioWeightApplication.main(null);
        } catch (NoSuchElementException e) {
            assertTrue("The correct stopping should be done with EXIT command. ",
                    false);
            return;
        }
        assertTrue("Application stopped properly.", true);
    }
}
