/**
 * Project: HashWordAnalyzer
 * Description: This Java program implements a word analysis tool using a hash table.
 *              It reads a text file, processes each word, and stores them in a hash table
 *              to analyze word frequency and distribution.
 * 
 * Class: WordHashTable
 * - tableSize: The size of the hash table, which determines the number of slots available.
 * - hashTable: An array of linked lists to store words based on their hash code.
 * 
 * Constructor: WordHashTable(int size)
 * - Initializes the hash table with a specified size.
 * 
 * Method: addToHashTable(String word)
 * - Adds a word to the hash table after computing its hash code.
 * 
 * Method: processFile(String filePath)
 * - Reads a text file and processes each word, converting them to lowercase and removing
 *   non-alphabetic characters before adding them to the hash table.
 * 
 * Method: displayStatistics()
 * - Displays statistics about the hash table, including the number of words in each slot
 *   and the total number of words.
 * 
 * Main Method: main(String[] args)
 * - Creates an instance of WordHashTable, processes a sample text file
 *   ("The Great Gatsby" by F. Scott Fitzgerald), and displays the hash table statistics.
 * 
 * Note: This project is designed to provide insights into the distribution of words
 *       using a hash table data structure.
 */


package hashWordAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class WordHashTable 
{
    private int tableSize;
    private LinkedList<String>[] hashTable;
    
    public WordHashTable(int size) 
    {
        tableSize = size;
        hashTable = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) 
        {
            hashTable[i] = new LinkedList<>();
        }
    }

    public void addToHashTable(String word) 
    {
        int hash = Math.abs(word.hashCode() % tableSize);
        if (!hashTable[hash].contains(word)) 
        {
            hashTable[hash].add(word);
        }
    }

    public void processFile(String filePath) 
    {
        try 
        {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)));

            while (scanner.hasNext()) 
            {
                String word = scanner.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
                if (!word.isEmpty()) 
                {
                    addToHashTable(word);
                }
            }
            
            scanner.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public void displayStatistics() 
    {
        int totalWords = 0;
        
        for (int i = 0; i < tableSize; i++) 
        {
            System.out.println("Slot " + i + ": " + hashTable[i].size() + " words");
            totalWords += hashTable[i].size();
        }
        
        System.out.println("\nTotal words: " + totalWords);
        
        for (int i = 0; i < tableSize; i++) 
        {
            System.out.print("Slot " + i + ": ");
            for (String word : hashTable[i]) 
            {
                System.out.print(word + " ");
            }
            System.out.println(); 
        }
    }

    public static void main(String[] args) 
    {
        WordHashTable wordHashTable = new WordHashTable(1091);
 
        wordHashTable.processFile("/Users/himel/Desktop/The Project Gutenberg eBook of The Great Gatsby.txt");

        wordHashTable.displayStatistics();
        
    }
}













