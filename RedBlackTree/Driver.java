/**
 * This is the driver class for Project 3. 
 * It reads input from a file, constructs a graph based on the input, and applies a greedy coloring algorithm to the graph.
 * @author Xiao Shi
 * andrew id xiaoshi
*/

import java.io.*;
import java.util.*;

public class Driver {

    /**
     * Processes a single line of input to extract elements and insert them into a Red-Black Tree.
     * The input line is expected to contain elements separated by spaces or tabs. The first two
     * tokens of each line are ignored, which might typically include an identifier and a count
     * or any other preliminary data. The remaining tokens are considered to be relevant items
     * for insertion into the Red-Black Tree.
     * @param line A string representing the line of input to be processed. This line should contain
     * data items separated by spaces or tabs.
     * @param RBT The Red-Black Tree into which the extracted data items will be inserted. This tree
     * is used to store a unique set of items, ensuring no duplicates and maintaining balance for efficient operations.
    */
    
     private static void processLine(String line, RedBlackTree RBT) {
        StringTokenizer st;

        // use space, and tab for delimeters
        st = new StringTokenizer(line, " \t"); // use split if you prefer
        st.nextToken();
        st.nextToken();
        while (st.hasMoreTokens()) {
            RBT.insert(st.nextToken());
        }
    }

    /**
     * Parses a single line of input to extract graph information and translates it into an array of integers
     * representing vertices, using a Red-Black Tree for mapping input strings to vertex indices.
     * The input line is expected to contain space- or tab-separated values, where the first token is ignored
     * (presumably an identifier or command), the second token represents the number of vertices or classes,
     * and the subsequent tokens are used to look up the vertex index in the Red-Black Tree.
     * @param line A string containing the raw data for one part of the graph. This string is tokenized to extract the relevant information.
     * @param RBT A Red-Black Tree used to map string identifiers to integer indices. This mapping is necessary for translating the input data into a graph representation.
     * @return An array of integers where each integer represents a vertex in the graph. The vertices are determined based on the input line and the mapping provided by the Red-Black Tree.
     */
    private static int[] createGraph(String line, RedBlackTree RBT) {
        StringTokenizer st;
        st = new StringTokenizer(line, " \t"); // use split if you prefer
        st.nextToken();
        int classnum = Integer.parseInt(st.nextToken());
        int[] tmp = new int[classnum];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = RBT.getValue(st.nextToken());
        }
        return tmp;
    }


    /**
     * Performs a greedy coloring of the graph and outputs the coloring to a specified file.
     * This method assigns colors (represented as integers) to vertices of the graph such that
     * no two adjacent vertices share the same color. The greedy algorithm assigns colors by
     * considering vertices one by one and assigning the smallest available color that has not
     * been used by its adjacent vertices.
     * @param graph The adjacency matrix representation of the graph, where graph[u][v] == 1
     * indicates an edge between vertices u and v.
     * @param arrayofStrings An array of strings representing the labels of the vertices.
     * @param outfile The name of the output file where the coloring results will be saved.
     */

    private static void greedyColoring(int[][] graph, String[] arrayofStrings, String outfile) {
        String output = "";
        int[] result = new int[graph.length];
        Arrays.fill(result, -1);
        result[0] = 0;
        boolean[] available = new boolean[graph.length];
        Arrays.fill(available, true);
        for (int u = 1; u < graph.length; u++) {
            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] == 1 && result[v] != -1) {
                    available[result[v]] = false;
                }
            }
            int cr;
            for (cr = 0; cr < graph.length; cr++) {
                if (available[cr]) {
                    break;
                }
            }
            result[u] = cr;
            Arrays.fill(available, true);
        }
        int max = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > max) {
                max = result[i];
            }
        }
        System.out.println("");
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream(outfile, true), true);
            for (int i = 0; i <= max; i++) {
                System.out.print("Final Exam Period " + (i + 1) + " => ");
                output += "Final Exam Period " + (i + 1) + " => ";
                for (int j = 0; j < result.length; j++) {
                    if (result[j] == i) {
                        System.out.print(arrayofStrings[j] + " ");
                        output += arrayofStrings[j] + " ";
                    }
                }
                output += "\r\n";
                System.out.println("");
            }
            out.println(output);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes input from a file to construct and output an adjacency matrix for a graph, 
     * then applies a greedy coloring algorithm on the graph, writing the results to an output file.
     * This method reads a file line by line to build a graph represented by an adjacency matrix,
     * based on the relationships defined in the file. It utilizes a Red-Black Tree for efficient
     * lookup and storage of vertex relationships.
     * @param fileName The name of the file containing the graph data. Each line in the file represents
     * a part of the graph, with vertices and their connections.
     * @param outfile The name of the output file where the adjacency matrix and the results of the
     * greedy coloring algorithm will be appended.
     */
    private static void getResult(String fileName, String outfile) {
        RedBlackTree RBT = new RedBlackTree();
        try{
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            line = in.readLine();
            while(line != null) {
                processLine(line, RBT);
                line = in.readLine();
            }
        }
        catch(IOException e) {
            System.out.println("IO Exception");
        }

        int[][] AdjacencyMatrix = new int[RBT.getSize()][RBT.getSize()];
        String[] arrayofStrings = RBT.arrayofStrings();

        for (int i = 0; i < AdjacencyMatrix.length; i++) {
            for (int j = 0; j < AdjacencyMatrix[i].length; j++) {
                AdjacencyMatrix[i][j] = 0;
            }
        }

        try{
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            line = in.readLine();
            while(line != null) {
                int[] tmp = createGraph(line, RBT);
                line = in.readLine();
                // fill the adjacency matrix
                for (int i = 0; i < tmp.length - 1; i++) {
                    for (int j = i + 1; j < tmp.length; j++) {
                        AdjacencyMatrix[tmp[i] - 1][tmp[j] - 1] = 1;
                        AdjacencyMatrix[tmp[j] - 1][tmp[i] - 1] = 1;
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("IO Exception");
        }

        try {
            String matrixOutput = "";
            PrintWriter out = new PrintWriter(new FileOutputStream(outfile, true), true);
            for (int i = 0; i < AdjacencyMatrix.length; i++) {
                for (int j = 0; j < AdjacencyMatrix[i].length; j++) {
                    System.out.print(AdjacencyMatrix[i][j]);
                    matrixOutput += AdjacencyMatrix[i][j];
                }
                matrixOutput += "\r\n";
                System.out.println("");
            }
            out.println(matrixOutput);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        greedyColoring(AdjacencyMatrix, arrayofStrings, outfile);
        System.out.println("");
    }


    public static void main(String[] args) {
        System.out.println("xiaoshi");
        try {
            PrintWriter out = new PrintWriter(new FileOutputStream("result.txt", true), true);
            out.println("xiaoshi");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getResult("testfile1.txt", "result.txt");
        getResult("testfile2.txt", "result.txt");
    }
}