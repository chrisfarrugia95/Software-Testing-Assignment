package testing;


import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@Cucumber.Options(format={"pretty", "html:target/cucumber"}, glue = { "step_definitions" }, features = { "src/features" }, monochrome = true)
public class RunBDD {
}
