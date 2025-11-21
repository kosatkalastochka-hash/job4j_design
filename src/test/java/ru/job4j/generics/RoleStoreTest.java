package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRolenameIsprogrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        Role result = store.findById("1");
        assertThat(result.getProfession()).isEqualTo("programmer");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsprogrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.add(new Role("1", "manager"));
        Role result = store.findById("1");
        assertThat(result.getProfession()).isEqualTo("programmer");
    }

    @Test
    void whenReplaceThenRolenameIsmanager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.replace("1", new Role("1", "manager"));
        Role result = store.findById("1");
        assertThat(result.getProfession()).isEqualTo("manager");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.replace("10", new Role("10", "manager"));
        Role result = store.findById("1");
        assertThat(result.getProfession()).isEqualTo("programmer");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsprogrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getProfession()).isEqualTo("programmer");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        boolean result = store.replace("1", new Role("1", "manager"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        boolean result = store.replace("10", new Role("10", "manager"));
        assertThat(result).isFalse();
    }
}

