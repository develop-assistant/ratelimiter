package cn.idea360.ratelimiter.rule;

import lombok.Data;

/**
 * @author cuishiying
 */
@Data
public class ApiLimit {

	private static final int DEFAULT_TIME_UNIT = 1; // 1s

	private String api;

	private int limit;

	private int unit = DEFAULT_TIME_UNIT;

	public ApiLimit() {
	}

	public ApiLimit(String api, int limit) {
		this(api, limit, DEFAULT_TIME_UNIT);
	}

	public ApiLimit(String api, int limit, int unit) {
		this.api = api;
		this.limit = limit;
		this.unit = unit;
	}

}
