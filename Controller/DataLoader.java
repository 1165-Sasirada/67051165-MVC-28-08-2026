package Controller;

import Model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class DataLoader {
	private final Map<String, Members> members = new LinkedHashMap<>();
	private final Map<String, Role_Change_Requests> requests = new LinkedHashMap<>();
	private final List<Decisions> decisions = new ArrayList<>();

	public void loadSeedData(String filePath) throws IOException {
		String json = new String(Files.readAllBytes(Paths.get(filePath)));

		Matcher m = Pattern.compile("\\{\\s*\"id\":\\s*\"([^\"]+)\",\\s*\"name\":\\s*\"([^\"]+)\",\\s*\"role\":\\s*\"([^\"]+)\",\\s*\"active\":\\s*(true|false)\\s*\\}").matcher(json);
		while (m.find()) {
			Members member = new Members(m.group(2), Role.valueOf(m.group(3)));
			members.put(m.group(1), member);
		}

		Matcher r = Pattern.compile("\\{\\s*\"id\":\\s*\"([^\"]+)\",\\s*\"requester_id\":\\s*\"([^\"]+)\",\\s*\"target_id\":\\s*\"([^\"]+)\",\\s*\"new_role\":\\s*\"([^\"]+)\",\\s*\"status\":\\s*\"([^\"]+)\"\\s*\\}").matcher(json);
		while (r.find()) {
			Role_Change_Requests req = new Role_Change_Requests(r.group(2), r.group(3), Role.valueOf(r.group(4)));
			requests.put(r.group(1), req);
		}

		Matcher d = Pattern.compile("\\{\\s*\"request_id\":\\s*\"([^\"]+)\",\\s*\"member_id\":\\s*\"([^\"]+)\",\\s*\"result\":\\s*\"([^\"]+)\"\\s*\\}").matcher(json);
		while (d.find()) {
			Role_Change_Requests req = requests.get(d.group(1));
			if (req != null) {
				DecisionResult result = DecisionResult.valueOf(d.group(3));
				Decisions decision = new Decisions(req, d.group(2), result);
				decisions.add(decision);

				req.processDecision(result);
			}
		}
	}

	public Map<String, Members> get_Members() {
		return members;
	}

	public Map<String, Role_Change_Requests> get_Requests() {
		return requests;
	}

	public List<Decisions> get_Decisions() {
		return decisions;
	}
}
