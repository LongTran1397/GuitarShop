package org.group02.guitarshop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Email", nullable = true, length = 50)
    private String email;

    @Column(name = "Name", nullable = true, columnDefinition="nvarchar(255)")
    private String name;

    @Column(name = "Title", nullable = true, columnDefinition="nvarchar(255)")
    private String title;

    @Column(name = "Message_Content", nullable = true, columnDefinition="nvarchar(1000)")
    private String messageContent;
    
    // @Column(name = "product_id")
    // private int productId;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product product;

    public Message(String email, String name, String messageContent, Product product) {
        this.email = email;
        this.name = name;
        this.messageContent = messageContent;
        this.product = product;
    }
    
}
