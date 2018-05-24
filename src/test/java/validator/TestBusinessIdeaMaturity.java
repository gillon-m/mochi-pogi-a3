package validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class TestBusinessIdeaMaturity {

	@Test
	public void testMaturity() {
		Cluster bi = new Cluster();
		
		Category c1 = Mockito.mock(Category.class);
		Mockito.when(c1.size()).thenReturn(20);
		Mockito.when(c1.relevance()).thenReturn(0.3);
		Category c2 = Mockito.mock(Category.class);
		Mockito.when(c2.size()).thenReturn(10);
		Mockito.when(c2.relevance()).thenReturn(0.5);
		Category c3 = Mockito.mock(Category.class);
		Mockito.when(c3.size()).thenReturn(30);
		Mockito.when(c3.relevance()).thenReturn(0.7);
		Category c4 = Mockito.mock(Category.class);
		Mockito.when(c4.size()).thenReturn(10);
		Mockito.when(c4.relevance()).thenReturn(0.1);
		Category c5 = Mockito.mock(Category.class);
		Mockito.when(c5.size()).thenReturn(30);
		Mockito.when(c5.relevance()).thenReturn(0.8);
		
		bi.add(c1);
		bi.add(c2);
		bi.add(c3);
		bi.add(c4);
		bi.add(c5);
		
		assertEquals(0.57, bi.maturity(), 0.001);
	}
}
