package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SupplierEntity {
    @Id
    private String id;
    private String name;
    private String company;

    @Column(nullable = false,unique = true)
    private String email;

    private String address;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private UserEntity user;
}
