package dev.liambloom.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class XMLUtils {
    public static Stream<Node> streamNodeList(NodeList l) {
        return IntStream.range(0, l.getLength())
            .mapToObj(l::item);
    }

    public static Stream<Element> streamNodeListElements(NodeList l) {
        return streamNodeList(l)
            .filter(Element.class::isInstance)
            .map(Element.class::cast);
    }
}
