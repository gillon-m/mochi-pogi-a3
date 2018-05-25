package validator;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;

import validator.steps.RemoveKeywordWhenEmptySteps;

public class RemoveKeywordWhenEmptyTest extends JUnitStory {
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new RemoveKeywordWhenEmptySteps());
	}
	
	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryLoader(
				new LoadFromClasspath(getClass().getClassLoader())).useStoryReporterBuilder(
						new StoryReporterBuilder().withFormats(Format.CONSOLE));
	}
	
	@Override
	@Test
	public void run() throws Throwable {
		super.run();
	}
}
