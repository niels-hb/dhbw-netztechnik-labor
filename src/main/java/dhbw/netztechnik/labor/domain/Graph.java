package dhbw.netztechnik.labor.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class Graph {
  @Getter
  @Setter
  private String name;

  private final Set<Node> nodes = new HashSet<>();

  public void updateStatus() {
    nodes.forEach(Node::advertiseStatus);
  }

  public void addNode(String name, int nodeId) {
    addNode(new Node(name, nodeId));
  }

  public void addNode(Node node) {
    nodes.add(node);
  }

  public void setCost(String from, String to, int cost) {
    setCost(getNode(from), getNode(to), cost);
  }

  public void setCost(Node from, Node to, int cost) {
    from.getLinks().put(to, cost);
    to.getLinks().put(from, cost);
  }

  private Node getNode(String name) {
    return nodes.stream().filter(node -> node.getName().equals(name)).findFirst().orElse(null);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(String.format("Graph %s {\n", name));

    nodes.forEach(node -> builder.append(node.toString()));

    builder.append("}");

    return builder.toString();
  }
}
