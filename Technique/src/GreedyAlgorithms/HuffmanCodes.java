package GreedyAlgorithms;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Ethan on 16/1/1.
 */
public class HuffmanCodes {

    private static class HuffmanCharacter {
        char character; // The character
        int frequency;  // The frequency of the character

        HuffmanCharacter(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }
    }

    private static class HuffmanNode {
        char character;         // The character of the node
        int frequency;          // The frequency of the node
        int codeword;           // The codeword of the node
        HuffmanNode leftNode;   // left child
        HuffmanNode rightNode;  // right child

        // Constructors
        HuffmanNode() {
        }

        HuffmanNode(HuffmanCharacter huffmanCharacter) {
            this(huffmanCharacter, null, null);
        }

        HuffmanNode(HuffmanCharacter huffmanCharacter, HuffmanNode leftNode, HuffmanNode rightNode) {
            this.character = huffmanCharacter.character;
            this.frequency = huffmanCharacter.frequency;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.codeword = 0b0;
        }
    }

    public static HuffmanNode huffman(HuffmanCharacter[] huffmanCharacters) {
        int n = huffmanCharacters.length;

        // Establish the PriorityQueue
        Comparator<HuffmanNode> comparator = new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode n1, HuffmanNode n2) {
                if (n1.frequency > n2.frequency) {
                    return 1;
                } else if (n1.frequency < n2.frequency) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(comparator);
        for (HuffmanCharacter huffmanCharacter : huffmanCharacters) {
            HuffmanNode huffmanNode = new HuffmanNode(huffmanCharacter);
            priorityQueue.add(huffmanNode);
        }

        for (int i = 1; i < n; i++) {
            HuffmanNode huffmanNode1 = priorityQueue.poll();
            HuffmanNode huffmanNode2 = priorityQueue.poll();
            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.leftNode = huffmanNode1;
            huffmanNode.rightNode = huffmanNode2;
            huffmanNode.frequency = huffmanNode1.frequency + huffmanNode2.frequency;
            priorityQueue.add(huffmanNode);
        }
        LinkedList<Byte> codewordList = new LinkedList<>(); // Counting the codeword
        setCodeword(priorityQueue.peek(), codewordList);
        return priorityQueue.poll(); // root
    }

    public static void setCodeword(HuffmanNode huffmanNode, LinkedList<Byte> codewordList) {
        if (huffmanNode.leftNode != null) {
            codewordList.addLast((byte) 0);
            setCodeword(huffmanNode.leftNode, codewordList);
            codewordList.removeLast();
        }
        if (huffmanNode.rightNode != null) {
            codewordList.addLast((byte) 1);
            setCodeword(huffmanNode.rightNode, codewordList);
            codewordList.removeLast();
        }
        if (huffmanNode.leftNode == null && huffmanNode.rightNode == null) {
            int codeword = 0b0;
            for (Byte b : codewordList) {
                codeword <<= 1;
                codeword += b;
            }
            huffmanNode.codeword = codeword;
        }
    }

    public static void printCodeword(HuffmanNode huffmanNode) {
        if (huffmanNode != null) {
            if (huffmanNode.rightNode == null && huffmanNode.leftNode == null) {
                System.out.println(huffmanNode.character + "'s codeword is: " +
                        Integer.toBinaryString(huffmanNode.codeword));
            } else {
                printCodeword(huffmanNode.leftNode);
                printCodeword(huffmanNode.rightNode);
            }
        }
    }

    public static void main(String[] args) {
        HuffmanCharacter[] huffmanCharacters = {new HuffmanCharacter('a', 45), new HuffmanCharacter('b', 13),
                new HuffmanCharacter('c', 12), new HuffmanCharacter('d', 16), new HuffmanCharacter('e', 9),
                new HuffmanCharacter('f', 5)};
        printCodeword(huffman(huffmanCharacters));
    }
}