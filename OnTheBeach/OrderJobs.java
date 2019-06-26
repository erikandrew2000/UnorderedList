package OnTheBeach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/*
 how I solved the problem: I used pen and paper to see what steps I would take to order such a list by hand;
 my solution was to go downwards on the right hand side of the list, for each line adding the right hand
 side letter to the ordered list, then the left hand side letter in the right position; once that was done, I would
 add any remaining letters which were not in a pair, adding them to the right of the ordered list; I tried a couple
 of examples and found that it worked; I then implemented the same steps in code

 establishing whether a pattern is circular was more challenging; I did the same, wrote the pattern on paper
 and looked for ways I can verify whether it's circular or not; once I found the pattern (detailed below in
 the code), I implemented the check in my code

 it is a brute force solution, so it is possible that someone might come up with a more efficient and elegant
 solution; as the task does not suggest large data sets, time&space complexity were not considered

*/

public class OrderJobs {

    public static void main (String [] args) {
        System.out.println(orderJobs("test11.txt"));
    }

    public static String orderJobs (String fileName) {

        FileReader fr;
        BufferedReader br;

        //first get the number of rows in the unordered list (i):
        int i=0;
        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String s = br.readLine();
            while (s!=null) {
                i++;
                s = br.readLine();
            }
            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }

        //create array of strings of length i to store the rows from the document:
        String [] [] jobs = new String [i] [];

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String s = br.readLine();
            int l=0;
            while (s!=null) {
                //split each row into letters before and after regex:
                jobs[l]=s.split(" => ");
                s = br.readLine();
                l++;
            }
            br.close();
            fr.close();

            //now I've got a 2d array, [0] is left side of regex, [1] is right side of regex

        } catch (IOException e) {
            System.out.println("error: "+e.getMessage());
        }

        //go through array; for first index that has a sub-array of length 2, i.e. jobs[i].length>1, add jobs[i][1]
        //to linkedlist of letters; for the next indexes with length 2, add before or after existing element, depending
        //on whether it has to be smaller or larger

        LinkedList<String> output = new LinkedList<>();
        for (int j=0; j<jobs.length; j++) {
            if (jobs[j].length>1) {
                if (jobs[j][0].equals(jobs[j][1]))
                {
                    return "Error: jobs cannot depend on themselves";
                }
                if (!output.contains(jobs[j][1])) {
                    if (!output.contains(jobs[j][0])) {
                        output.add(jobs[j][1]);
                        output.add(jobs[j][0]);
                    }
                    else {
                        int temp=output.indexOf(jobs[j][0]);
                        output.add(temp,jobs[j][1]);
                    }
                }
                if (!output.contains(jobs[j][0])) {
                    int temp=output.indexOf(jobs[j][1]);
                    output.add(temp+1,jobs[j][0]);
                }
            }

        }

        for (int j=0; j<jobs.length; j++) {
            if (!output.contains(jobs[j][0]))
                output.add(jobs[j][0]);
        }

        //check for circularity, which occurs in two patterns as below:
//        1. b => c  (same letters diagonally)
//           ...
//           c => b
//
//        2. b => c  (letter on right propagated downwards diagonally, letter on the right is propagated on right)
//           ...
//           c => f
//           ...
//           f => b
        //if circular pattern found, display message, end program

        for (int j=0; j<jobs.length; j++) {
                if (jobs[j].length > 1) {
                    String tempRight = jobs[j][1];
                    String tempLeft = jobs[j][0];
                    int k = 0;
                        while (k < jobs.length) {
                            if (jobs[k].length>1) {
                                if (jobs[k][0].equals(tempRight)) {
                                    if (jobs[k][1].equals(tempLeft) || jobs[k][1].equals(jobs[j][0])) {
                                        return "Error: jobs can't have circular dependencies";

                                    }
                                    tempRight = jobs[k][1];
                                    tempLeft = jobs[k][0];
                                }
                            }
                            k++;
                        }
                }
        }

        return output.toString();
    }
}
