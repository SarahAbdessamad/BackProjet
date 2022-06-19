package com.example.backprojet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long IdVacation;

    private String VacationStartDate;
    private String VacationEndDate;
    public String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Vacation(long idVacation, String vacationStartDate, String vacationEndDate,String userName) {
        IdVacation = idVacation;
        VacationStartDate = vacationStartDate;
        VacationEndDate = vacationEndDate;
        this.userName=userName;
    }

    @JsonIgnore
    @ManyToOne
    private Users userss;

/*    public Users getUsers() {
        return userss;
    }
 */
    public void setUsers(Users users) {
        this.userss = users;
    }




    public Vacation() {

    }

    public long getIdVacation() {
        return IdVacation;
    }

    public void setIdVacation(long idVacation) {
        IdVacation = idVacation;
    }

    public String getVacationStartDate() {
        return VacationStartDate;
    }

    public void setVacationStartDate(String vacationStartDate) {
        VacationStartDate = vacationStartDate;
    }

    public String getVacationEndDate() {
        return VacationEndDate;
    }

    public void setVacationEndDate(String vacationEndDate) {
        VacationEndDate = vacationEndDate;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "IdVacation=" + IdVacation +
                ", VacationStartDate='" + VacationStartDate + '\'' +
                ", VacationEndDate='" + VacationEndDate + '\'' +
                ", userName='" + userName + '\'' +

                '}';
    }
}

