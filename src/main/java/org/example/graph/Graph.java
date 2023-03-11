package org.example.graph;

import java.util.List;

public interface Graph<T> {
    void insertNode(Node<T> value);
    List<Node<T>> getNodes();
}
