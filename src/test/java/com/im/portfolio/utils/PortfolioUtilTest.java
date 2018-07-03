package com.im.portfolio.utils;

import com.im.portfolio.exceptions.PortfolioBusinessException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PortfolioUtilTest {

    @Test
    public void testReadFileWithIncorrectPath(){
        try {
            PortfolioUtil.readRowsFromFile
                    ("incorrect name");
        } catch (PortfolioBusinessException e) {
            assertTrue("Incorrect path to file should raise this exception. ",
                    true);
            return;
        }
        assertTrue("Incorrect path must raise exception. ",
                false);
    }

    @Test
    public void testOutputFile(){
        try {
           String path = PortfolioUtil.resultToOutputFile
                    ("portfolio_1.txt",
                            "Test Text");

            Assert.assertEquals("Must be equal file name.",path,"portfolio_1.output");
        } catch (PortfolioBusinessException e) {
            assertTrue("Wrong File Name. ",
                    false);
        }

    }

    @Test
    public void testWeightCalc(){

        Assert.assertEquals("Must be right calculation results.", Double.valueOf(0.333),
                PortfolioUtil.calcWeight(new Integer(3000), new Integer(1000) ));

        Assert.assertEquals("Must be right calculation results.", Double.valueOf(0.667),
                PortfolioUtil.calcWeight(new Integer(3000), new Integer(2000) ));

        Assert.assertEquals("Must be right calculation results.", Double.valueOf(0.25),
                PortfolioUtil.calcWeight(new Integer(2000), new Integer(500) ));

        Assert.assertEquals("Must be right calculation results.", Double.valueOf(0.05),
                PortfolioUtil.calcWeight(new Integer(5000), new Integer(250) ));
    }

}
