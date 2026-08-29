import Model.*;
import View.*;
import Controller.*;

public class Main {
	public static void main(String[] args) {
        DataLoader loader = new DataLoader();

        try {
            loader.loadSeedData("seed_data.json");
            System.out.println("Seed data loaded successfully.");
        } catch (Exception e) {
            System.err.println("Failed to load seed data: " + e.getMessage());
            return;
        }

        MemberController m = new MemberController(loader.get_Members());
        RequestController r = new RequestController(loader.get_Members(), loader.get_Requests(), loader.get_Decisions());

        ConsoleView view = new ConsoleView(m, r);
        view.start();
    }
}
