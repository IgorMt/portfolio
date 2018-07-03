package com.im.portfolio.services;

import com.im.portfolio.exceptions.PortfolioBusinessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PortfolioWeightServiceTest {

    private List<String> input;
    PortfolioWeightService pws = new PortfolioWeightService();

    @Before
    public void before() {
        input = new ArrayList();
        input.add("A,B,1000");
        input.add("A,C,2000");
        input.add("B,D,500");
        input.add("B,E,250");
        input.add("B,F,250");
        input.add("C,G,1000");
        input.add("C,H,1000");
    }

    @Test
    public void testGetPortfolioTreeSuccess() throws Exception {
        String result = pws.getBaseCalculations(input, "portfolio_1.txt");
        Assert.assertEquals("The output results must be the same.", result
                , "All done, here are the results:\n" +
                        "A,D,0.167\n" +
                        "A,E,0.083\n" +
                        "A,F,0.083\n" +
                        "A,G,0.333\n" +
                        "A,H,0.333\n" +
                        "\n" +
                        "Successfully calculated. See the results in the output file: portfolio_1.output");
    }

    @Test
    public void testGetPortfolioTreeInconsistencyInput() {
        input.add("A,B,1000");
        try {
            pws.getBaseCalculations(input, "portfolio_1.txt");
        } catch (PortfolioBusinessException e) {
            Assert.assertEquals("Inconsistency exception should be happened.", e.getMessage()
                    , "ERROR: Failed to calculate portfolio weights. Input data " +
                            "is inconsistent. Root portfolio value is not equal to the sum " +
                            "of all the leafs values.");
            input.remove("A,B,1000");
            return;
        }
        assertTrue("Exception must happened before. ",
                false);
        input.remove("A,B,1000");
    }

    @Test
    public void testGetPortfolioTreeNoRootNode() {

        input.add("A,A,1000");
        try {
            pws.getBaseCalculations(input, "portfolio_1.txt");
        } catch (PortfolioBusinessException e) {
            Assert.assertEquals("Inconsistency exception should be happened.", e.getMessage()
                    , "ERROR: Failed to build the portfolio tree." +
                            "Input data is inconsistent. Root Node was not created.");

            input.remove("A,A,1000");
            return;
        }
        assertTrue("Exception must happened before. ",
                false);
        input.remove("A,A,1000");
    }

}
