package org.group02.guitarshop.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Name", nullable = true, columnDefinition="nvarchar(255)")
    private String name;

    @Column(name = "Email", nullable = true, length = 50)
    private String email;

    @Column(name = "Title", nullable = true, columnDefinition="nvarchar(255)")
    private String title;

    @Column(name = "Message_Content", nullable = true, columnDefinition="nvarchar(1000)")
    private String messageContent;

}
