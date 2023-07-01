package cn.idea360.ratelimiter.rule.parse;

import cn.idea360.ratelimiter.rule.RuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class RuleConfigParserTest {

	@Test
	public void parseYaml() {
		InputStream in = this.getClass().getResourceAsStream("/ratelimiter-rule.yaml");
		Representer represent = new Representer();
		represent.getPropertyUtils().setSkipMissingProperties(true);
		Yaml yaml = new Yaml(represent);
		RuleConfig ruleConfig = yaml.loadAs(in, RuleConfig.class);
		log.info(ruleConfig.toString());
	}

	@Test
	public void parseProperties() throws IOException {
		InputStream in = this.getClass().getResourceAsStream("/rule.properties");
		Properties properties = new Properties();
		properties.load(in);
		log.info(String.valueOf(properties));
	}

}