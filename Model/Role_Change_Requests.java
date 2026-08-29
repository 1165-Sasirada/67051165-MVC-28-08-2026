package Model;

public class Role_Change_Requests {
	private static int request_count = 0;
	
	private String id;
	private String requester_id;
	private String target_id;
	private Role new_role;
	private RequestStatus status;

	private int approval_count = 0;
	private int rejection_count = 0;

	public Role_Change_Requests(String requester, String target, Role new_role) {
		request_count++;

		this.id = nextId();
		this.requester_id = requester;
		this.target_id = target;
		this.new_role = new_role;
		this.status = RequestStatus.PENDING;
	}

	private static String nextId() {
		String new_id = String.format("C%02d", request_count);
		return new_id;
	}

	public void processDecision(DecisionResult d) {
		if (this.status == RequestStatus.CANCELLED) {
			throw new IllegalStateException("This request has been cancelled.");
		}

		if ((this.status == RequestStatus.APPROVED) || (this.status == RequestStatus.REJECTED)) {
			throw new IllegalStateException("This role change request is completed.");
		}

		if (d == DecisionResult.APPROVE) {
			this.approval_count++;
		} else {
			this.rejection_count++;
		}

		if (this.approval_count >= 2) {
			this.status = RequestStatus.APPROVED;
		} else if (this.rejection_count >= 2) {
			this.status = RequestStatus.REJECTED;
		}
	}

	public void cancelRequest(String member_id) {
		if (!member_id.equals(this.requester_id)) {
			throw new IllegalArgumentException("Only original requester can cancel a request.");
		}
		if ((this.approval_count > 0) || (this.rejection_count > 0)) {
			throw new IllegalStateException("Votes have been casted. This request cannot be cancelled.");
		}

		this.status = RequestStatus.CANCELLED;
		System.out.println("Request cancelled successfully.");
	}
	
	public String get_id() {
		return this.id;
	}

	public String get_requester_id() {
		return this.requester_id;
	}

	public String get_target_id() {
		return this.target_id;
	}

	public Role get_new_role() {
		return this.new_role;
	}

	public RequestStatus get_status() {
		return this.status;
	}

	public int get_approval_count() {
		return this.approval_count;
	}

	public int get_rejection_count() {
		return this.rejection_count;
	}
}
