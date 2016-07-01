package data.structure;

public interface Heap<T> {

	public int getSize();
	
	public void add(T t);
	
	public T pop();
	
	public boolean isEmpty();
}
