package com.im.portfolio;

import com.im.portfolio.exceptions.PortfolioBusinessException;
import com.im.portfolio.services.PortfolioWeightService;

import java.util.Scanner;

/**
 * @author imaltsev
 * @since 03/07/18
 * <p>
 * Entry class with the main method
 * The main method reads parameters and transfers them to calculation service,
 * then output results are returned to the console.
 */
public class PortfolioWeightApplication {

    public static void main(String[] args) {

        System.out.println("Start Portfolio Calculator.");
        PortfolioWeightService pws = new PortfolioWeightService();
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.print("Enter the full path to Portfolio File for calculation: ");
            System.out.print(">");
            while (true) {
                String cmd = scanner.nextLine();
                if (cmd.equalsIgnoreCase("exit")) {
                    System.out.println("Portfolio Calculator Stopped.");
                    break;
                }
                try {
                    System.out.println("Calculation has started...");
                    System.out.println(pws.getLeafsRootWeights(cmd));
                } catch (PortfolioBusinessException e) {
                    System.out.println(e.getMessage());
                }
                System.out.print("Another calculation? Enter the full path to Portfolio File or " +
                        "EXIT: >");
            }
        }
    }
}
