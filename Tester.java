import java.util.*;
import java.io.*;

public class Tester
{
	public static int getRandInt(int max)
	{
		return (int)(Math.random() * max);
	}

	public static void testLinkedList()
	{
		LinkedList<Integer> list = new LinkedList<>();

		for (int i = 0; i < 20; i++)
			list.insertNode(new ListNode<Integer>(getRandInt(50)));
	
		// Solution: prints out 20 random numbers
		list.printList(); // Test that everything was inserted correctly
		// Solution: 20
		System.out.println(list.size()); // Test that the size is correct

		// Deletes the 6th node
		list.deleteNthNode(5);
		list.printList();

		// Deletes the last node
		list.deleteNthNode(18);
		list.printList();

		// Deletes the head node
		list.deleteNthNode(0);
		list.printList();

		// Mass search
		for (int i = 0; i < 100; i++)
		{
			int num = getRandInt(50);
			System.out.print("Searching for "+num+"...");

			if (list.findValue(num))
				System.out.println("found");
			else
				System.out.println("not found");
		}

		// Mass deletion
		for (int i = 0; i < 100; i++)
			list.deleteValue(getRandInt(50));

		list.printList();
		System.out.println("Head: "+list.getHead()+", "+list.getHead().getData());
		System.out.println("Tail: "+list.getTail()+", "+list.getTail().getData());
	}

	public static void main(String [] args)
	{
		//==========================================//
		//=====:	Linked List Testing		:=======//
		//==========================================//
		testLinkedList();

		//==========================================//
		//=====:	Bloom Filter Testing	:=======//
		//==========================================//

		//==========================================//
		//=====:		BST Testing			:=======//
		//==========================================//

		//==========================================//
		//=====:	2-4 Tree Testing		:=======//
		//==========================================//

		//==========================================//
		//=====:	Linked List Testing		:=======//
		//==========================================//
	}
}