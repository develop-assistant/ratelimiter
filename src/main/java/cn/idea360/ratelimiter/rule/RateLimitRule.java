package cn.idea360.ratelimiter.rule;

/**
 * @author cuishiying
 */
public interface RateLimitRule {

	ApiLimit getLimit(String appId, String uri);

}
