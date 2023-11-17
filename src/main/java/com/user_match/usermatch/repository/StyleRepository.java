package com.user_match.usermatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_match.usermatch.model.Style;

public interface StyleRepository extends JpaRepository<Style, Long>{

    Optional<Style> findByProperties(String style_tag, String clothing_type, String size_info);
    
}
