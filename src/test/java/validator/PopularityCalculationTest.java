package validator;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

import validator.cluster.Category;
import validator.cluster.Cluster;

public class PopularityCalculationTest {
	@Test
	public void shouldReturnThePopularityOfCategoriesWhenTheClusterHasThreeCategories() {
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
	public void shouldReturnZeroPopularityWhenThereNoCategoriesInTheCluster(){
		Cluster bi = new Cluster();
		assertEquals(0.0, bi.getPopularity("Category"), 0.001);
	}
	
	@Test
	public void shouldReturnAPopularityOfOneWhenThereIsOneCategoryInTheCluster(){
		Category c1 = Mockito.mock(Category.class);
		Mockito.when(c1.size()).thenReturn(50);
		Cluster bi = new Cluster();
		bi.add(c1);
		assertEquals(1, bi.getPopularity(c1), 0.001);
	}
	
	@Test
	public void shouldReturnZeroPopularityWhenThereAreNoDocumentsInTheCategories(){
		Category c1 = Mockito.mock(Category.class);
		Category c2 = Mockito.mock(Category.class);
		Category c3 = Mockito.mock(Category.class);
		Mockito.when(c1.size()).thenReturn(0);
		Mockito.when(c2.size()).thenReturn(0);
		Mockito.when(c3.size()).thenReturn(0);
		Set<Category> categories = new HashSet<Category>();
		categories.add(c1);
		categories.add(c2);
		categories.add(c3);
		
		Cluster bi = new Cluster();
		bi.add(categories);
		
		assertEquals(0.0, bi.getPopularity(c1), 0.001);
		assertEquals(0.0, bi.getPopularity(c2), 0.001);
		assertEquals(0.0, bi.getPopularity(c3), 0.001);
	}
	

	@Test
	public void shouldReturnThePopularityOfCategoriesWhenThereAreNoDocumentsForOneCategoryInTheCluster(){
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
