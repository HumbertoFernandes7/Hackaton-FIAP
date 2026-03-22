package com.fiap.hackaton.sus.helper.entities;

import com.fiap.hackaton.sus.helper.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_health_unit")
public class HealthUnitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitType unitType;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private AddressEntity addressId;

    @Column(nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    private BigDecimal longitude;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private boolean open24h;

    @Column(nullable = false)
    private int maxCapacity;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    private Instant updatedAt;
}
