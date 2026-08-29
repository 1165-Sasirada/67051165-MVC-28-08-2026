package Model;

public class Decisions {
	private static int decision_count = 0;

	private String id;
	private String request_id;
	private String member_id;
	private DecisionResult result;

	public Decisions(Role_Change_Requests req, String member, DecisionResult result) {
		if (member.equals(req.get_requester_id()) || member.equals(req.get_target_id())) {
			throw new IllegalArgumentException("Neither the requester nor the target member is allowed to vote.");
		}

		decision_count++;

		this.id = nextId();
		this.request_id = req.get_id();
		this.member_id = member;
		this.result = result;
	}

	private static String nextId() {
		String new_id = String.format("D%02d", decision_count);
		return new_id;
	}

	public String get_id() {
		return this.id;
	}

	public String get_request_id() {
		return this.request_id;
	}

	public String get_member_id() {
		return this.member_id;
	}

	public DecisionResult get_decision() {
		return this.result;
	}
}
