package test.cases;

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
}
