package org.example.friendgraph;

import org.example.graph.Edge;
import org.example.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class FriendsNode<T> implements Node<T> {
    private T value;
    private List<Edge<T>> edges;

    public FriendsNode(T value) {
        this.value = value;
        edges = new ArrayList<>(3);
    }

    @Override
    public void insertEdge(Node<T> destination) {
        edges.add(new Edge<>(this, destination));
        // as we have an undirected graph
        destination.getEdges().add(new Edge<>(destination, this));
    }

    @Override
    public List<Edge<T>> getEdges() {
        return edges;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
