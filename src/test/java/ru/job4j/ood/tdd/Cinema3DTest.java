package ru.job4j.ood.tdd;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class Cinema3DTest {
    @Disabled
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenBuyOfSoldTicket(){
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(SeatOccupiedException.class);
    }

    @Test
    public void whenBuyWhithNullAccountThenGetException(){
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(null, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyWhithNullDateThenGetException(){
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        assertThatThrownBy(() -> cinema.buy(account,1,2,null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnInvalidColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, -1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAddNullSessionThenGetException() {
        Cinema cinema = new Cinema3D();

        assertThatThrownBy(() -> cinema.add(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyTicketThenTicketHasCorrectFields() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        int row = 5;
        int seat = 7;

        Ticket ticket = cinema.buy(account, row, seat, date);

        assertThat(ticket.getRow()).isEqualTo(row);
        assertThat(ticket.getSeat()).isEqualTo(seat);
        assertThat(ticket.getDate()).isEqualTo(date);
        assertThat(ticket.getAccount()).isEqualTo(account);
        assertThat(ticket.getCinema()).isEqualTo(cinema);
    }

}