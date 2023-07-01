package cn.idea360.ratelimiter.rule;

/**
 * @author cuishiying
 */
public class TrieRateLimitRule implements RateLimitRule {

	private final RuleConfig ruleConfig;

	public TrieRateLimitRule(RuleConfig ruleConfig) {
		this.ruleConfig = ruleConfig;
	}

	@Override
	public ApiLimit getLimit(String appId, String uri) {
		if (ruleConfig == null || ruleConfig.getConfigs() == null) {
			return null;
		}

		for (RuleConfig.AppRuleConfig config : ruleConfig.getConfigs()) {
			if (config.getAppId().equals(appId)) {
				for (ApiLimit limit : config.getLimits()) {
					if (uri.startsWith(limit.getApi())) {
						return limit;
					}
				}
			}
		}

		return null;
	}

}
