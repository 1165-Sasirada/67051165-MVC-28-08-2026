package Controller;

import Model.*;
import java.util.*;

public class RequestController {
	private final Map<String, Members> members;
	private final Map<String, Role_Change_Requests> requests;
	private final List<Decisions> decisions;

	public RequestController(Map<String, Members> members, Map<String, Role_Change_Requests> requests, List<Decisions> decisions) {
		this.members = members;
		this.requests = requests;
		this.decisions = decisions;
	}

	public Role_Change_Requests createRequest(String requester_id, String target_id, Role new_role) {
		for (Role_Change_Requests req : requests.values()) {
			if (req.get_target_id().equals(target_id) && req.get_status() == RequestStatus.PENDING) {
				throw new IllegalStateException("This traget member already has another request pending.");
			}
		}

		Role_Change_Requests req = new Role_Change_Requests(requester_id, target_id, new_role);
		requests.put(req.get_id(), req);
		
		return req;
	}

	public void cancelRequest(String request_id, String member_id) {
		Role_Change_Requests req = requests.get(request_id);
		if (req == null) {
			throw new IllegalArgumentException("Request not found.");
		}

		req.cancelRequest(member_id);
	}

	public Decisions castDecisions(String request_id, String member_id, DecisionResult d) {
		Role_Change_Requests req = requests.get(request_id);

		if (req == null) {
			throw new IllegalArgumentException("Requet not found.");
		}

		for (Decisions dec : decisions) {
			if (dec.get_request_id().equals(request_id) && dec.get_member_id().equals(member_id)) {
				throw new IllegalStateException("ALready voted on this request.");
			}
		}

		Decisions decision = new Decisions(req, member_id, d);
		Members target = members.get(req.get_target_id());
		
		decisions.add(decision);
		req.processDecision(d, target);
		
		return decision;
	}

	public Map<String, Role_Change_Requests> get_Requests() {
		return requests;
	}
}
