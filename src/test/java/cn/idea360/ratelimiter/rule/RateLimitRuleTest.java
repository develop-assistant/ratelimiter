package cn.idea360.ratelimiter.rule;

import cn.idea360.ratelimiter.rule.datasource.FileRuleConfigSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RateLimitRuleTest {

	@Test
	public void getLimit() {
		FileRuleConfigSource ruleConfigSource = new FileRuleConfigSource();
		RuleConfig ruleConfig = ruleConfigSource.load();
		TrieRateLimitRule rateLimitRule = new TrieRateLimitRule(ruleConfig);
		ApiLimit limit = rateLimitRule.getLimit("app-1", "/v1/user");
		log.info(limit.toString());
	}

}