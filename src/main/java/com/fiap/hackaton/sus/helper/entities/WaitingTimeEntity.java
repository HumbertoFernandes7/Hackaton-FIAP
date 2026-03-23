package com.fiap.hackaton.sus.helper.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_waiting_time")
public class WaitingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, unique = true)
    private HealthUnitEntity healthUnitId;

    @PositiveOrZero
    @Column(nullable = false)
    private int waitingTimeMinutes;

    @PositiveOrZero
    @Column(nullable = false)
    private int waitingPatients;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = true)
    private Instant updatedAt;
}