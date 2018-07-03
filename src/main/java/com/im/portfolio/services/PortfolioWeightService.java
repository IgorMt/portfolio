package com.im.portfolio.services;

import com.im.portfolio.exceptions.PortfolioBusinessException;
import com.im.portfolio.utils.PortfolioUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author imaltsev
 * @since 03/07/18
 * <p>
 * This service class holds methods validating and calculating the leaf-to-root weight.
 */
public class PortfolioWeightService {

    /**
     * This method reads data from input file and converts the data as List<String>
     *
     * @param fileName - the absolute path to the file with input data
     *                 e.g. C:\portfolio\src\main\resources\portfolio_1.txt
     * @return String - the result of calculation to console output
     */
    public String getLeafsRootWeights(String fileName) throws PortfolioBusinessException {
        List<String> rows = PortfolioUtil.readRowsFromFile(fileName).orElseGet(()
                -> new ArrayList<>());
        return getBaseCalculations(rows, fileName);
    }

    /**
     * This method transforms input data to Node tree, validates and calculates portfolio weights.
     *
     * @param rows - List<String>, input data strings to transform the strings into nodes
     * @param fileName - the absolute path to the file with input data
     *                 e.g. C:\portfolio\src\main\resources\portfolio_1.txt
     * @return String - the result of calculation to console output
     */
    public String getBaseCalculations(List<String> rows, String fileName)
            throws PortfolioBusinessException {
        String results = getCalculatedStringResult(rows.stream().map(line -> line.split(","))
                .map(p -> new Node(p[0], p[1], Integer.valueOf(p[2])))
                .collect(Collectors.toList()));
        String outputFile = PortfolioUtil.resultToOutputFile(fileName, results);
        return "All done, here are the results:\n" + results + "\nSuccessfully calculated. " +
                "See the results in the output file: " + outputFile;
    }

    private String getCalculatedStringResult(List<Node> nodes) throws PortfolioBusinessException {
        //Build Node tree
        Node root = new Node();
        boolean isParent;
        for (Node a : nodes) {
            isParent = true;
            for (Node b : nodes) {
                if (a.getName().equals(b.getParentName())) {
                    b.setParent(a);
                    a.addChild(b);
                }
                if (a.getParentName().equals(b.getName())) {
                    isParent = false;
                }
            }
            if (isParent) {
                if (root.getName() == null) {
                    root.setName(a.getParentName());
                }
                root.addChild(a);
                if (root.getValue() == null) {
                    root.setValue(a.getValue());
                } else {
                    root.setValue(root.getValue() + a.getValue());
                }
            }
        }
        return validateAndCalculate(root, nodes);
    }

    private String validateAndCalculate(Node root, List<Node> nodes)
            throws PortfolioBusinessException {

        if (root.getName() == null || root.getValue() == null) {
            throw new PortfolioBusinessException("ERROR: Failed to build the portfolio tree." +
                   "Input data is inconsistent. Root Node was not created.");
        }

        List<Node> leafs = nodes.stream().filter(p -> p.getChildren().size() == 0)
                .collect(Collectors.toList());
        if (!validateTree(root, leafs)) {
            throw new PortfolioBusinessException("ERROR: Failed to calculate portfolio weights. " +
                    "Input data is inconsistent. Root portfolio value is not equal to " +
                    "the sum of all the leafs values.");
        }
        //Calculate weights and build the result string
        StringBuilder sb = new StringBuilder();
        for (Node nd : leafs) {
            nd.setWeightPerRoot(PortfolioUtil.calcWeight(root.getValue(), nd.getValue()));
            sb.append(root.getName());
            sb.append(",");
            sb.append(nd.getName());
            sb.append(",");
            sb.append(String.valueOf(nd.getWeightPerRoot()));
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean validateTree(Node root, List<Node> leafs) {
        return root.getValue() == leafs.stream().mapToInt(Node::getValue).sum();
    }
}
