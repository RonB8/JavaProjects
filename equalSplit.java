/**
 * Two methods that work with one-dimensional and two-dimensional arrays.
 * More details before each method.
 *
 * @author Ron Bar
 * @version 01.01.2022
 */
public class equalSplit
{
    //question 1:
    /**
     * A method that tests whether it is possible to divide a given set into two different groups of equal size so that the sum of the organs in the 2 groups is equal.
     * Private method are attached to the class
     * @param arr the 1D array
     * return if the desired thing is indeed possible
     */
    public static boolean equalSplit (int[] arr)
    {
        return equalSplit (arr, -1, 0, 0, 0, 0);
    }
    //question 2:   
    /**
     * A method that calculates the longest slope in a given two-dimensional array when the numbers in the array are natural
     * Private methods are attached to the class
     * @param mat the 2D array
     * @param num the stub between jumps
     * @return the largest slop option
     */
    public static int longestSlope(int [][] mat,int num){
        return slopsLoop(mat,num,0,0,0);
    }

    //private Functions

    //question 1:
    /**
     * A method that examines what is required in the question by dividing the array into 2 different parts indicated by right and left,
    The index i runs on all members of the array and each time two options are examined - what happens if we weigh the current member to the right, and what happens if we weigh to the left
     * @param arr the 1D array
     * @param i the index runs on all members of the array 
     * @param left the number of cells of the left part
     * @param right the number of cells of the right part
     * @param sumLeft the cells of the left part were assembled into it
     * @param sumRight the cells of the right part were assembled into it
     * return if the desired thing is indeed possible
     */
    private static boolean equalSplit (int[] arr, int i, int left, int right, int sumLeft, int sumRight)
    {
        //Check if the number is even and if we weighed more than half of the members of the array (since in each part the number of members should be half of arr)
        if (arr.length%2!=0 || left>arr.length/2 || right>arr.length/2) return false;
        //Checks whether we have already summed up half of the organs for each part, and if the parts are indeed equal
        if (left==right && left==arr.length/2) return sumLeft==sumRight;
        //Split point - weighting the current limb to the right part || Left part
        return equalSplit (arr, i+1, left+1, right, sumLeft+arr[i+1], sumRight) ||equalSplit (arr, i+1, left, right+1, sumLeft, sumRight+arr[i+1]);
    }

    //question 2:
    /**
     * main slope finder` runs trow the 2D array and call slope counter to count the options if any exciting
     * @param mat the matrix (2D array) that was given by the user
     * @param num the stub size
     * @param rows the row flag
     * @param calms the calms flag
     * @param maxSum maximum slope found until now
     * @return the largest slope found in the array
     */
    private static int slopsLoop(int[][] mat,int num,int rows,int calms,int maxSum){
        // checks end condition if true means that we finished goring trow the array
        if(rows == mat.length){
            return maxSum;
        }
        //checking if end of calm was riced if true gow one row down
        if (calms == mat[0].length){
            return slopsLoop(mat,num,(rows+1),0,maxSum);
        }
        //setting a temporary sum holder
        int tempSum;
        //checks all options from the current place
        tempSum = slopsCounter(mat,num,rows,calms);
        // if temporary sum is bigger then the general sum put temporary in general
        if (tempSum > maxSum){
            maxSum = tempSum;
        }
        //continue loop
        return slopsLoop(mat,num,rows,(calms+1),maxSum);

    }

    /**
     * slope counter that checks any moving option in the current place (from original slope counter)
     * @param array the array that was given by the user
     * @param num the stub size
     * @param rows the row flag
     * @param calms the calms flag
     * @return the maximum slope found in the current place
     */
    private static int slopsCounter(int[][] array, int num , int rows, int calms){
        int check,total = 0;
        int coherentNumberToCheck = array[rows][calms];
        //checking slop if going up
        check = slopsCounter(array,num,(rows - 1),calms,0,coherentNumberToCheck);
        //if bigger then total replace total
        if (total < check){
            total = check;
        }
        //checking slop if going down
        check = slopsCounter(array,num,(rows + 1),calms,0,coherentNumberToCheck);
        //if bigger then total replace total
        if (total < check){
            total = check;
        }
        //checking slop if going right
        check = slopsCounter(array,num,rows,(calms + 1),0,coherentNumberToCheck);
        //if bigger then total replace total
        if (total < check){
            total = check;
        }
        //checking slop if going left
        check = slopsCounter(array,num,rows,(calms - 1),0,coherentNumberToCheck);
        //if bigger then total replace total
        if (total < check){
            total = check;
        }
        //returns larges slope
        return 1+total;

    }

    /**
     *checking slope options for specific place and direction
     * @param array the array that was given by the user
     * @param num the stub size
     * @param rows the row flag
     * @param calms the calms flag
     * @param sum the sum of the slope (Counter)
     * @param oldNum original number in the place before this one
     * @return  largest slope from original point
     */
    private static int slopsCounter (int[][] array, int num , int rows, int calms , int sum ,int oldNum){
        // if edge case is true return 0 no more moves available
        if (rows >= array.length || calms >= array[0].length || rows < 0 || calms < 0){
            return 0;
        }
        // if slope is possible  (the stub size between the numbers)
        if(oldNum - array[rows][calms] == num && array[rows][calms] > 0 )
        {
            int temp;
            //checks option one (moving one row up and calling slopsCounter
            temp = slopsCounter(array,num,(rows+1),calms, sum,array[rows][calms]);
            //if sum is smaller then temp change sum
            if (sum < temp)
                sum = temp;
            //checks option two (moving one row down and calling slopsCounter
            temp = slopsCounter(array,num,(rows-1),calms, sum,array[rows][calms]);
            //if sum is smaller then temp change sum
            if (sum < temp)
                sum = temp;
            //checks option two (moving one calm right and calling slopsCounter
            temp = slopsCounter(array,num,rows,(calms+1), sum,array[rows][calms]);
            if (sum < temp)
                sum = temp;
            //checks option two (moving one calm left and calling slopsCounter
            temp = slopsCounter(array,num,rows,(calms-1), sum,array[rows][calms]);
            //if sum is smaller then temp change sum
            if (sum < temp)
                sum = temp;
            //return largest slope from original testing  + 1 for the current one
            return 1 + sum;
        }
        //else the current jump is not legitimate slope
        return 0;
    }
}