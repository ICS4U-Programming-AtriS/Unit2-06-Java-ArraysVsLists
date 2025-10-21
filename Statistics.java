import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
* Program that performs statistical operations on an input text file.
* It will find and report the mean, median, and mode of an integer dataset.
*
* @author  Atri Sarker
* @version 1.0
* @since   2025-10-21
*/
public final class Statistics {
  /**
   * Constant for the file path of the input file.
   */
  private static final String INPUT_FILE_PATH = "./input.txt";

  /**
   * Private constructor to satisfy style checker.
   * @exception IllegalStateException for the utility class.
   * @see IllegalStateException
   */
  private Statistics() {
    // Prevents illegal states.
    throw new IllegalStateException("Utility class.");
  }

  /**
   * Function that finds and returns the mean of an array.
   * @param arr an array of integers.
   * @return the mean/average of an array.
   */
  public static double calcMean(final int[] arr) {
    // Initialize a variable for the sum
    double sum = 0.0;
    // Loop through the values of the array
    for (int num : arr) {
      // Increment the sum
      sum += num;
    }
    // Calculate the mean
    final double mean = sum / arr.length;
    // Return the mean
    return mean;
  }
  
  /**
   * Function that finds and returns the median of an array.
   * 
   * @param arr an array of integers. [MUST BE SORTED]
   * @return the median of an array.
   */
  public static double calcMedian(final int[] arr) {
    // Get the size of the array
    final int size = arr.length;
    // Initialize variable for the median
    double median;
    // Check if the array is evenly sized or oddly sized
    if (size % 2 == 0) {
      // If the array has an even size.
      // Set the median to the average of the middle two elements.
      median = (arr[size / 2] + arr[(size / 2) + 1]) / 2.0;
    } else {
      // If the array has an odd size.
      // Set the median to the value of the middle element.
      median = arr[size / 2];
    }
    // Return the median
    return median;
  }

  /**
   * Function that finds and returns the mode[s] of an array.
   * 
   * @param arr an array of integers. [MUST BE SORTED]
   * @return the mode[s] of an array.
   */
  public static int[] calcMode(final int[] arr) {
    // List to hold the mode[s]
    ArrayList<Integer> modesList = new ArrayList<>();
    // Variable for the population threshold to be a mode.
    int modePop = 0;
    // Variable for the population of a current chain of numbers.
    int currentPop = 0;
    // Variable for the current chain's number;
    int currentNum = 0;
    // Go through every number in the array
    for (int num : arr) {
      // Reset the chain if it's a new chain
      if (num != currentNum) {
        currentNum = num;
        currentPop = 0;
      }
      // Increment the population of the current chain by 1
      currentPop += 1;
      // Check if the population is bigger than the current mode threshold.
      if (currentPop > modePop) {
        // If so, make currentPop the new modePop
        modePop = currentPop;
        // and clear the list
        modesList.clear();
      }
      // Check if the population meets the current mode threshold.
      if (currentPop == modePop) {
        // If so, add it to the list of modes.
        modesList.add(num);
      }
    }
    // Convert the list of modes to an array
    int[] modes = new int[modesList.size()];
    for (int index = 0; index < modesList.size(); index++) {
      modes[index] = modesList.get(index);
    }
    // Return the array
    return modes;
  }


  /**
   * Entrypoint of the program.
   * @param args UNUSED.
   */
  public static void main(final String[] args) {
    try {
      // Access the input file and create a File object.
      File inputFile = new File(INPUT_FILE_PATH);
      // Scanner that will read the File Object.
      Scanner fileReader = new Scanner(inputFile);
      // Create list to store all the ints
      ArrayList<Integer> listOfInts = new ArrayList<>();
      // Loop through all available lines
      while (fileReader.hasNextLine()) {
        // Add the line to the list
        // [As an integer]
        try {
          // Convert line to an integer
          int num = Integer.parseInt(fileReader.nextLine());
          // Add the number to the list
          listOfInts.add(num);
        } catch (NumberFormatException error) {
          // If the line can't be converted to an integer,
          // the program just ignores the line and continues.
          continue;
        }
      }
      // Close the file reader
      fileReader.close();
      // Convert the list to an array
      int[] arrOfInts = new int[listOfInts.size()];
      for (int index = 0; index < listOfInts.size(); index++) {
        arrOfInts[index] = listOfInts.get(index);
      }
      // 

    } catch (IOException error) {
      System.out.println(error);
    }
  }
}
