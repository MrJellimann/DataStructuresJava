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
}