package cn.idea360.ratelimiter.algorithm;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cuishiying
 */
public class FixedTimeWinRateLimitAlg implements RateLimitAlg {

	/* timeout for {@code Lock.tryLock() }. */
	private static final long TRY_LOCK_TIMEOUT = 200L; // 200ms.

	private final Stopwatch stopwatch;

	private final AtomicInteger currentCount = new AtomicInteger(0);

	private final int limit;

	private final Lock lock = new ReentrantLock();

	public FixedTimeWinRateLimitAlg(int limit) {
		this(limit, Stopwatch.createStarted());
	}

	protected FixedTimeWinRateLimitAlg(int limit, Stopwatch stopwatch) {
		this.limit = limit;
		this.stopwatch = stopwatch;
	}

	@Override
	public boolean tryAcquire() throws Exception {
		int updatedCount = currentCount.incrementAndGet();
		if (updatedCount <= limit) {
			return true;
		}

		try {
			if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
				try {
					if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
						currentCount.set(0);
						stopwatch.reset();
					}
					updatedCount = currentCount.incrementAndGet();
					return updatedCount <= limit;
				}
				finally {
					lock.unlock();
				}
			}
			else {
				throw new Exception("tryAcquire() wait lock too long:" + TRY_LOCK_TIMEOUT + "ms");
			}
		}
		catch (InterruptedException e) {
			throw new InterruptedException("tryAcquire() is interrupted by lock-time-out.");
		}
	}

}
