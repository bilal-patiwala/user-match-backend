package com.user_match.usermatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "style")
public class Style {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String body_type;
    private String cloths_prefrence;
    private String clothing_style;
    private String winter_wear_type;
    private String outfit_swap_type;
}
