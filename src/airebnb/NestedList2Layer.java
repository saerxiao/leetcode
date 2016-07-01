package airebnb;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
ArrayList Iterator
nested ArrayList

[[],[1,2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]]

[[2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]]

1 2 3 4 5 6 7 8 9 10

1.boolean hasNext()
return true or false if there is another element in the set
2.int next()
return the value of the next element in the array
3.void remove()
remove the last element returned by the iterator.  That is, remove the element that the previous next() returned
This method can be called only once per call to next(), otherwise, an exception will be thrown. 
 
 * If you need more classes, simply define them inline.
 */

class NestedList2Layer implements List<Integer> {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java 8.");

    for (String string : strings) {
      System.out.println(string);
    }
    
    NestedList2Layer list = new NestedList2Layer();
    List<List<Integer>> data = new ArrayList<>();
    List<Integer> l0 = new ArrayList<>();
    List<Integer> l1 = new ArrayList<>();
    l1.add(1);
    List<Integer> l2 = new ArrayList<>();
    l2.add(2);
    l2.add(3);
    List<Integer> l3 = new ArrayList<>();
    l3.add(4);
    l3.add(5);
    data.add(null);
    data.add(l0);
    data.add(l1);
    data.add(l2);
    data.add(l0);
    data.add(l3);
    
    list.data = data;
    Iterator<Integer> it = list.iterator();
    
    while (it.hasNext()) {
      int next = it.next();
      System.out.print(next + " ");
      if (next == 3) {
    	  it.remove();
      }      
    }
    System.out.println(data.size());
  }
  
  List<List<Integer>> data;
  
  @Override
  public Iterator<Integer> iterator() {
	  return new ListIterator();
  }
  
  private class ListIterator implements Iterator<Integer> {
    
	  private Iterator<List<Integer>> pit;
	  private Iterator<Integer> cit;
	  private int currentSize = 0;
	  private int totalSize;
    
    ListIterator() {
    	totalSize = getTotalSize();
    	pit = data.iterator();   
    }
    
    private int getTotalSize() {
    	int cnt = 0;
    	for (List<Integer> list : data) {
    		if (list != null) {
    			cnt += list.size();
    		}
    		
    	}
    	return cnt;
    }
    
    public boolean hasNext() {
    	return currentSize < totalSize;
    }
    
    public Integer next() {
    	if (hasNext()) {
    		currentSize++;
    		if (cit != null && cit.hasNext()) {
    			return cit.next();
    		} else {
    			while (pit.hasNext()) {
    				List<Integer> clist = pit.next();
    				if (clist != null && !clist.isEmpty()) {
    					cit = clist.iterator();
    					return cit.next();
    				}
    			}
    			throw new RuntimeException("the program should not come here");
    		}
    	} else {
    		throw new RuntimeException("End of iterator.");
    	}
    }
    
    public void remove() {
      cit.remove();
      totalSize--;
      currentSize--;
    }
    
  }

@Override
public int size() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean contains(Object o) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Object[] toArray() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <T> T[] toArray(T[] a) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean add(Integer e) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean remove(Object o) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean containsAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean addAll(Collection<? extends Integer> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean addAll(int index, Collection<? extends Integer> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean removeAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean retainAll(Collection<?> c) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public void clear() {
	// TODO Auto-generated method stub
	
}

@Override
public Integer get(int index) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Integer set(int index, Integer element) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void add(int index, Integer element) {
	// TODO Auto-generated method stub
	
}

@Override
public Integer remove(int index) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int indexOf(Object o) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int lastIndexOf(Object o) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public java.util.ListIterator<Integer> listIterator() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public java.util.ListIterator<Integer> listIterator(int index) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Integer> subList(int fromIndex, int toIndex) {
	// TODO Auto-generated method stub
	return null;
}


}
