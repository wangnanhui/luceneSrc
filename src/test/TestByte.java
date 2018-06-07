package test;

import org.apache.lucene.util.BytesRef;

public class TestByte {
	public static void main(String[] args) {
		
		byte [] b = {74, 65, 73, 74, 30};
		BytesRef ref = new BytesRef(b);
		System.out.println(ref.toString());
		
		
	}

}
