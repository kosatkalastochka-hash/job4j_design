package ru.job4j.generics;

public class Role extends Base {
    private final String profession;

    public Role(String id, String profession) {
        super(id);
        this.profession = profession;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public String getProfession() {
        return profession;
    }
}
