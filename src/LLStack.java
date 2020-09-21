/**
 * This class will use Nodes to form a linked list. It implements the LIFO (Last
 * In First Out) methodology to reverse the input string.
 *
 **/

public class LLStack
{

    private Node head;

    // Constructor with no parameters for outer class
    public LLStack()
    {
        // to do
    }

    // This is an inner class specifically utilized for LLStack class,
    // thus no setter or getters are needed
    private class Node
    {
        private Object data;
        private Node next;

        // Constructor with no parameters for inner class
        public Node()
        {
            this(null, null);
        }

        // Parametrized constructor for inner class
        public Node(Object newData, Node nextLink)
        {
            // to do: Data part of Node is an Object
            // to do: Link to next node is a type Node
            this.data = newData;
            this.next = nextLink;
        }
    }

    // Adds a node as the first node element at the start of the list with the
    // specified data.
    public void addToStart(Object itemData)
    {
        // to do
        // NOTE: the logic here could be implemented in a single line,
        // but not required to be a one liner.
        this.head = new Node(itemData, this.head);
    }

    // Removes the head node and returns the data Object being
    // deleted.
    // Returns null if the list is empty.
    public Object deleteHead()
    {
        if (this.head == null)
        {
            return null;
        }
        Object toReturn = this.head.data;
        this.head = this.head.next;
        return toReturn;
    }

    // Returns the size of linked list by traversing the list
    public int size()
    {
        int count = 0;
        Node curNode = this.head;
        while (curNode != null)
        {
            curNode = curNode.next;
            count++;
        }
        return count;
    }

    // Finds if there is match for the given object
    public boolean contains(Object item)
    {
        Node curNode = this.head;
        while (curNode != null)
        {
            if (item.equals(curNode.data))
            {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    // Finds the first node containing the target item, and returns a
    // reference to that node. Return null if target not found.
    private Node findData(Object target)
    {
        Node current = head;
        Object itemAtPosition;
        while (current != null)
        {
            itemAtPosition = current.data;

            if (itemAtPosition.equals(target))
                return current;
            current = current.next;
        }
        return null; // Target not found!
    }

    public void outputList()
    {
        Node current = head;
        while (current != null)
        {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public String toString()
    {
        String retValue = "";
        Node current = head;

        while (current != null)
        {
            retValue += current.data.toString() + " ";
            current = current.next;
        }
        return retValue;
    }

    public boolean isEmpty()
    {
        return this.head == null;
    }

    public void clear()
    {
        this.head = null;
    }

    // For two lists to be equal they must contain the same data items in
    // the same order. The equals method of T is used to compare data items.
    public boolean equals(Object otherObject)
    {
        if (otherObject == null)
            return false;

        else if (!(otherObject instanceof LLStack))
            return false;

        else
        {
            LLStack otherList = (LLStack) otherObject;
            if (size() != otherList.size())
                return false;
            Node position = head;
            Node otherPosition = otherList.head;
            while (position != null)
            {
                if (!(position.data.equals(otherPosition.data)))
                    return false;
                position = position.next;
                otherPosition = otherPosition.next;
            }
            return true; // objects are the same
        }
    }

}
