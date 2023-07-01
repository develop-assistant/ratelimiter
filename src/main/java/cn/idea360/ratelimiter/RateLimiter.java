package cn.idea360.ratelimiter;

import cn.idea360.ratelimiter.algorithm.FixedTimeWinRateLimitAlg;
import cn.idea360.ratelimiter.algorithm.RateLimitAlg;
import cn.idea360.ratelimiter.rule.ApiLimit;
import cn.idea360.ratelimiter.rule.RateLimitRule;
import cn.idea360.ratelimiter.rule.RuleConfig;
import cn.idea360.ratelimiter.rule.TrieRateLimitRule;
import cn.idea360.ratelimiter.rule.datasource.FileRuleConfigSource;
import cn.idea360.ratelimiter.rule.datasource.RuleConfigSource;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cuishiying
 */
@Slf4j
public class RateLimiter {

	private final ConcurrentHashMap<String, RateLimitAlg> counters = new ConcurrentHashMap<>();

	private final RateLimitRule rule;

	public RateLimiter() {
		RuleConfigSource configSource = new FileRuleConfigSource();
		RuleConfig ruleConfig = configSource.load();
		this.rule = new TrieRateLimitRule(ruleConfig);
	}

	public boolean limit(String appId, String url) throws Exception {
		ApiLimit apiLimit = rule.getLimit(appId, url);
		if (apiLimit == null) {
			return true;
		}
		// 获取api对应在内存中的限流计数器（rateLimitCounter）
		String counterKey = appId + ":" + apiLimit.getApi();
		RateLimitAlg rateLimitCounter = counters.get(counterKey);
		if (rateLimitCounter == null) {
			RateLimitAlg newRateLimitCounter = new FixedTimeWinRateLimitAlg(apiLimit.getLimit());
			rateLimitCounter = counters.putIfAbsent(counterKey, newRateLimitCounter);
			if (rateLimitCounter == null) {
				rateLimitCounter = newRateLimitCounter;
			}
		}
		// 判断是否限流
		return rateLimitCounter.tryAcquire();
	}

}
