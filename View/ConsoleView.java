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
			System.out.println("3. Submit Decision");
			System.out.println("4. Exit");

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
					handleDecision();
					break;
				case "4":
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
}
