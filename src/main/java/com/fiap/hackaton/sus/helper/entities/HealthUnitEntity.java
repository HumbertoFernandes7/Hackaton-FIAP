package com.fiap.hackaton.sus.helper.entities;

import com.fiap.hackaton.sus.helper.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_health_unit")
public class HealthUnitEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnitType unitType;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressEntity addressId;

    @Column(nullable = false, precision = 18, scale = 15)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 18, scale = 15)
    private BigDecimal longitude;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private boolean open24h;

    @Column(nullable = true)
    private LocalTime openTime;

    @Column(nullable = true)
    private LocalTime closeTime;

    @Column(nullable = false)
    private int maxCapacity;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    private Instant updatedAt;
}
