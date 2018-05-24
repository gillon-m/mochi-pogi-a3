package validator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import validator.Category;
import validator.Cluster;

public class TestPopularityCalculation {
	@Test
	public void testPopularityCalculation() {
		Category c1 = Mockito.mock(Category.class);
		Category c2 = Mockito.mock(Category.class);
		Category c3 = Mockito.mock(Category.class);
		Mockito.when(c1.size()).thenReturn(50);
		Mockito.when(c2.size()).thenReturn(30);
		Mockito.when(c3.size()).thenReturn(20);
		
		Cluster bi = new Cluster();
		bi.add(c1);
		bi.add(c2);
		bi.add(c3);
		
		assertEquals(0.5, bi.getPopularity(c1), 0.001);
		assertEquals(0.3, bi.getPopularity(c2), 0.001);
		assertEquals(0.2, bi.getPopularity(c3), 0.001);
	}

	@Test
	public void testNoCategories(){
		Cluster bi = new Cluster();
		assertEquals(0.0, bi.getPopularity("Category"), 0.001);
	}
	
	@Test
	public void testOneCategory(){
		Category c1 = Mockito.mock(Category.class);
		Mockito.when(c1.size()).thenReturn(50);
		Cluster bi = new Cluster();
		bi.add(c1);
		assertEquals(1, bi.getPopularity(c1), 0.001);
	}
	
	@Test
	public void testNoDocuments(){
		Category c1 = Mockito.mock(Category.class);
		Category c2 = Mockito.mock(Category.class);
		Category c3 = Mockito.mock(Category.class);
		Mockito.when(c1.size()).thenReturn(0);
		Mockito.when(c2.size()).thenReturn(0);
		Mockito.when(c3.size()).thenReturn(0);
		
		Cluster bi = new Cluster();
		bi.add(c1);
		bi.add(c2);
		bi.add(c3);
		
		assertEquals(0.0, bi.getPopularity(c1), 0.001);
		assertEquals(0.0, bi.getPopularity(c2), 0.001);
		assertEquals(0.0, bi.getPopularity(c3), 0.001);
	}
	

	@Test
	public void testNoDocumentsForOneCategory(){
		Category c1 = Mockito.mock(Category.class);
		Category c2 = Mockito.mock(Category.class);
		Category c3 = Mockito.mock(Category.class);
		Mockito.when(c1.size()).thenReturn(0);
		Mockito.when(c2.size()).thenReturn(2);
		Mockito.when(c3.size()).thenReturn(3);
		
		Cluster bi = new Cluster();
		bi.add(c1);
		bi.add(c2);
		bi.add(c3);
		
		assertEquals(0.0, bi.getPopularity(c1), 0.001);
		assertEquals(0.4, bi.getPopularity(c2), 0.001);
		assertEquals(0.6, bi.getPopularity(c3), 0.001);
	}
	
}
