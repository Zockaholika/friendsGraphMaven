package org.example.graph;

public class Edge<T> {
    private final Node<T> source;
    private final Node<T> destination;

    public Edge(Node<T> source, Node<T> destination) {
        this.source = source;
        this.destination = destination;
    }


    @Override
    public String toString() {
        return "source:" + source + " destination: " + destination;
    }
}
