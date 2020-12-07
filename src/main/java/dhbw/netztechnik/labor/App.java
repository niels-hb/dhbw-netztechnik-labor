package dhbw.netztechnik.labor;

import java.io.File;

import dhbw.netztechnik.labor.domain.Graph;
import dhbw.netztechnik.labor.services.FileService;

public class App {
  private static FileService fileService = new FileService();

  public static void main(String[] args) {
    File file = new File("src/main/resources/mock/input.txt");

    Graph graph = fileService.readInput(file);
    System.out.println(graph);

    for (int i = 0; i < 10; i++) {
      graph.updateStatus();

      System.out.println(graph);
    }
  }
}
