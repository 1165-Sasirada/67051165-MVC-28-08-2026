package View;

import Model.*;
import Controller.*;
import java.util.*;

public class ConsoleView {
	private final MemberController memberController;
	private final RequestController requestController;
	private final Scanner sc = new Scanner(System.in);

	public ConsoleView(MemberController m, RequestController r) {
		this.memberController = m;
		this.requestController = r;
	}

	public void start() {
		while (true) {
			System.out.println("===== MENU =====");
			System.out.println("1. List Members");
			System.out.println("2. List Requests");
			System.out.println("3. Create Role Change Request");
			System.out.println("4. Cancel Role Change Request");
			System.out.println("5. Submit Decision");
			System.out.println("6. Add New Member");
			System.out.println("7. Exit");

			System.out.print("Your choice: ");
			String choice = sc.nextLine();
			switch (choice) {
				case "1":
					listMembers();
					break;
				case "2":
					listRequests();
					break;
				case "3":
					handleCreateRequest();
					break;
				case "4":
					handleCancelRequest();
					break;
				case "5":
					handleDecision();
					break;
				case "6":
					handleAddMember();
					break;
				case "7":
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice.");
					break;
			}
		}
	}

	private void listMembers() {
		System.out.println("=== Members ===");
		for (Members m : memberController.get_Members().values()) {
			System.out.printf("[%s] %s | Role: %s | Active: %b%n", m.get_id(), m.get_name(), m.get_role(), m.get_status());
		}
	}

	private void listRequests() {
		System.out.println("=== Role Change Requests ===");
		for (Role_Change_Requests r : requestController.get_Requests().values()) {
			System.out.printf("[%s] Requester: %s | Target: %s | New Role: %s | Status: %s (Approvals: %d, Rejections: %d)%n", 
			r.get_id(), r.get_requester_id(), r.get_target_id(), r.get_new_role(), r.get_status(), r.get_approval_count(), r.get_rejection_count());
		}
	}

	private void handleCreateRequest() {
		try {
			System.out.print("Requester ID: ");
			String requester_id = sc.nextLine();
			System.out.print("Target ID: ");
			String target_id = sc.nextLine();
			System.out.print("New Role (CREATOR / PRODUCER / EDITOR / FINANCE): ");
			Role role = Role.valueOf(sc.nextLine().toUpperCase());
			
			Role_Change_Requests req = requestController.createRequest(requester_id, target_id, role);
			System.out.println("Create Request: " + req.get_id());
		} catch (Exception e) {
			System.out.println("Failed to create request: " + e.getMessage());
		}
	}

	public void handleCancelRequest() {
		try {
			System.out.print("Request ID: ");
			String request_id = sc.nextLine();
			System.out.print("Member ID: ");
			String member_id = sc.nextLine();
			
			requestController.cancelRequest(request_id, member_id);
		} catch (Exception e) {
			System.out.println("Cancellation Failed: " + e.getMessage());
		}
	}

	private void handleDecision() {
		try {
			System.out.print("Request ID: ");
			String req_id = sc.nextLine();
			System.out.print("Member ID: ");
			String member_id = sc.nextLine();
			System.out.print("Decision (APPROVE / REJECT): ");
			DecisionResult result = DecisionResult.valueOf(sc.nextLine().toUpperCase());

			requestController.castDecisions(req_id, member_id, result);
			System.out.println("Decision recorded.");
		} catch (Exception e) {
			System.out.println("Voting failed: " + e.getMessage());
		}
	}

	public void handleAddMember() {
		try {
			System.out.print("Name: ");
			String name = sc.nextLine();
			System.out.print("Role (CREATOR / PRODUCER / EDITOR / FINANCE): ");
			Role role = Role.valueOf(sc.nextLine().toUpperCase());

			Members m = memberController.addMembers(name, role);
			System.out.println("New member added: " + m.get_id());
		} catch (Exception e) {
			System.out.println("Failed to add new member: " + e.getMessage());
		}
	}
}
