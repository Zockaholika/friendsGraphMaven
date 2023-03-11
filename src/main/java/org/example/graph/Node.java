package org.example.graph;

import java.util.List;

public interface Node<T> {
    void insertEdge(Node<T> node);
    List<Edge<T>> getEdges();
    T getValue();
}
