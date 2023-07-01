package cn.idea360.ratelimiter.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RateLimitAlgTest {

	@Test
	public void tryAcquire() throws Exception {
		RateLimitAlg rateLimitAlg = new FixedTimeWinRateLimitAlg(2);
		for (int i = 0; i < 3; i++) {
			boolean acquire = rateLimitAlg.tryAcquire();
			log.info("limit: {}", acquire);
		}
	}

}