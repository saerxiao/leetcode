package airebnb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NestedList implements List<Integer> {
	
	public static void main(String[] args) {
	    ArrayList<String> strings = new ArrayList<String>();
	    strings.add("Hello, World!");
	    strings.add("Welcome to CoderPad.");
	    strings.add("This pad is running Java 8.");

	    for (String string : strings) {
	      System.out.println(string);
	    }
	    
	    NestedList clist1 = new NestedList();
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
	    clist1.data = data;
	    NestedList list = new NestedList();
//	    list.data = data;
	    list.data.add(clist1);
	    
	    List<Integer> clist2 = new ArrayList<>();
	    clist2.add(6);
	    list.data.add(clist2);
	    
	    System.out.println(list.getTotalSize());
	    Iterator<Integer> it = list.iterator();
	    while (it.hasNext()) {
	      int next = it.next();
	      System.out.print(next + " ");
	      if (next == 3) {
	    	  it.remove();
	      }      
	    }
	    System.out.println();
	    System.out.println(list.getTotalSize());
	  }
	
	private List<List<Integer>> data = new ArrayList<>();
	
	@Override
	public Iterator<Integer> iterator() {
		return new NestedListIterator();
	}
	
	private class NestedListIterator implements Iterator<Integer> {
		Iterator<List<Integer>> pit;
		Iterator<Integer> cit;
		
		int totalSize;
		int currentSize;
		
		NestedListIterator() {
			totalSize = getTotalSize();
			pit = data.iterator();
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
						
						if (clist != null && clist.iterator().hasNext()) {
							cit = clist.iterator();
							return cit.next();
						}
					}
					throw new RuntimeException("Program should not come here");
				}
			} else {
				throw new RuntimeException("End of the iterator.");
			}
		}
		
		public void remove() {
			cit.remove();
			currentSize--;
			totalSize--;
		}
	}
	
	public int getTotalSize() {
		int cnt = 0;
    	for (List<Integer> list : data) {
    		if (list != null) {
    			cnt += list.size();
    		}
    		
    	}
    	return cnt;
	}

	@Override
	public int size() {
		return size(data);
	}
	
	private int size(List<List<Integer>> data) {
		int cnt = 0;
		for (List<Integer> list : data) {
			if (list instanceof NestedList) {
				cnt += size(((NestedList) list).data);
			} else {
				if (list != null) {
					cnt += list.size();
				}				
			}
		}
		return cnt;
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
	public ListIterator<Integer> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<Integer> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
