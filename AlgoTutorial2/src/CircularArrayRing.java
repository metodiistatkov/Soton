import java.util.AbstractCollection;
import java.util.Iterator;

public class CircularArrayRing<E>  extends AbstractCollection<E> implements Ring<E>{
	//properties of the ring
	private E [] ring;
	private static int defaultSize = 10;
	private int indexToAdd = 0;
	int numOfElements = 0;
	
	//implementing an iterator by 
	//creating a private nested class 
	//that implements the Iterator interface
	private class RingIterator implements Iterator{
		int index=0;
		@Override
		public boolean hasNext() {
			//if the index requested is present in the array
			if(index<size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return get(index++);
		}
		
		@Override
		public void remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
	}
	
	//default constructor
	@SuppressWarnings("unchecked")
	public CircularArrayRing() {
		this(defaultSize);
	}
	
	//constructor with custom size
	@SuppressWarnings("unchecked")
	public CircularArrayRing (int size) {
		defaultSize =size;
		ring = (E[]) new Object [size];
		
	}
	
	//adding an element
	@Override
	public boolean add(E e) {
		ring[indexToAdd % defaultSize] = e;
		indexToAdd ++;
		numOfElements++;
		return true;
	}

	//retrieving an element
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		
		if(index >= size() || index >= indexToAdd || index < 0) {
		throw new IndexOutOfBoundsException();
		}
		else {
			//retrieving the last added element first
			return ring[(defaultSize + indexToAdd -index -1) % defaultSize];
		}
	}
	
	//getter for the iterator
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> iterator()  {
		return new RingIterator();
	}

	//the number of elements in the array
	@Override
	public int size() {
		//since the array is circular
		//if number of elements becomes larger than the default size
		//return the default size of the array
		return Math.min(numOfElements, defaultSize);
	}

}
