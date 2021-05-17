import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api_features",
        glue = "steps/api_steps",
        plugin = {"pretty", "json:target/runner.json", "junit:target/junit.xml"}
)
public class Runner { }
