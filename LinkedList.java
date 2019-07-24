import java.util.*;
import java.io.*;

public class LinkedList<T extends Comparable<T>>
{
	private ListNode<T> head;
	private ListNode<T> tail;
	private int size;

	// Default constructor
	LinkedList()
	{
		this.head = null;
		this.tail = null;
	}

	// Constructor with head
	LinkedList(ListNode<T> head)
	{
		this.head = head;
		this.tail = head;
	}

	// Full constructor
	LinkedList(ListNode<T> head, ListNode<T> tail)
	{
		this.head = head;
		this.tail = tail;
	}

	public ListNode<T> getHead()
	{
		return this.head;
	}

	public ListNode<T> getTail()
	{
		return this.tail;
	}

	public int size()
	{
		return this.size;
	}

	public void setHead(ListNode<T> node)
	{
		this.head = node;
	}

	public void setTail(ListNode<T> node)
	{
		this.tail = node;
	}

	public void insertNode(ListNode<T> node)
	{
		if (node == null)
		{
			System.err.println("Error: given null node to insert");
			return;
		}

		// We have no head or tail node
		if (this.head == null && this.tail == null)
		{
			// Order 1 operation
			// Make our head and tail the new node
			this.head = node;
			this.tail = node;
			this.size++;
			return;
		}

		// We have a head node
		if (this.head != null)
		{
			// We still don't have a tail
			if (this.getTail() == null)
			{
				// The head has no next, so set that and the tail
				if (this.getHead().getNext() == null)
				{
					// Order 1 operation
					this.getHead().setNext(node);
					this.setTail(node);
					this.size++;
					return;
				}
				else
				{
					// The head has a next, so we need to loop through
					// until we have no more nodes to loop through
					ListNode<T> temp = this.getHead();

					// Order n operation
					while (temp.getNext() != null)
						temp = temp.getNext();

					temp.setNext(node);
					this.setTail(node);
					this.size++;
					return;
				}
			}
			else
			{
				// Order 1 operation

				// Insert at the tail because we have one
				// Note: This assumes that something links to the tail node
					// First, set the current tail's next to our new node
				this.getTail().setNext(node);
					// Then, set the list's tail to the new node
				this.setTail(node);

				this.size++;
				return;
			}
		}

		System.err.println("Error: complication in insert");
	}

	public void deleteNthNode(int index)
	{
		// Delete the nth node in the list
		ListNode<T> temp = this.getHead();
		ListNode<T> prev = temp; // Keep track of previous node

		// Catch any wrong indexes to delete
		if (index > this.size()-1 || index < 0)
		{
			System.err.println("Error: trying to delete node outside of list bounds");
		}

		// Catch head deletion
		// Note: 	We can't catch the tail deletion early
		//			because we need the previous node
		if (index == 0)
		{
			if (this.getHead().getNext() != null)
			{
				// The head has a next, so let's grab it
				// and check if it's the tail node
				if (this.getHead().getNext() == this.getTail())
				{
					this.setHead(this.getTail());
					this.size--;
					return;
				}
				else
				{
					// The head has a next that is not the tail
					// so make that the head
					this.setHead(this.getHead().getNext());
					this.size--;
					return;
				}
			}
			else
			{
				// The head has no next node, which means the list will be empty
				this.setHead(null);
				this.setTail(null);
				this.size = 0;
				return;
			}
		}

		// Order n operation
		// We're not deleting the head, so go on to the node at the index
		for (int i = 0; i < index; i++)
		{
			if (temp.getNext() != null)
			{
				prev = temp;
				temp = temp.getNext();
			}
			else
			{
				// We tried to move down the list but there is nothing there
				System.err.println("Error: tried to move to index "+i+" and found disconnection");
				return;
			}
		}

		// Set the previous node to the node after the one we want to delete
		prev.setNext(temp.getNext());
		this.size--;

		// Make sure we're not deleting the tail
		if (this.getTail() == temp)
		{
			// The node we deleted is the tail, so set the tail to the previous node
			this.setTail(prev);
		}
	}

	public void deleteValue(T data)
	{
		// Delete the node with the value 'data' in the array
		// We must do this manually to grab the previous value
		// However, this will be very similar to findValue_helper()
		ListNode<T> temp = this.getHead();
		ListNode<T> prev = temp;
		boolean found = false;

		// Until we find the value OR we hit a null node
		while (temp != null && !found)
		{
			if (temp.getData().compareTo(data) == 0)
			{
				// We found the node, so jump out
				found = true;
			}
			else
			{
				// We didn't find it, so goto the next one
				prev = temp;
				temp = temp.getNext();
			}
		}

		// Temp is not null, so we need to delete
		if (temp != null)
		{
			// We found the head and need to delete it
			if (this.getHead() == temp)
			{
				// We only have one node, so the list will be empty
				if (this.getHead().getNext() == null)
				{
					this.setHead(null);
					this.setTail(null);
					this.size--;
					return;
				}
				// We have two nodes, so make the tail the head now
				else if (this.getHead().getNext() == this.getTail())
				{
					this.setHead(this.getTail());
					this.size--;
					return;
				}
				// We have more than two nodes, so just grab the next one
				else
				{
					this.setHead(temp.getNext());
					this.size--;
					return;
				}
			}
			// We found the tail and need to delete it
			else if (this.getTail() == temp)
			{
				prev.setNext(null);
				this.setTail(prev);
				this.size--;
				return;
			}
			// It's neither the head nor tail so just delete it normally
			else
			{
				// Set the previous node's next to the node after
				prev.setNext(temp.getNext());
				this.size--;
				return;
			}
		}
		else
		{
			// We have nothing to delete, so let that be known
			System.out.println("Value "+data+" not found for deletion");
			return;
		}
	}

	public boolean findValue(T data)
	{
		// Returns true if the value 'data' is in the list
		if (findValue_helper(data) == null)
			return false;
		else
			return true;
	}

	public ListNode<T> findValue_helper(T data)
	{
		// Returns the node in the list with the value 'data'
		ListNode<T> temp = this.getHead();

		// Order n operation
		// Only enters the while-loop if temp is non-null,
		// so we need to have a head node to even start the search
		while (temp != null)
		{
			if (temp.getData().compareTo(data) == 0)
				return temp;
			else
				temp = temp.getNext();
		}

		// We've reached the end of the list and haven't found it,
		// or we've got a null head, so return null
		return null;
	}

	public void printList()
	{
		// Order n operation
		ListNode<T> temp = this.getHead();

		// Catch no list to print
		if (temp == null)
		{
			System.out.println("No list to print");
			return;
		}

		while (temp.getNext() != null)
		{
			System.out.print(temp.getData()+" ");
			temp = temp.getNext();
		}

		// Print the last node
		System.out.println(temp.getData());
	}
}