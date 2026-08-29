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
		Role_Change_Requests req = new Role_Change_Requests(requester_id, target_id, new_role);
		requests.put(req.get_id(), req);
		
		return req;
	}

	public Decisions castDecisions(String request_id, String member_id, DecisionResult d) {
		Role_Change_Requests req = requests.get(request_id);

		if (req == null) {
			throw new IllegalArgumentException("Requets not found.");
		}

		Decisions decision = new Decisions(req, member_id, d);
		decisions.add(decision);
		req.processDecision(d);
		
		return decision;
	}

	public Map<String, Role_Change_Requests> get_Requests() {
		return requests;
	}
}
