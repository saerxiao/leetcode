package airebnb;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedListStack {

	public static Iterator<Integer> getIterator(final List<Object> in)
	{
		return new Iterator<Integer>() {

			private List<Object> input;
			private List<Object> current;
			private int currentIndex;
			private Stack<Iterator<Object>> previous;
			
			{
				this.input = in;
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Integer next() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
}
