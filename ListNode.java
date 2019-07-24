import java.util.*;
import java.io.*;

public class ListNode<T extends Comparable<T>>
{
	private T data;
	private ListNode<T> next;

	ListNode(T data)
	{
		this.data = data;
		this.next = null;
	}

	public T getData()
	{
		return this.data;
	}

	public ListNode<T> getNext()
	{
		return this.next;
	}

	public void setData(T data)
	{
		this.data = data;
	}

	public void setNext(ListNode<T> node)
	{
		this.next = node;
	}
}