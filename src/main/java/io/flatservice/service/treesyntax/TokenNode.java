package io.flatservice.service.treesyntax;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Value
public class TokenNode {
    Object value;
    List<TokenNode> childNodes = new ArrayList<>();

    public static TokenNode of(String value) {
        var v = getValue(value);
        return new TokenNode(v);
    }

    public static Object getValue(String valueStr) {
        if (isInteger(valueStr))
            return Integer.valueOf(valueStr);
        if (isDouble(valueStr))
            return Double.valueOf(valueStr);
        if (isFloat(valueStr))
            return Float.valueOf(valueStr);
        return valueStr;

    }

    private static boolean isInteger(String valueStr) {
        if (valueStr == null)
            return false;
        try {
            var val = Integer.parseInt(valueStr);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static boolean isDouble(String valueStr) {
        if (valueStr == null)
            return false;
        try {
            var val = Double.parseDouble(valueStr);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static boolean isFloat(String valueStr) {
        if (valueStr == null)
            return false;
        try {
            var val = Float.parseFloat(valueStr);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public void addChild(final TokenNode tokenNode) {
        childNodes.add(tokenNode);
    }

    public int getDepth() {
        var depth = 0;
        for (TokenNode n : childNodes) {
            depth = Math.max(depth, n.getDepth());
        }
        return depth + 1;
    }

    public List<Object> getLeaf() {
        var list = new ArrayList<>();
        transverse(list);
        Collections.reverse(list);
        return list;
    }

    public void transverse(List<Object> list) {
        for (TokenNode tokenNode : this.getChildNodes()) {
            tokenNode.transverse(list);
        }
        if (!this.getValue().equals(")"))
            list.add(this.getValue());
    }
}
