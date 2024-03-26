package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		tags = "@sana",
		glue= {"StepDef"},
		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true
		)
public class TestRunner {
}
