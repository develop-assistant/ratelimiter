package cn.idea360.ratelimiter.rule.parse;

import cn.idea360.ratelimiter.rule.RuleConfig;

import java.io.InputStream;

/**
 * @author cuishiying
 */
public interface RuleConfigParser {

	RuleConfig parse(String configText);

	RuleConfig parse(InputStream in);

}
