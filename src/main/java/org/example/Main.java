package org.example;

import org.example.friendgraph.FriendsGraph;
import org.example.friendgraph.FriendsNode;
import org.example.graph.Graph;
import org.example.graph.Node;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

/*        Graph<String> friendsGraph = new FriendsGraph<>();

        //create nodes

        FriendsNode<String> node1 = new FriendsNode<>("1");
        FriendsNode<String> node2 = new FriendsNode<>("2");
        FriendsNode<String> node3 = new FriendsNode<>("3");
        FriendsNode<String> node4 = new FriendsNode<>("4");
        FriendsNode<String> node5 = new FriendsNode<>("5");

        //insert edges
        node1.insertEdge(node2);
        node1.insertEdge(node3);
        node1.insertEdge(node4);

        node2.insertEdge(node4);
        node2.insertEdge(node5);

        node3.insertEdge(node4);
        node3.insertEdge(node5);

        node4.insertEdge(node5);

        friendsGraph.insertNode(node1);
        friendsGraph.insertNode(node2);
        friendsGraph.insertNode(node3);
        friendsGraph.insertNode(node4);
        friendsGraph.insertNode(node5);


        for (Node<String> node : friendsGraph.getNodes()){
            System.out.println(node.getEdges());
        }*/



/*        Graph<Person> personGraph = new FriendsGraph<>();

        //create nodes

        FriendsNode<Person> person1 = new FriendsNode<>(new Person("Sebastian",37));
        FriendsNode<Person> person2 = new FriendsNode<>(new Person("Andre",38));
        FriendsNode<Person> person3 = new FriendsNode<>(new Person("Dominik",26));
        FriendsNode<Person> person4 = new FriendsNode<>(new Person("Kurt", 26));
        FriendsNode<Person> person5 = new FriendsNode<>(new Person("Tobias",23));

        //insert edges
        person1.insertEdge(person2);
        person1.insertEdge(person3);
        person1.insertEdge(person4);

        person2.insertEdge(person3);
        person2.insertEdge(person4);

        person3.insertEdge(person5);

        person3.insertEdge(person5);

        personGraph.insertNode(person1);
        personGraph.insertNode(person2);
        personGraph.insertNode(person3);
        personGraph.insertNode(person4);
        personGraph.insertNode(person5);


        for (Node<Person> node : personGraph.getNodes()){
            System.out.println(node.getEdges());
        }*/

        URL resource = Main.class.getClassLoader().getResource("friends.txt");
        if (resource == null) {
            throw new IllegalArgumentException("file not found");
        }

        File file = new File(resource.toURI());
/*
        System.out.println("file name: " + file.getName());
        System.out.println("absoulut path: " + file.getAbsolutePath());
        System.out.println("is writable: " + file.canWrite());
        System.out.println("is readable: " + file.canRead());
        System.out.println("file size in bytes: " + file.length());
        System.out.println("last modifacition: " + Date.from(Instant.ofEpochMilli(file.lastModified())));
        System.out.println(file.getTotalSpace());
*/
        final Graph<Person> friendsgraph = new FriendsGraph<>();
        final List<String> assignments = new ArrayList<>(10);

        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
                assignments.add(scanner.nextLine());
            }
            // create nodes

            assignments.forEach(assignment -> friendsgraph.insertNode(new FriendsNode<>(new Person(assignment.split("-")[0]))));

            // create edges

            assignments.forEach(assignment -> {
                String[] split = assignment.split("-");
                Person leftSidePerson = new Person(split[0]);
                Node<Person> left = friendsgraph.getNodes().stream().filter(node -> node.getValue().equals(leftSidePerson)).findAny().orElse(null);

                Person rightSidePerson = new Person(split[0]);
                Node<Person> right = friendsgraph.getNodes().stream().filter(node -> node.getValue().equals(rightSidePerson)).findAny().orElse(null);

                if (left != null) {
                    left.insertEdge(right);
                }
            });
        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        }


        //write the graph
        File graphFile = new File("graph.txt");
        try(PrintWriter printWriter = new PrintWriter(graphFile)) {
            for (Node<Person> node : friendsgraph.getNodes()) {
                printWriter.println(node.getEdges());
            }
        }catch ( IOException e) {
            System.err.println(e.getMessage());
        }

        for (Node<Person> node : friendsgraph.getNodes()) {
            System.out.println(node.getEdges());
        }

    }
}