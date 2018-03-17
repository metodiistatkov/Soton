import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {
	//declaring characteristics of the circular array
	private int arraySize;
	private int capacity = 10;
	private int head;
	private int tail;
	private Integer[] circularArray;

	//a method which returns the size
	public int size()
	{
		return arraySize;
	}

	//constructor, where characteristics of the array are initialized
	public CircularArrayQueue() {
		circularArray = new Integer[capacity];
		arraySize=0;
		head = 0;
		tail = 0;
	}

	//elements are added to the end of the array
	@Override
	public void enqueue(int in) {
		if(size() == capacity) {
			resize();
		} else {
			//resizing the array
			//adding new element to the array by creating a new array, which has 1 more space
			Integer[] newArray = new Integer[arraySize+1];
			//copying all elements from the old(smaller) array into the new one
			System.arraycopy(circularArray, head, newArray, 0, size());
			circularArray = newArray;
			//giving appropriate values to head and tail
			head = 0;
			tail = arraySize;
		}
		circularArray[tail] = in;
		tail = (tail+1) % circularArray.length;
		arraySize++;
	}

	//elements are removed from the beginning of the array
	@Override
	public int dequeue() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("No elements to remove");
		}
		else {
			Integer dequeuedElement = circularArray[head];
			circularArray[head] = null;
			head = (head + 1) % circularArray.length;
			arraySize--;
			return dequeuedElement;
		}


	}

	//getting the number of items in the array
	@Override
	public int noItems() {
		return arraySize;
	}

	//if the array is empty it has 0 elements
	@Override
	public boolean isEmpty() {
		return (arraySize == 0);
	}

	//the remaining free space left can be obtained by subtracting the number of elements inserted from the original size
	public int getCapacityLeft() {
		return capacity - arraySize;
	}

	//array is resized by introducing a new array, which has twice the capacity of the old one
	//all elements from the old one are copied to the new one
	//as a result there is a new array with the same elements but larger size
	public void resize() {
		Integer [] expanded = new Integer[capacity*2];
		capacity = capacity*2;
		System.arraycopy(circularArray, head, expanded, 0, circularArray.length);
		head = 0;
		tail = arraySize;
		circularArray = expanded;
	}
}
