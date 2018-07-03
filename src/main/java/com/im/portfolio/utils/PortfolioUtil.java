package com.im.portfolio.utils;

import com.im.portfolio.exceptions.PortfolioBusinessException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author imaltsev
 * @since 03/07/18
 * <p>
 *Utility class holds auxiliary methods to read/write files and calculate
 */
public class PortfolioUtil {

    /**
     * This method reads data from input file and converts the data into List<String>
     *
     * @param fileName - the absolute path to the file with input data
     *                 e.g. C:\portfolio\src\main\resources\portfolio_1.txt
     * @return Optional<List<String>> - the list of lines from input file
     */
    public static Optional<List<String>> readRowsFromFile(String fileName)
            throws PortfolioBusinessException {
        try {
            Path path = Paths.get(fileName);
            Stream<String> stream = Files.lines(path);
            return Optional.of(stream.collect(Collectors.toList()));
        } catch (InvalidPathException ipe) {
            throw new PortfolioBusinessException("ERROR: Invalid path to your file: " + fileName +
                         ipe.getMessage(), ipe);
        } catch (IOException ioe) {
            throw new PortfolioBusinessException("ERROR: Can't read your file with path: " +
                    fileName + "  Error reason: " + ioe.getMessage(), ioe);
        }
    }

    /**
     * This method writes results of calculation into output file.
     * Input file name is changing here as
     * the extension of input file name is converted into "*.output"
     *
     * @param fileName - the absolute path to the file with input data
     *                 e.g. C:\portfolio\src\main\resources\portfolio_1.txt
     * @return String - the absolute path to output file
     */
    public static String resultToOutputFile(String fileName, String result)
            throws PortfolioBusinessException {
        String pathOutput = changeFileExtension(fileName, "output");
        try {
            Path path = Paths.get(pathOutput);
            Files.write(path, result.getBytes());
        } catch (InvalidPathException ipe) {
            throw new PortfolioBusinessException("ERROR: Incorrect path to output file: "
                    + fileName + "  " + ipe.getMessage(), ipe);
        } catch (IOException ioe) {
            throw new PortfolioBusinessException("ERROR: Can't write text to output file: "
                    + fileName + "  Error reason: " + ioe.getMessage(), ioe);
        }
        return pathOutput;
    }

    /**
     * This method calculates the leaf-to-root weight
     *
     * @param rootValue - is the root fund portfolio value
     * @param leafValue - is the leaf fund portfolio value
     * @return Double - the result
     */
    public static Double calcWeight(Integer rootValue, Integer leafValue) {
        return new BigDecimal(leafValue).divide(new BigDecimal(rootValue),
                numLength(rootValue) + 3, RoundingMode.HALF_EVEN).setScale(3,
                        RoundingMode.HALF_UP).doubleValue();
    }

    private static int numLength(int n) {
        if (n == 0) return 1;
        int l;
        n = Math.abs(n);
        for (l = 0; n > 0; ++l)
            n /= 10;
        return l;
    }

    private static String changeFileExtension
            (String source, String newExtension) {
        String target;
        String currentExtension = getFileExtension(source);

        if (currentExtension.equals("")) {
            target = source + "." + newExtension;
        } else {
            target = source.replaceFirst(Pattern.quote("." +
                    currentExtension) + "$", Matcher.quoteReplacement("." + newExtension));
        }
        return target;
    }

    private static String getFileExtension(String f) {
        String ext = "";
        int i = f.lastIndexOf('.');
        if (i > 0 && i < f.length() - 1) {
            ext = f.substring(i + 1);
        }
        return ext;
    }
}
