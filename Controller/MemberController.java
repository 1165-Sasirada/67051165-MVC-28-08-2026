package Controller;

import Model.*;
import java.util.*;

public class MemberController {
    private final Map<String, Members> members;

    public MemberController(Map<String, Members> members) {
        this.members = members;
    }

    public Members addMembers(String name, Role role) {
        Members member = new Members(name, role);
        members.put(member.get_id(), member);

        return member;
    }

    public Map<String, Members> get_Members() {
        return members;
    }
}
