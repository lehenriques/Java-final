package com.desafio.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "university_cod")
    private Long universityCod;

    @Column(name = "course_cod")
    private Long courseCod;

    @Column(name = "movimentacao")
    private String movimentacao;

    @Column(name = "saldo")
    private Double saldo;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "date_movimentacao")
    private Date dateMovimentacao;

    public Account() {

    }

    public Account(Long universityCod, Long courseCod, String movimentacao, Date dateMovimentacao) {
        super();
        this.universityCod = universityCod;
        this.courseCod = courseCod;
        this.movimentacao = movimentacao;
        this.saldo = saldo;
        this.dateMovimentacao = dateMovimentacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getUniversityCod() {
        return universityCod;
    }

    public void setUniversityCod(Long universityCod) {
        this.universityCod = universityCod;
    }

    public Long getCourseCod() {
        return courseCod;
    }

    public void setCourseCod(Long courseCod) {
        this.courseCod = courseCod;
    }

    public String getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getDateMovimentacao() {
        return dateMovimentacao;
    }

    public void setDateMovimentacao(Date dateMovimentacao) {
        this.dateMovimentacao = dateMovimentacao;
    }

    public void deposit(Double valor) {
        this.saldo += valor;
    }

    public void withdraw(Double valor){
        this.saldo -= valor;
    }

}
