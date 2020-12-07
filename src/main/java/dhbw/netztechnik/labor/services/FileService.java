package dhbw.netztechnik.labor.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import dhbw.netztechnik.labor.domain.Graph;

public class FileService {
  public Graph readInput(File file) {
    Scanner scanner = null;

    try {
      scanner = new Scanner(file);
      Graph graph = new Graph();

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();

        if (isDefinition(line)) {
          graph.setName(line.split(" ")[1]);
        } else if (isNode(line)) {
          String[] node = line.replaceAll("[ ;]", "").split("=");

          graph.addNode(node[0], Integer.parseInt(node[1]));
        } else if (isLink(line)) {
          String[] linkDef = line.replaceAll("[ ;]", "").split("[-:]");
          int cost = Integer.parseInt(linkDef[2]);

          graph.setCost(linkDef[0], linkDef[1], cost);
        } else {
          continue;
        }
      }

      return graph;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (Objects.nonNull(scanner)) {
        scanner.close();
      }
    }

    return null;
  }

  private boolean isDefinition(String line) {
    return line.matches("Graph [a-zA-Z]+ \\{");
  }

  private boolean isNode(String line) {
    return line.matches("[A-Z] = [0-9]+;");
  }

  private boolean isLink(String line) {
    return line.matches("[A-Z] - [A-Z] : [0-9]+;");
  }
}
