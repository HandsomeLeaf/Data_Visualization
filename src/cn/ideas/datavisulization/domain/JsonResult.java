package cn.ideas.datavisulization.domain;

import java.util.List;

public class JsonResult {
	private List<String> timeShaft;
	private List<Integer> patentApplyCount;

	public List<String> getTimeShaft() {
		return timeShaft;
	}

	public void setTimeShaft(List<String> timeShaft) {
		this.timeShaft = timeShaft;
	}

	public List<Integer> getPatentApplyCount() {
		return patentApplyCount;
	}

	public void setPatentApplyCount(List<Integer> patentApplyCount) {
		this.patentApplyCount = patentApplyCount;
	}
}
