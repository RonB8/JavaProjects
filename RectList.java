/**
 * Represents information about rectangles
 * rectangle in lined list mode!
 * @author Ron Boaron
 * @version 15.01.2022
 */
public class RectList
{
    private RectNode _head;

    /**
     * A constructor for a new list empty of triangle
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     */
    public RectList()
    {
        _head = null;
    }

    /**
     * accepts as a rectangle parameter and adds it to the end of the list.
     * If this rectangle already on the list, the method will do nothing.
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @param r RectangleA
     */
    public void addRect(RectangleA r)
    {
        RectNode node = new RectNode(r,null);
        // checks if edge cases accrues
        if(empty()){
            _head = node;
        }
        else
        {
            //The prevNode variable so as not to "lose" the list - will stop at the last node
            RectNode currentNode = _head, prevNode=null;
            // loop threw the list as long as the next rectNode is not empty
            while (currentNode!=null){
                if (currentNode.getRect().equals(r)) return;
                prevNode = currentNode;
                currentNode=currentNode.getNext();   
            }
            prevNode.setNext(node);
        }
    }

    /**

     * The number of rectangles in the list whose southwestern point is p.
     * If there are no rectangles in the list (i.e. the list is empty) or there are no rectangles in the list whose point p is southwest, the method will return 0.
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @return number of residents
     */
    public int howManyWithPoint(Point p)
    {
        int count=0;
        // checks if edge cases accrues
        if (empty()) return count;
        RectNode current = _head;
        // loop threw the list as long as the next rectnode is not empty
        while(current !=null)
        {
            if ((current .getRect().getPointSW()).equals(p)) count++;
            current = current.getNext();
        }
        return count;
    }

    /**
     * Returns the longest diagonal length between all rectangles.
     * if there are no rectangles in the list (that is, the list is empty) the method will return 0.
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @return returns the longest distance between two Cities
     */
    public double longestDiagonal()
    {
        // checks if edge cases accrues
        if (empty()) return 0;
        RectNode current = _head;
        double temp=0;
        // loop threw the list as long as the next rectnode is not empty
        while (current!=null)
        {
            // if true found a longer Diagonal in temp;
            if ((current.getRect().getDiagonalLength()) > temp)
                temp = (current.getRect()).getDiagonalLength();
            current = current.getNext();
        }
        return temp;
    }

    /**
     * Returns a replica of the southwestern point of the most rectangle left on the list.
     * one rectangle is more left-handed than another rectangle if the southwestern point its is to the left of the southwestern point of the other rectangle.
     * if there is more than one rectangle one of which is the leftmost, the method returns a replica of the southwestern point of
     * the first rectangle among them on the list.
     * if there are no rectangles in the list (i.e. the list is empty) The method will return null.
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @Returns the leftmost southwest point that exists in the rectangle from the list
     */
    public Point mostLeftRect()
    {
        // checks if edge cases accrues
        if (empty()) return null;
        //if not set current to the first city
        RectNode current = _head;
        RectNode rectMostWest = current;
        current = current.getNext();
        // loop threw the list as long as current is not empty
        while (current!=null)
        {
            //checking if thar are any triangles southern and western than current triangle
            if ((current.getRect().getPointSW()).isLeft(rectMostWest.getRect().getPointSW()))
                rectMostWest = current;
            current=current.getNext();
        }
        return new Point(rectMostWest.getRect().getPointSW());
    }

    /**
     * The highestRect method returns a copy of the northeast point of the highest rectangle in the list.
     * one rectangle is higher than another rectangle if its northeastern point is high more than that of the other.
     * if there is more than one rectangle at the highest height, the method returns a replica of the northeastern point of the first rectangle on the list.
     * if there are no rectangles in the list (i.e. the list is empty) the method will return null.
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @Returns the leftmost southwest point that exists in the rectangle from the list
     */
    public Point highestRect()
    {
        if (empty()) return null;
        RectNode p = _head;
        RectNode rectMostNorth = p;
        p = p.getNext();
        while (p!=null)
        {
            if ((p.getRect().getPointNE()).isAbove(rectMostNorth.getRect().getPointNE()))
                rectMostNorth = p;
            p=p.getNext();
        }
        return new Point(rectMostNorth.getRect().getPointNE());
    }

    /**
     * returns a new rectangle (object from the RectangleA class) which is the rectangle with the minimum area that contains all the rectangles in the list.
     * if there are no rectangles in the list (i.e. the list is empty) the method will return null.
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @Return a rectangle that contains all the rectangles from the list
     */
    public RectangleA minimalContainer()
    {
        Point pSW = new Point(mostLeftRect().getX(), lowestRect().getY());
        Point pNE = new Point(mostRightRect().getX(), highestRect().getY());
        return new RectangleA(pSW,pNE);
    }

    /**
     * Returns a string of characters that represents the list of rectangles.
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @return a string of all the rectangles list
     */
    public String toString()
    {
        String s = "The list has "+ numOfRectangles()+" rectangles.";
        if (empty()) return "";
        RectNode p = _head;
        for(int i=1; p!=null; i++,p=p.getNext())
        {
            s+= "\n"+ i+". "+ p.getRect().toString();
        }
        return s;
    }

    //==================================================Private functions===============================================

    /**
     * privte function that return if the list is empty
     * the function complicity of time is O(1)
     * the function complicity of space is O(0)
     * @return if the list is empty.
     */
    private boolean empty() { return _head==null;}

    /**
     * returns the rightmost point of the rightmost rectangle
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @returns the rightmost point in the list of rectangles
     */
    private Point mostRightRect()
    {
        if (empty()) return null;
        RectNode p = _head;
        RectNode rectMostEast = p;
        p = p.getNext();
        while (p!=null)
        {
            if ((p.getRect().getPointNE()).isRight(rectMostEast.getRect().getPointNE()))
                rectMostEast = p;
            p=p.getNext();
        }
        return new Point(rectMostEast.getRect().getPointNE());
    }

    /**
     * returns the most low point of the most low rectangle
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @returns the most low point in the list of rectangles
     */
    private Point lowestRect()
    {
        if (empty()) return null;
        RectNode p = _head;
        RectNode rectMostSouth = p;
        p = p.getNext();
        while (p!=null)
        {
            if ((p.getRect().getPointSW()).isUnder(rectMostSouth.getRect().getPointSW()))
                rectMostSouth = p;
            p=p.getNext();
        }
        return new Point(rectMostSouth.getRect().getPointSW());
    }

    /**
     * returns the number of rectangles in the list
     * the function complicity of time is O(n)
     * the function complicity of space is O(1)
     * @return the length of the list
     */
    private int numOfRectangles()
    {
        if(empty()) return 0;
        RectNode p=_head;
        int i=0;
        do{
            p=p.getNext();
            i++;
        }
        while(p!=null);
        return i;
    }
}
