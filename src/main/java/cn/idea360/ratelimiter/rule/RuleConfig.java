package cn.idea360.ratelimiter.rule;

import lombok.Data;

import java.util.List;

/**
 * @author cuishiying
 */
@Data
public class RuleConfig {

	private List<AppRuleConfig> configs;

	@Data
	public static class AppRuleConfig {

		private String appId;

		private List<ApiLimit> limits;

		public AppRuleConfig() {
		}

		public AppRuleConfig(String appId, List<ApiLimit> limits) {
			this.appId = appId;
			this.limits = limits;
		}

	}

}
