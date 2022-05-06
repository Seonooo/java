package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TICKET_STATE")
public class TicketStateEntity {
    // 예매상태
    // 0 -> 결제완료 / 1 -> 결제취소 / 2-> 기간만료 /
    @Column(length = 10)
    private String tsstate;
    // 예매상태코드
    @Id
    private Long tscode;
    // 회원예매
    // @JsonBackReference
    // @OneToMany(mappedBy = "ticketStateEntity")
    // private List<TicketEntity> ticketEntityList = new ArrayList<>();
    // // 비회원예매
    // @JsonBackReference
    // @OneToMany(mappedBy = "ticketStateEntity")
    // private List<VisitorTicketEntity> visitorTicketEntityList = new
    // ArrayList<>();
}
