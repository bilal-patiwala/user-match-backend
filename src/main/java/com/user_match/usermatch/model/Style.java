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
    private String style_tag;
    private String clothing_type;
    private String size_info;
    private String outfit_swap_type;
}
