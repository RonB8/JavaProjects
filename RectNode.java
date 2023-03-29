/**
 * The RectNode class represents a node in a linked list that has a rectangle.
 * @author Ron Boaron
 * @version 15.01.2022
 */
public class RectNode
{
    private RectangleA _rect;
    private RectNode _next;

    /**
     *  A constructor for a new rectangle without next pointer
     *  next will be set to null
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @param r rectangle to be set in the node
     */
    public RectNode (RectangleA r)
    {
        _rect = new RectangleA(r);
        _next = null;
    }
    
    /**
     * A constructor for a new rectangle with next pointer
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @param r rectangle to be set in the node
     * @param n next pointer
     */
    public RectNode (RectangleA r, RectNode n)
    {
        _rect = new RectangleA(r);
        _next = n;
    }
    
    /**
     * Copy constructor for a RectNode
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @param r RectNode to clone
     */
    public RectNode (RectNode r)
    {
        _rect = new RectangleA(r._rect);
        _next = r._next;
    }
    
    /**
     * Return RectangleA object with all the information
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @return RectangleA
     */
    public RectangleA getRect()
    {
        return new RectangleA(_rect);
    }
    
    /**
     * Returns next pointer
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @return next pointer
     */
    public RectNode getNext()
    {
        return _next;
    }
    
    /**
     * Sets new RectangleA to the object
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @param r rectangle to be set
     */
    public void setRect(RectangleA r)
    {
        _rect= new RectangleA(r);
    }
    
    /**
     * Sets a new next pointer to the object
     * the function complicity of time is O(1)
     * the function complicity of space is O(1)
     * @param next next pointer
     */
    public void setNext( RectNode next)
    {
        _next = next;
    }
}
