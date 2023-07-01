package cn.idea360.ratelimiter.rule.datasource;

import cn.idea360.ratelimiter.rule.RuleConfig;

/**
 * @author cuishiying
 */
public interface RuleConfigSource {

	RuleConfig load();

}
