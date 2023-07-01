package cn.idea360.ratelimiter.algorithm;

/**
 * @author cuishiying
 */
public interface RateLimitAlg {

	boolean tryAcquire() throws Exception;

}
