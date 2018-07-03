package com.im.portfolio.exceptions;

/**
 * @author imaltsev
 * @since 03/07/18
 * <p>
 * Exception class stands for issues arising in the process of Portfolio Calculation.
 */
public class PortfolioBusinessException extends Exception {

    private String message;

    public PortfolioBusinessException(String message) {
        super(message);
        this.message = message;
    }

    public PortfolioBusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
