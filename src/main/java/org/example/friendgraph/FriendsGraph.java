package org.example.friendgraph;

import org.example.graph.Graph;
import org.example.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class FriendsGraph<T> implements Graph<T> {
    private final List<Node<T>> nodes;
    public FriendsGraph() {
        nodes = new ArrayList<>();
    }

    @Override
    public void insertNode(Node<T> node) {
        nodes.add(node);
    }

    public List<Node<T>> getNodes() {
        return nodes;
    }
}
