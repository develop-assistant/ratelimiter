package cn.idea360.ratelimiter.rule.parse;

import cn.idea360.ratelimiter.rule.RuleConfig;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.InputStream;

/**
 * @author cuishiying
 */
public class YamlRuleConfigParser implements RuleConfigParser {

	@Override
	public RuleConfig parse(String configText) {
		return null;
	}

	@Override
	public RuleConfig parse(InputStream in) {
		if (in != null) {
			Representer represent = new Representer();
			represent.getPropertyUtils().setSkipMissingProperties(true);
			Yaml yaml = new Yaml(represent);
			return yaml.loadAs(in, RuleConfig.class);
		}
		return null;
	}

}
