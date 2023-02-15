package com.naya.oracledbpocapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT", schema = "EVENT_ID_TEST")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "SESSION_ID")
    private Long sessionId;

    @Column(name = "EVENT_ID")
    private String eventId;

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", sessionId=" + sessionId +
                ", eventId='" + eventId + '\'' +
                '}';
    }
}
