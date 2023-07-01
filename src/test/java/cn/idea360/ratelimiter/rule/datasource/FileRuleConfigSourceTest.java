package cn.idea360.ratelimiter.rule.datasource;

import cn.idea360.ratelimiter.rule.RuleConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class FileRuleConfigSourceTest {

	@Test
	public void load() {
		FileRuleConfigSource ruleConfigSource = new FileRuleConfigSource();
		RuleConfig ruleConfig = ruleConfigSource.load();
		log.info(ruleConfig.toString());
	}

}