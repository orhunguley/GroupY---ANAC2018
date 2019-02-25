import java.util.HashMap;
import java.util.List;

import negotiator.Bid;
import negotiator.issue.Issue;
import negotiator.issue.IssueDiscrete;
import negotiator.issue.Value;
import negotiator.issue.ValueDiscrete;
import negotiator.utility.UtilitySpace;

public class OpponentModel {
	private double time;
	private HashMap<Issue, HashMap<Value, Double>> seenWeigth;

	public OpponentModel(UtilitySpace utilitySpace, double time) {

		this.time = time;
		init(utilitySpace);

	}

	private void init(UtilitySpace utilitySpace) {
		seenWeigth = new HashMap<Issue, HashMap<Value, Double>>();
		for (Issue issue : utilitySpace.getDomain().getIssues()) {

			List<ValueDiscrete> values = ((IssueDiscrete) issue).getValues();
			HashMap<Value, Double> valueMap = new HashMap<Value, Double>();
			for (Value value : values) {
				valueMap.put(value, 0.0);
			}
			seenWeigth.put(issue, valueMap);
		}
	}

	public double timeEffect() {
		return Math.pow((double) (1 - time), Math.E);

	}

	public void updateModel(Bid bid,int normalize) {

		for (int issueIndex = 0; issueIndex < bid.getIssues().size(); issueIndex++) {
			Issue issue = bid.getIssues().get(issueIndex);
			Value issueValue = bid.getValue(issueIndex + 1);
			HashMap<Value, Double> valueMap = seenWeigth.get(issue);

			valueMap.put(issueValue, valueMap.get(issueValue) + timeEffect()/normalize);

		}
	}

	public double ExpectedUtility(Bid bid) {

		double utility = 0;
		for (int issueIndex = 0; issueIndex < bid.getIssues().size(); issueIndex++) {
			Issue issue = bid.getIssues().get(issueIndex);
			Value issueValue = bid.getValue(issueIndex + 1);

			HashMap<Value, Double> valueMap = seenWeigth.get(issue);
			double weight = valueMap.get(issueValue);

			utility += weight;
		}
		return utility;
	}

}