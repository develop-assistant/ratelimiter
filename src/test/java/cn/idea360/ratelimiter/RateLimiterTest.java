package cn.idea360.ratelimiter;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RateLimiterTest {

	@Test
	public void limit() throws Exception {
		RateLimiter rateLimiter = new RateLimiter();
		for (int i = 0; i < 110; i++) {
			boolean limit = rateLimiter.limit("app-1", "/v1/user");
			log.info("limit: {}", limit);
		}
	}

}