package dhbw.netztechnik.labor.domain;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
  private final String name;

  private final int nodeId;

  private final Map<Node, Integer> links = new HashMap<>();

  private int rootId;

  private int sumCost;

  private int nextHop;

  public Node(String name, int nodeId) {
    this.name = name;
    this.nodeId = nodeId;

    this.rootId = nodeId;
  }

  public void advertiseStatus() {
    links.forEach((node, cost) -> {
      node.receiveStatus(this, cost);
    });
  }

  public void receiveStatus(Node sender, int cost) {
    int combinedCost = sender.getSumCost() + cost;

    if (sender.getRootId() < rootId || sender.getRootId() == rootId && combinedCost < sumCost) {
      rootId = sender.getRootId();
      nextHop = sender.getNodeId();

      sumCost = combinedCost;
    }
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Node && nodeId == ((Node) obj).getNodeId();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(
        String.format("\tNode %s [%s] : %d Next Hop %d\n", name, Integer.toHexString(hashCode()), nodeId, nextHop));

    links.forEach((node, cost) -> builder.append(
        String.format("\t\t%s - %s [%s]: %s\n", name, node.getName(), Integer.toHexString(node.hashCode()), cost)));

    return builder.toString();
  }
}
